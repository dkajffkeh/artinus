package com.artinus.userapp.constant;

public enum ActionType {

    SUBSCRIBE("구독"),
    UNSUBSCRIBE("해지")
    ;

    private final String name;

    ActionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
