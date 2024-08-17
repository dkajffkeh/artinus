package com.artinus.userapp.service.subscription;

import static com.artinus.userapp.constant.ChannelType.CANCELLATION_ONLY;
import static com.artinus.userapp.exception.code.ArtinusErrorCode.CANCEL_ONLY;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.artinus.userapp.constant.ChannelType;
import com.artinus.userapp.constant.SubscriptionStatus;
import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.exception.ArtinusException;
import com.artinus.userapp.facade.channel.ChannelFacade;
import com.artinus.userapp.facade.subscription.SubscriptionMstFacade;
import com.artinus.userapp.facade.user.WebUserFacade;
import com.artinus.userapp.payload.request.SubscribeRequest;
import java.awt.print.Pageable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SubscriptionHandlerTest {

    @Mock
    private WebUserFacade webUserFacade;

    @Mock
    private ChannelFacade channelFacade;

    @Mock
    private SubscriptionMstFacade subscriptionMstFacade;

    @InjectMocks
    private SubscriptionHandler subscriptionHandler;

    @BeforeEach
    void mockSetUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("구독 취소만 가능한 채널을 구독하려 할때는 Exception 이 발생한다.")
    void subscribeFirstErrorTest() {
        SubscribeRequest request = new SubscribeRequest("01012341234", 1L, SubscriptionStatus.REGULAR_SUBSCRIPTION);
        WebUser webUser = new WebUser(1L, "01012341234");
        Channel channel = new Channel(1L, "쿠팡", CANCELLATION_ONLY);

        when(webUserFacade.findByPhoneNumber(anyString())).thenReturn(webUser);
        when(channelFacade.findById(anyLong())).thenReturn(channel);

        ArtinusException exception = assertThrows(ArtinusException.class, () -> {
           subscriptionHandler.subscribe(request);
        });

        assertEquals(exception.getMessage(), CANCEL_ONLY.getMessage());
    }

    @Test
    @DisplayName("구독 취소만 가능한 케이스 이지만 구독 안함 케이스는 통과 된다.")
    void subscribeNotSubscribedTest() {
        SubscribeRequest request = new SubscribeRequest("01012341234", 1L, SubscriptionStatus.NOT_SUBSCRIBED);
        WebUser webUser = new WebUser(1L, "01012341234");
        Channel channel = new Channel(1L, "쿠팡", CANCELLATION_ONLY);

        when(webUserFacade.findByPhoneNumber(anyString())).thenReturn(webUser);
        when(channelFacade.findById(anyLong())).thenReturn(channel);

        assertDoesNotThrow(() -> subscriptionHandler.subscribe(request));

    }

}
