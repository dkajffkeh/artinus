package com.artinus.userapp.domain.entity.user;

import static com.artinus.userapp.constant.SubscriptionStatus.NOT_SUBSCRIBED;
import static com.artinus.userapp.exception.code.ArtinusErrorCode.CANCEL_ONLY_CHANNEL;
import static com.artinus.userapp.exception.code.ArtinusErrorCode.NOT_UPPER_SUBSCRIBE_REQUEST;
import static com.artinus.userapp.global.utils.DateTimeUtils.toyyyyMMdd;
import static javax.persistence.CascadeType.ALL;

import com.artinus.userapp.constant.SubscriptionStatus;
import com.artinus.userapp.domain.entity.base.BaseEntity;
import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.domain.entity.subscription.SubscriptionActionHist;
import com.artinus.userapp.domain.entity.subscription.SubscriptionMst;
import com.artinus.userapp.exception.ArtinusException;
import com.artinus.userapp.payload.request.SubscribeRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.util.ObjectUtils;

@Entity
@Table(name = "WEB_USER", indexes = {
        @Index(name = "idx_phone_number", columnList = "PHONE_NUMBER")
})
public class WebUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHONE_NUMBER", nullable = false, unique = true)
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "webUser", cascade = ALL)
    private List<SubscriptionMst> subscriptions = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "webUser", cascade = ALL)
    private List<SubscriptionActionHist> actionHists = new ArrayList<>();

    public List<SubscriptionMst> getSubscriptions() {
        return subscriptions;
    }

    public WebUser() {

    }

    public WebUser(String phoneNumber) {
        this(null, phoneNumber);
    }

    public WebUser(Long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public void subscribe(Channel channel,
            SubscriptionMst subscriptionMst,
            SubscribeRequest request) {

        // first action
        if(isFirstAction(subscriptionMst)) {
            subscribeFirst(channel, request);
            return;
        }

        // 구독 정보를 upgrade 하는 경우
        upgradeSubscribeLevel(channel, subscriptionMst, request);

    }

    private void upgradeSubscribeLevel(Channel channel,
            SubscriptionMst subscriptionMst,
            SubscribeRequest request) {

        LocalDateTime now = LocalDateTime.now();

        if(channel.isCancelOnly()) {
            throw new ArtinusException(CANCEL_ONLY_CHANNEL);
        }

        // 상위 레벨로의 구독 요청이 아닌경우
        if(!subscriptionMst.isUpperLevelReq(request.getSubscriptionStatus())) {
            throw new ArtinusException(NOT_UPPER_SUBSCRIBE_REQUEST);
        }



        this.actionHists.add(
                new SubscriptionActionHist(this,
                        channel,
                        toyyyyMMdd(now),
                        null,
                        subscriptionMst.getSubscriptionStatus(),
                        request.getSubscriptionStatus(),
                        toyyyyMMdd(now)));


    }

    // 최초 구독 정보 생성
    private void subscribeFirst(Channel channel, SubscribeRequest request) {
        // Cancel Only 타입으로 들어온 경우 다른 구독신청을 할 수 없다.
        if(request.getSubscriptionStatus() != NOT_SUBSCRIBED && channel.isCancelOnly()) {
            throw new ArtinusException(CANCEL_ONLY_CHANNEL);
        }

        SubscriptionMst subscriptionMst = new SubscriptionMst(this, channel, toyyyyMMdd(
                LocalDateTime.now()), request.getSubscriptionStatus());

        this.subscriptions.add(subscriptionMst);

        buildFirstHist(channel, request.getSubscriptionStatus());
    }

    private void buildFirstHist(Channel channel,
            SubscriptionStatus subscriptionStatus) {
        LocalDateTime now = LocalDateTime.now();
        // 구독 안함 hist
        if(subscriptionStatus == NOT_SUBSCRIBED) {
            this.actionHists.add(
                    new SubscriptionActionHist(this,
                            channel,
                            null,
                            toyyyyMMdd(now),
                            null,
                            subscriptionStatus,
                            toyyyyMMdd(now)));
            return;
        }

        // 나머지 구독 이벤트 핸들링
        this.actionHists.add(
                new SubscriptionActionHist(this,
                        channel,
                        toyyyyMMdd(now),
                        null,
                        null,
                        subscriptionStatus,
                        toyyyyMMdd(now)));

    }

    private boolean isFirstAction(SubscriptionMst subscriptionMst) {
        return ObjectUtils.isEmpty(subscriptionMst);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WebUser webUser = (WebUser) o;
        return Objects.equals(id, webUser.id) && Objects.equals(phoneNumber,
                webUser.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber);
    }
}
