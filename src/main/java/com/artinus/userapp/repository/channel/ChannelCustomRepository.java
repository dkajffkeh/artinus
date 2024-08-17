package com.artinus.userapp.repository.channel;

import static com.artinus.userapp.domain.entity.channel.QChannel.channel;

import com.artinus.userapp.domain.entity.channel.Channel;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ChannelCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ChannelCustomRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Channel findByIdHistFetched(Long id) {
        return jpaQueryFactory.selectFrom(channel)
                .leftJoin(channel.actionHists).fetchJoin()
                .where(channel.id.eq(id))
                .fetchFirst();
    }
}
