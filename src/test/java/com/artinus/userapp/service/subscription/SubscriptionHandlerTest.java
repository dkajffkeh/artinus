package com.artinus.userapp.service.subscription;

import static com.artinus.userapp.constant.ChannelType.BOTH;
import static com.artinus.userapp.constant.ChannelType.CANCELLATION_ONLY;
import static com.artinus.userapp.constant.ChannelType.SUBSCRIPTION_ONLY;
import static com.artinus.userapp.exception.code.ArtinusErrorCode.CANCEL_ONLY;
import static com.artinus.userapp.exception.code.ArtinusErrorCode.NOT_UPPER_SUBSCRIBE_REQUEST;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.artinus.userapp.constant.SubscriptionStatus;
import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.domain.entity.subscription.SubscriptionMst;
import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.exception.ArtinusException;
import com.artinus.userapp.facade.channel.ChannelFacade;
import com.artinus.userapp.facade.subscription.SubscriptionMstFacade;
import com.artinus.userapp.facade.user.WebUserFacade;
import com.artinus.userapp.payload.request.SubscribeRequest;
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

    private final WebUser webUser = new WebUser(1L, "01012341234");

    private final Channel cancelOnly = new Channel(1L, "쿠팡", CANCELLATION_ONLY);
    private final Channel both = new Channel(1L, "쿠팡", BOTH);
    private final Channel subscribeOnly = new Channel(1L, "쿠팡", SUBSCRIPTION_ONLY);

    private final SubscriptionMst initSubscription = null;
    private final SubscriptionMst bothAndNot = new SubscriptionMst(webUser, both, "20240818",
            SubscriptionStatus.NOT_SUBSCRIBED);
    private final SubscriptionMst bothAndRegular = new SubscriptionMst(webUser, both, "20240818",
            SubscriptionStatus.REGULAR_SUBSCRIPTION);
    private final SubscriptionMst bothAndPremium = new SubscriptionMst(webUser, both, "20240818",
            SubscriptionStatus.PREMIUM_SUBSCRIPTION);

    private final SubscriptionMst subOnlyAndNot = new SubscriptionMst(webUser, subscribeOnly,
            "20240818", SubscriptionStatus.NOT_SUBSCRIBED);
    private final SubscriptionMst subOnlyAndRegular = new SubscriptionMst(webUser, subscribeOnly,
            "20240818", SubscriptionStatus.REGULAR_SUBSCRIPTION);
    private final SubscriptionMst subOnlyAndPremium = new SubscriptionMst(webUser, subscribeOnly,
            "20240818", SubscriptionStatus.PREMIUM_SUBSCRIPTION);

    private final SubscriptionMst cancelOnlyAndNot = new SubscriptionMst(webUser, cancelOnly,
            "20240818", SubscriptionStatus.NOT_SUBSCRIBED);
    private final SubscriptionMst cancelOnlyAndRegular = new SubscriptionMst(webUser, cancelOnly,
            "20240818", SubscriptionStatus.REGULAR_SUBSCRIPTION);
    private final SubscriptionMst cancelOnlyAndPremium = new SubscriptionMst(webUser, cancelOnly,
            "20240818", SubscriptionStatus.PREMIUM_SUBSCRIPTION);

    private final SubscribeRequest notReq = new SubscribeRequest("01012341234", 1L,
            SubscriptionStatus.NOT_SUBSCRIBED);
    private final SubscribeRequest regularReq = new SubscribeRequest("01012341234", 1L,
            SubscriptionStatus.REGULAR_SUBSCRIPTION);
    private final SubscribeRequest premiumReq = new SubscribeRequest("01012341234", 1L,
            SubscriptionStatus.PREMIUM_SUBSCRIPTION);

    @BeforeEach
    void mockSetUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("채널 타입 구독, 해지 가능한 케이스 전체 케이스 구독 테스트")
    void subscribeBothTest() {
        SubscribeRequest request = new SubscribeRequest("01012341234", 1L,
                SubscriptionStatus.REGULAR_SUBSCRIPTION);

        webUser.subscribe(both, bothAndNot, premiumReq);
        assertAll("채널 타입 [구독,해지 가능]",
                () -> {
                    ArtinusException exception = assertThrows(ArtinusException.class, () -> {
                        webUser.subscribe(both, bothAndRegular, notReq);
                    });

                    assertEquals(exception.getMessage(), NOT_UPPER_SUBSCRIBE_REQUEST.getMessage());
                },
                () -> assertDoesNotThrow(() -> webUser.subscribe(both, bothAndRegular, premiumReq)),
                // REGULAR -> PREMIUM REQ
                () -> {
                    ArtinusException exception = assertThrows(ArtinusException.class, () -> {
                        webUser.subscribe(both, bothAndPremium, regularReq);
                    });

                    assertEquals(exception.getMessage(), NOT_UPPER_SUBSCRIBE_REQUEST.getMessage());
                }
        );
    }

    @Test
    @DisplayName("구독 취소만 가능한 채널을 구독하려 할때는 Exception 이 발생한다.")
    void subscribeFirstErrorTest() {
        SubscribeRequest request = new SubscribeRequest("01012341234", 1L,
                SubscriptionStatus.REGULAR_SUBSCRIPTION);

        when(webUserFacade.findByPhoneNumber(anyString())).thenReturn(webUser);
        when(channelFacade.findById(anyLong())).thenReturn(cancelOnly);

        ArtinusException exception = assertThrows(ArtinusException.class, () -> {
            subscriptionHandler.subscribe(request);
        });

        assertEquals(exception.getMessage(), CANCEL_ONLY.getMessage());
    }

    @Test
    @DisplayName("구독 취소만 가능한 케이스 이지만 구독 안함 케이스는 통과 된다.")
    void subscribeNotSubscribedTest() {
        SubscribeRequest request = new SubscribeRequest("01012341234", 1L,
                SubscriptionStatus.NOT_SUBSCRIBED);

        when(webUserFacade.findByPhoneNumber(anyString())).thenReturn(webUser);
        when(channelFacade.findById(anyLong())).thenReturn(cancelOnly);

        assertDoesNotThrow(() -> subscriptionHandler.subscribe(request));
    }


}
