package com.artinus.userapp.constant;

public enum SubscriptionStatus {

    NOT_SUBSCRIBED("구독 안함", 0),
    REGULAR_SUBSCRIPTION("일반 구독", 1),
    PREMIUM_SUBSCRIPTION("프리미엄 구독", 2),
    ;

    private final String name;

    private final int subscriptionLevel;

    SubscriptionStatus(String name, int subscriptionLevel) {
        this.name = name;
        this.subscriptionLevel = subscriptionLevel;
    }

    public String getName() {
        return name;
    }

    public int getSubscriptionLevel() {
        return subscriptionLevel;
    }
}
