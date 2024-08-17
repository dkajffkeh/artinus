package com.artinus.userapp.payload.response;

import com.artinus.userapp.constant.SubscriptionStatus;
import java.time.LocalDateTime;

public class SubscriptionAction {

    private String subscriptionStart;
    private String subscriptionEnd;
    private SubscriptionStatus prevSubscriptionType;
    private SubscriptionStatus nextSubscriptionType;
    private LocalDateTime actionDateTime;

    public SubscriptionAction() {
    }

    public SubscriptionAction(String subscriptionStart, String subscriptionEnd,
            SubscriptionStatus prevSubscriptionType,
            SubscriptionStatus nextSubscriptionType, LocalDateTime actionDateTime) {
        this.subscriptionStart = subscriptionStart;
        this.subscriptionEnd = subscriptionEnd;
        this.prevSubscriptionType = prevSubscriptionType;
        this.nextSubscriptionType = nextSubscriptionType;
        this.actionDateTime = actionDateTime;
    }

    public String getSubscriptionStart() {
        return subscriptionStart;
    }

    public String getSubscriptionEnd() {
        return subscriptionEnd;
    }

    public SubscriptionStatus getPrevSubscriptionType() {
        return prevSubscriptionType;
    }

    public SubscriptionStatus getNextSubscriptionType() {
        return nextSubscriptionType;
    }

    public LocalDateTime getActionDateTime() {
        return actionDateTime;
    }
}
