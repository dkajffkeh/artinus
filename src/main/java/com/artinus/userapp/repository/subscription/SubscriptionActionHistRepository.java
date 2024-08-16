package com.artinus.userapp.repository.subscription;

import com.artinus.userapp.domain.entity.subscription.SubscriptionActionHist;
import org.springframework.data.repository.Repository;

public interface SubscriptionActionHistRepository extends Repository<SubscriptionActionHist, Long> {

    SubscriptionActionHist save(SubscriptionActionHist subscriptionActionHist);
}
