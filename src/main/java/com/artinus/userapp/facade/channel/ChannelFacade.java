package com.artinus.userapp.facade.channel;

import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.exception.ArtinusException;
import com.artinus.userapp.exception.code.ArtinusErrorCode;
import com.artinus.userapp.repository.channel.ChannelRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    public Channel findById(Long id) {
        Channel channel = channelRepository.findById(id);
        if(ObjectUtils.isEmpty(channel)) {
            throw new ArtinusException(ArtinusErrorCode.CHANNEL_NOT_FOUND);
        }
        return channel;
    }
}
