package com.artinus.userapp.api.channel;

import com.artinus.userapp.payload.ApiResult;
import com.artinus.userapp.payload.response.ChannelHistResponse;
import com.artinus.userapp.service.channel.ChannelHistHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/channels")
public class ChannelHistController {

    private final ChannelHistHandler channelHistHandler;

    public ChannelHistController(ChannelHistHandler channelHistHandler) {
        this.channelHistHandler = channelHistHandler;
    }

    @GetMapping
    public ApiResult<ChannelHistResponse> getChannelHists(
            @RequestParam("date") String date,
            @RequestParam("channelId") Long channelId
    ) {
        return ApiResult.succeed(channelHistHandler.getChannelHists(date, channelId));
    }
}
