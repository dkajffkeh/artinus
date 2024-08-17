package com.artinus.userapp.payload.request;

import com.artinus.userapp.constant.SubscriptionStatus;

public class UnSubscribeRequest {

    private String phoneNumber;

    private Long channelId;

    private SubscriptionStatus subscriptionStatus;

    public UnSubscribeRequest(String phoneNumber, Long channelId,
            SubscriptionStatus subscriptionStatus) {
        this.phoneNumber = phoneNumber;
        this.channelId = channelId;
        this.subscriptionStatus = subscriptionStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getChannelId() {
        return channelId;
    }

    public SubscriptionStatus getSubscriptionStatus() {
        return subscriptionStatus;
    }
}
