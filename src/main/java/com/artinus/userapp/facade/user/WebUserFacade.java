package com.artinus.userapp.facade.user;

import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.repository.user.WebUserRepository;
import org.springframework.stereotype.Service;

@Service
public class WebUserFacade {

    public WebUserFacade(
            WebUserRepository webUserRepository) {
        this.webUserRepository = webUserRepository;
    }

    private final WebUserRepository webUserRepository;

    public WebUser save(WebUser webUser) {
        return webUserRepository.save(webUser);
    }

}
