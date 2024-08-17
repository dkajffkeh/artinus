package com.artinus.userapp.service.subscription;

import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.domain.entity.subscription.SubscriptionMst;
import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.facade.channel.ChannelFacade;
import com.artinus.userapp.facade.subscription.SubscriptionMstFacade;
import com.artinus.userapp.facade.user.WebUserFacade;
import com.artinus.userapp.payload.request.SubscribeRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionHandler {

    private final WebUserFacade webUserFacade;

    private final ChannelFacade channelFacade;

    private final SubscriptionMstFacade subscriptionMstFacade;

    public SubscriptionHandler(WebUserFacade webUserFacade,
            ChannelFacade channelFacade,
            SubscriptionMstFacade subscriptionMstFacade) {
        this.webUserFacade = webUserFacade;
        this.channelFacade = channelFacade;
        this.subscriptionMstFacade = subscriptionMstFacade;
    }


    @Transactional
    public void subscribe(SubscribeRequest request) {
        WebUser webUser = webUserFacade.findByPhoneNumber(request.getPhoneNumber());
        Channel channel = channelFacade.findById(request.getChannelId());
        SubscriptionMst currentSubscription = subscriptionMstFacade.findByChannelAndUser(webUser, channel);

        webUser.subscribe(channel, currentSubscription, request);

        webUserFacade.save(webUser);
    }
}
