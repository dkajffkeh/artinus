package com.artinus.userapp.service.channel;

import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.facade.channel.ChannelFacade;
import com.artinus.userapp.payload.response.ChannelHistResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class ChannelHistHandler {

    private final ChannelFacade channelFacade;

    public ChannelHistHandler(ChannelFacade channelFacade) {
        this.channelFacade = channelFacade;
    }

    @Transactional(readOnly = true)
    public ChannelHistResponse getChannelHists(String date, Long channelId) {
        Channel channel = channelFacade.findByIdHistFetched(channelId, date);

        if(ObjectUtils.isEmpty(channel)) {
            return ChannelHistResponse.emptyOf(date, channelId);
        }

        return channel.toChannelHistResponse(date);
    }

}
