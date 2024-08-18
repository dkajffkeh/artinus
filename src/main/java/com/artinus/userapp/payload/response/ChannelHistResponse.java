package com.artinus.userapp.payload.response;

import java.util.List;

public class ChannelHistResponse {

    private String date;

    private Long channelId;

    private List<ChannelHistPayload> hists;

    public ChannelHistResponse(String date, Long channelId,
            List<ChannelHistPayload> hists) {
        this.date = date;
        this.channelId = channelId;
        this.hists = hists;
    }

    public String getDate() {
        return date;
    }

    public Long getChannelId() {
        return channelId;
    }

    public List<ChannelHistPayload> getHists() {
        return hists;
    }

    public static ChannelHistResponse emptyOf(String date, Long channelId) {
        return new ChannelHistResponse(date, channelId, List.of());
    }

    public static class ChannelHistPayload {

        private Long userId;

        private String phoneNumber;

        private List<SubscriptionAction> actions;

        public ChannelHistPayload(Long userId, String phoneNumber,
                List<SubscriptionAction> actions) {
            this.userId = userId;
            this.phoneNumber = phoneNumber;
            this.actions = actions;
        }

        public Long getUserId() {
            return userId;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public List<SubscriptionAction> getActions() {
            return actions;
        }
    }
}
