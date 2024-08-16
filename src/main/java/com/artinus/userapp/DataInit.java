package com.artinus.userapp;

import static com.artinus.userapp.constant.ChannelType.BOTH;
import static com.artinus.userapp.constant.ChannelType.CANCELLATION_ONLY;
import static com.artinus.userapp.constant.ChannelType.SUBSCRIPTION_ONLY;

import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.facade.channel.ChannelFacade;
import com.artinus.userapp.facade.user.WebUserFacade;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataInit {

    private final ChannelFacade channelFacade;
    private final WebUserFacade webUserFacade;

    public DataInit(ChannelFacade channelFacade,
            WebUserFacade webUserFacade) {
        this.channelFacade = channelFacade;
        this.webUserFacade = webUserFacade;
    }

    @Transactional
    @PostConstruct
    public void initData() {
        WebUser webUser1 = webUserFacade.save(new WebUser("01012341234"));
        WebUser webUser2 = webUserFacade.save(new WebUser("01012341235"));

        Channel channel1 = channelFacade.save(new Channel("워크", "WERK", BOTH));
        Channel channel2 = channelFacade.save(new Channel("아임웹", "IMWEB", SUBSCRIPTION_ONLY));
        Channel channel3 = channelFacade.save(new Channel("스팀", "STEAM", CANCELLATION_ONLY));


    }

}
