package com.artinus.userapp.facade.subscription;

import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.domain.entity.subscription.SubscriptionMst;
import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.repository.subscription.SubscriptionMstCustomRepository;
import com.artinus.userapp.repository.subscription.SubscriptionMstRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionMstFacade {

    private final SubscriptionMstRepository subscriptionMstRepository;

    private final SubscriptionMstCustomRepository customRepository;

    public SubscriptionMstFacade(
            SubscriptionMstRepository subscriptionMstRepository,
            SubscriptionMstCustomRepository customRepository) {
        this.subscriptionMstRepository = subscriptionMstRepository;
        this.customRepository = customRepository;
    }

    public SubscriptionMst save(SubscriptionMst subscriptionMst) {
        return subscriptionMstRepository.save(subscriptionMst);
    }

    public SubscriptionMst findByChannelAndUser(WebUser webUser, Channel channel) {
        return customRepository.findByChannelAndUser(webUser, channel);
    }
}
