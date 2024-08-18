package com.artinus.userapp.service.subscription;

import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.facade.user.WebUserFacade;
import com.artinus.userapp.payload.response.SubscriptionHistResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class SubscriptionHistHandler {

    private final WebUserFacade webUserFacade;

    public SubscriptionHistHandler(WebUserFacade webUserFacade) {
        this.webUserFacade = webUserFacade;
    }

    @Transactional(readOnly = true)
    public SubscriptionHistResponse getSubscriptionHists(String phoneNumber) {
        WebUser webUser = webUserFacade.findByPhoneNumberHistFetched(phoneNumber);
        if(ObjectUtils.isEmpty(webUser)) return SubscriptionHistResponse.emptyOf();
        return webUser.histResponse();
    }

}
