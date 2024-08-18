package com.artinus.userapp.payload.response;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionHistResponse {

    private List<ChannelHist> hists = new ArrayList<>();

    public SubscriptionHistResponse() {

    }

    public SubscriptionHistResponse(
            List<ChannelHist> hists) {
        this.hists = hists;
    }

    public static SubscriptionHistResponse emptyOf() {
        return new SubscriptionHistResponse(new ArrayList<>());
    }

    public List<ChannelHist> getHists() {
        return hists;
    }

    public static class ChannelHist implements Comparable<ChannelHist> {

        private String date;

        private List<SingleChannelHist> channelHists;

        public ChannelHist() {

        }

        public ChannelHist(String date,
                List<SingleChannelHist> channelHists) {
            this.date = date;
            this.channelHists = channelHists;
        }

        public String getDate() {
            return date;
        }

        public List<SingleChannelHist> getChannelHists() {
            return channelHists;
        }


        @Override
        public int compareTo(ChannelHist other) {
            // 날짜를 정수형으로 변환하여 비교 (내림차순)
            return Integer.compare(Integer.parseInt(other.getDate()), Integer.parseInt(this.date));
        }
    }


    public static class SingleChannelHist {

        private String channelName;

        private Long channelId;

        private List<SubscriptionAction> actions;

        public SingleChannelHist() {

        }

        public SingleChannelHist(String channelName, Long channelId,
                List<SubscriptionAction> actions) {
            this.channelName = channelName;
            this.channelId = channelId;
            this.actions = actions;
        }

        public String getChannelName() {
            return channelName;
        }

        public Long getChannelId() {
            return channelId;
        }

        public List<SubscriptionAction> getActions() {
            return actions;
        }
    }
}
