package com.artinus.userapp.domain.entity.channel;

import static javax.persistence.FetchType.LAZY;

import com.artinus.userapp.constant.ChannelType;
import com.artinus.userapp.domain.entity.base.BaseEntity;
import com.artinus.userapp.domain.entity.subscription.SubscriptionActionHist;
import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.payload.response.ChannelHistResponse;
import com.artinus.userapp.payload.response.ChannelHistResponse.ChannelHistPayload;
import com.artinus.userapp.payload.response.SubscriptionAction;
import com.artinus.userapp.payload.response.SubscriptionHistResponse.SingleChannelHist;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "CHANNEL")
public class Channel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CHANNEL_NAME", length = 30, nullable = false)
    private String channelName;

    @Column(name = "CHANNEL_TYPE", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    @OneToMany(fetch = LAZY, mappedBy = "channel")
    private List<SubscriptionActionHist> actionHists = new ArrayList<>();

    public Channel() {

    }

    public Channel(String channelName, ChannelType channelType) {
        this(null, channelName, channelType);
    }

    public Channel(Long id, String channelName,
            ChannelType channelType) {
        this.id = id;
        this.channelName = channelName;
        this.channelType = channelType;
    }

    public Long getId() {
        return id;
    }

    public String getChannelName() {
        return channelName;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public boolean isCancelOnly() {
        return this.channelType == ChannelType.CANCELLATION_ONLY;
    }

    public boolean isSubscribeOnly() {
        return this.channelType == ChannelType.SUBSCRIPTION_ONLY;
    }

    public ChannelHistResponse toChannelHistResponse(String date) {
        Map<WebUser, List<SubscriptionActionHist>> groupByWebUser = actionHists.stream()
                .collect(Collectors.groupingBy(SubscriptionActionHist::getWebUser));

        List<ChannelHistPayload> hists = new ArrayList<>();

        groupByWebUser.forEach((webUser, actionHists) -> {
            List<SubscriptionAction> sortedActions = actionHists.stream()
                    .map(SubscriptionActionHist::buildDtoAction)
                    .sorted(Comparator.comparing(SubscriptionAction::getActionDateTime).reversed())
                    .toList();

            hists.add(new ChannelHistPayload(
                    webUser.getId(),
                    webUser.getPhoneNumber(),
                    sortedActions));
        });

        return new ChannelHistResponse(date, id, hists);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Channel channel = (Channel) o;
        return Objects.equals(id, channel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
