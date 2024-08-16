package com.artinus.userapp.facade.channel;

import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.repository.channel.ChannelRepository;
import org.springframework.stereotype.Service;

@Service
public class ChannelFacade {

    private final ChannelRepository channelRepository;

    public ChannelFacade(
            ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public Channel save(Channel channel) {
        return channelRepository.save(channel);
    }

}
