package com.artinus.facade.subscription;

import com.artinus.userapp.domain.entity.subscription.SubscriptionMst;
import com.artinus.userapp.repository.subscription.SubscriptionMstRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionMstFacade {

    public SubscriptionMstFacade(
            SubscriptionMstRepository subscriptionMstRepository) {
        this.subscriptionMstRepository = subscriptionMstRepository;
    }

    private final SubscriptionMstRepository subscriptionMstRepository;

}
