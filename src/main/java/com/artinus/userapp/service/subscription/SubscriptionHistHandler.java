package com.artinus.userapp.service.subscription;

import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.facade.user.WebUserFacade;
import com.artinus.userapp.payload.response.SubscriptionHistResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionHistHandler {

    private final WebUserFacade webUserFacade;

    public SubscriptionHistHandler(WebUserFacade webUserFacade) {
        this.webUserFacade = webUserFacade;
    }

    @Transactional(readOnly = true)
    public SubscriptionHistResponse getSubscriptionHists(String phoneNumber) {
        return webUserFacade.findByPhoneNumberHistFetched(phoneNumber).histResponse();
    }

}
