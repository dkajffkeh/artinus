package com.artinus.userapp.payload.request;

import com.artinus.userapp.constant.SubscriptionStatus;

public class SubscribeRequest {

    private String phoneNumber;

    private Long channelId;

    private SubscriptionStatus subscriptionStatus;

    public SubscribeRequest(String phoneNumber, Long channelId,
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
