package com.artinus.userapp.repository.subscription;

import com.artinus.userapp.domain.entity.subscription.SubscriptionMst;
import org.springframework.data.repository.Repository;

public interface SubscriptionMstRepository extends Repository<SubscriptionMst, Long> {

    SubscriptionMst save(SubscriptionMst subscriptionMst);

}
