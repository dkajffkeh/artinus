package com.artinus.userapp.repository.subscription;

import static com.artinus.userapp.domain.entity.subscription.QSubscriptionMst.subscriptionMst;

import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.domain.entity.subscription.SubscriptionMst;
import com.artinus.userapp.domain.entity.user.WebUser;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionMstCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public SubscriptionMstCustomRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public SubscriptionMst findByChannelAndUser(WebUser webUser, Channel channel) {
        return jpaQueryFactory.selectFrom(subscriptionMst)
                .where(subscriptionMst.channel.eq(channel),
                        subscriptionMst.webUser.eq(webUser))
                .fetchFirst();
    }
}
