package com.artinus.userapp.service.subscription;

import static com.artinus.userapp.exception.code.ArtinusErrorCode.ALREADY_SUBSCRIBED;

import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.domain.entity.subscription.SubscriptionMst;
import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.exception.ArtinusException;
import com.artinus.userapp.facade.channel.ChannelFacade;
import com.artinus.userapp.facade.subscription.SubscriptionMstFacade;
import com.artinus.userapp.facade.user.WebUserFacade;
import com.artinus.userapp.payload.request.SubscribeRequest;
import com.artinus.userapp.payload.request.UnSubscribeRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionHandler {

    private final WebUserFacade webUserFacade;

    private final ChannelFacade channelFacade;

    private final SubscriptionMstFacade subscriptionMstFacade;

    private static final Logger log = LoggerFactory.getLogger(SubscriptionHandler.class);

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

        try {
            webUserFacade.save(webUser);
        } catch (ConstraintViolationException e) {
            log.warn("subscription action already occurred");
            throw new ArtinusException(ALREADY_SUBSCRIBED);
        } catch (OptimisticLockingFailureException e) {
            log.warn("duplicate action requested");
            throw new ArtinusException(ALREADY_SUBSCRIBED);
        }

    }

    @Transactional
    public void unsubscribe(UnSubscribeRequest request) {
        WebUser webUser = webUserFacade.findByPhoneNumber(request.getPhoneNumber());
        Channel channel = channelFacade.findById(request.getChannelId());
        SubscriptionMst currentSubscription = subscriptionMstFacade.findByChannelAndUser(webUser, channel);

        webUser.unSubscribe(channel, currentSubscription, request);

        try {
            webUserFacade.save(webUser);
        } catch (OptimisticLockingFailureException e) {
            log.warn("duplicate action requested");
            throw new ArtinusException(ALREADY_SUBSCRIBED);
        }
    }
}
