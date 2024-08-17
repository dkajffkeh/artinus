package com.artinus.userapp.domain.entity.subscription;

import com.artinus.userapp.constant.SubscriptionStatus;
import com.artinus.userapp.domain.entity.base.BaseEntity;
import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.payload.response.SubscriptionAction;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIPTION_ACTION_HIST")
public class SubscriptionActionHist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private WebUser webUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHANNEL_ID", nullable = false)
    private Channel channel;

    @Column(name = "SUBSCRIPTION_START")
    private String subscriptionStart;

    @Column(name = "SUBSCRIPTION_END")
    private String subscriptionEnd;

    @Column(name = "PREV_SUBSCRIPTION_STATUS")
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus prevSubscriptionStatus;

    @Column(name = "NEXT_SUBSCRIPTION_STATUS")
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus nextSubscriptionStatus;

    @Column(name = "SUBSCRIPTION_ACTION_DATE")
    private String subscriptionActionDate;



    public SubscriptionActionHist() {

    }

    public SubscriptionActionHist(WebUser webUser,
            Channel channel, String subscriptionStart, String subscriptionEnd,
            SubscriptionStatus prevSubscriptionStatus,
            SubscriptionStatus nextSubscriptionStatus, String subscriptionActionDate) {
        this(null, webUser, channel, subscriptionStart, subscriptionEnd, prevSubscriptionStatus, nextSubscriptionStatus, subscriptionActionDate);
    }

    public SubscriptionActionHist(Long id, WebUser webUser,
            Channel channel, String subscriptionStart, String subscriptionEnd,
            SubscriptionStatus prevSubscriptionStatus,
            SubscriptionStatus nextSubscriptionStatus, String subscriptionActionDate) {
        this.id = id;
        this.webUser = webUser;
        this.channel = channel;
        this.subscriptionStart = subscriptionStart;
        this.subscriptionEnd = subscriptionEnd;
        this.prevSubscriptionStatus = prevSubscriptionStatus;
        this.nextSubscriptionStatus = nextSubscriptionStatus;
        this.subscriptionActionDate = subscriptionActionDate;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getSubscriptionActionDate() {
        return subscriptionActionDate;
    }

    public SubscriptionAction buildDtoAction() {
        return new SubscriptionAction(
                this.subscriptionStart,
                this.subscriptionEnd,
                this.prevSubscriptionStatus,
                this.nextSubscriptionStatus,
                super.createdAt
        );
    }
}
