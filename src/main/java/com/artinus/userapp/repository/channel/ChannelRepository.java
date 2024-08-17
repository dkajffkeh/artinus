package com.artinus.userapp.repository.channel;


import com.artinus.userapp.domain.entity.channel.Channel;
import org.springframework.data.repository.Repository;

public interface ChannelRepository extends Repository<Channel, Long> {

    Channel save(Channel channel);

    Channel findById(Long id);
}
