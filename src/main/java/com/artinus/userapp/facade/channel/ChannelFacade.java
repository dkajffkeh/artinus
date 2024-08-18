package com.artinus.userapp.facade.channel;

import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.exception.ArtinusException;
import com.artinus.userapp.exception.code.ArtinusErrorCode;
import com.artinus.userapp.repository.channel.ChannelCustomRepository;
import com.artinus.userapp.repository.channel.ChannelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ChannelFacade {

    private final ChannelRepository channelRepository;

    private final ChannelCustomRepository channelCustomRepository;

    private static final Logger log = LoggerFactory.getLogger(ChannelFacade.class);

    public ChannelFacade(ChannelRepository channelRepository,
            ChannelCustomRepository channelCustomRepository) {
        this.channelRepository = channelRepository;
        this.channelCustomRepository = channelCustomRepository;
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

    public Channel findByIdHistFetched(Long id, String date) {
        return channelCustomRepository.findByIdAndDate(id, date);
    }
}
