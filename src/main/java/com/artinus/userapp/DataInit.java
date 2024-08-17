package com.artinus.userapp;

import static com.artinus.userapp.constant.ChannelType.BOTH;
import static com.artinus.userapp.constant.ChannelType.CANCELLATION_ONLY;
import static com.artinus.userapp.constant.ChannelType.SUBSCRIPTION_ONLY;
import static com.artinus.userapp.constant.SubscriptionStatus.REGULAR_SUBSCRIPTION;

import com.artinus.userapp.constant.SubscriptionStatus;
import com.artinus.userapp.domain.entity.channel.Channel;
import com.artinus.userapp.domain.entity.subscription.SubscriptionMst;
import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.facade.channel.ChannelFacade;
import com.artinus.userapp.facade.subscription.SubscriptionMstFacade;
import com.artinus.userapp.facade.user.WebUserFacade;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataInit {

    private final ChannelFacade channelFacade;

    private final WebUserFacade webUserFacade;

    private final SubscriptionMstFacade subscriptionMstFacade;

    public DataInit(ChannelFacade channelFacade,
            WebUserFacade webUserFacade,
            SubscriptionMstFacade subscriptionMstFacade) {
        this.channelFacade = channelFacade;
        this.webUserFacade = webUserFacade;
        this.subscriptionMstFacade = subscriptionMstFacade;
    }


    @Transactional
    @PostConstruct
    public void initData() {
        // 사용자 생성
        WebUser webUser1 = webUserFacade.save(new WebUser("01012341234"));
        WebUser webUser2 = webUserFacade.save(new WebUser("01012341235"));

        // 채널 생성
        Channel channel1 = channelFacade.save(new Channel("워크", BOTH));
        Channel channel2 = channelFacade.save(new Channel("아임웹", SUBSCRIPTION_ONLY));
        Channel channel3 = channelFacade.save(new Channel("스팀", CANCELLATION_ONLY));

    }

}
