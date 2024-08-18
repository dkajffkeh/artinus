package com.artinus.userapp.facade.user;

import static com.artinus.userapp.exception.code.ArtinusErrorCode.USER_NOT_FOUND;

import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.exception.ArtinusException;
import com.artinus.userapp.repository.user.WebUserCustomRepository;
import com.artinus.userapp.repository.user.WebUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class WebUserFacade {

    private final WebUserRepository webUserRepository;

    private final WebUserCustomRepository customRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public WebUserFacade(WebUserRepository webUserRepository,
            WebUserCustomRepository customRepository) {
        this.webUserRepository = webUserRepository;
        this.customRepository = customRepository;
    }

    public WebUser save(WebUser webUser) {
        return webUserRepository.save(webUser);
    }

    public WebUser findByPhoneNumber(String phoneNumber) {
        WebUser webUser = webUserRepository.findFirstByPhoneNumber(phoneNumber);
        if(ObjectUtils.isEmpty(webUser)) {
            log.warn("USER_NOT_FOUND occur request phone number : {}", phoneNumber);
            throw new ArtinusException(USER_NOT_FOUND);
        }
        return webUser;
    }

    public WebUser findByPhoneNumberHistFetched(String phoneNumber) {
        return customRepository.findByPhoneNumberHistFetched(phoneNumber);
    }
}
