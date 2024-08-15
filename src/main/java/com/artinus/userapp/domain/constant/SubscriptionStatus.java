package com.artinus.userapp.domain.constant;

public enum SubscriptionStatus {

    NOT_SUBSCRIBED("구독 안함"),
    REGULAR_SUBSCRIPTION("일반 구독"),
    PREMIUM_SUBSCRIPTION("프리미엄 구독"),
    ;

    private final String name;

    SubscriptionStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
