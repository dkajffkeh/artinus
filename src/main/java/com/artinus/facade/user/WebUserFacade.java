package com.artinus.facade.user;

import com.artinus.userapp.repository.user.WebUserRepository;
import org.springframework.stereotype.Service;

@Service
public class WebUserFacade {

    public WebUserFacade(
            WebUserRepository webUserRepository) {
        this.webUserRepository = webUserRepository;
    }

    private final WebUserRepository webUserRepository;


}
