package com.artinus.userapp.payload.response;

import static org.junit.jupiter.api.Assertions.*;

import com.artinus.userapp.payload.response.SubscriptionHistResponse.ChannelHist;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SubscriptionHistResponseTest {

    @Test
    @DisplayName("Sort Test")
    void histsSortOrderTest() {
        List<ChannelHist> hists = new ArrayList<>();
        hists.add(new ChannelHist("20230104", new ArrayList<>()));
        hists.add(new ChannelHist("20230712", new ArrayList<>()));
        hists.add(new ChannelHist("20240714", new ArrayList<>()));

        Collections.sort(hists);

        assertEquals("20240714", hists.get(0).getDate() );
        assertEquals("20230712", hists.get(1).getDate() );
        assertEquals("20230104", hists.get(2).getDate() );
    }
}
