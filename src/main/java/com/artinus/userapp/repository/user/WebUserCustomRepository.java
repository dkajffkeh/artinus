package com.artinus.userapp.repository.user;

import static com.artinus.userapp.domain.entity.channel.QChannel.channel;
import static com.artinus.userapp.domain.entity.subscription.QSubscriptionActionHist.subscriptionActionHist;
import static com.artinus.userapp.domain.entity.user.QWebUser.webUser;

import com.artinus.userapp.domain.entity.subscription.QSubscriptionActionHist;
import com.artinus.userapp.domain.entity.user.WebUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class WebUserCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public WebUserCustomRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public WebUser findByPhoneNumberHistFetched(String phoneNumber) {
        return jpaQueryFactory.selectFrom(webUser)
                .leftJoin(webUser.actionHists, subscriptionActionHist).fetchJoin()
                .where(webUser.phoneNumber.eq(phoneNumber))
                .fetchOne();
    }
}
