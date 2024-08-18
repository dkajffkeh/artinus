package com.artinus.userapp.repository.channel;

import static com.artinus.userapp.domain.entity.channel.QChannel.channel;
import static com.artinus.userapp.domain.entity.subscription.QSubscriptionActionHist.subscriptionActionHist;

import com.artinus.userapp.domain.entity.channel.Channel;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ChannelCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ChannelCustomRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Channel findByIdAndDate(Long id, String date) {
        return jpaQueryFactory.selectFrom(channel)
                .leftJoin(channel.actionHists, subscriptionActionHist).fetchJoin()
                .leftJoin(subscriptionActionHist.webUser).fetchJoin()
                .where(channel.id.eq(id),
                        subscriptionActionHist.subscriptionActionDate.eq(date))
                .fetchOne();
    }
}
