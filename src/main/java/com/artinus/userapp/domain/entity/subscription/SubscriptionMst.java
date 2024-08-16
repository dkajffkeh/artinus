package com.artinus.userapp.domain.entity.subscription;

import com.artinus.userapp.constant.SubscriptionStatus;
import com.artinus.userapp.domain.entity.base.BaseEntity;
import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.domain.entity.user.WebUser;
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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SUBSCRIPTION_MST", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USER_ID", "CHANNEL_ID"})
})
public class SubscriptionMst extends BaseEntity {

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

    @Column(name = "SUBSCRIPTION_ACTION_DATE")
    private String subscriptionActionDate;

    @Column(name = "SUBSCRIPTION_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscriptionStatus;

    public SubscriptionMst() {

    }

    public SubscriptionMst(Long id, WebUser webUser,
            Channel channel, SubscriptionStatus subscriptionStatus) {
        this.id = id;
        this.webUser = webUser;
        this.channel = channel;
        this.subscriptionStatus = subscriptionStatus;
    }
}
