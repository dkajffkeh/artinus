package com.artinus.userapp.facade.user.stub.repository;

import com.artinus.userapp.domain.entity.user.WebUser;
import com.artinus.userapp.repository.user.WebUserRepository;

public class NullReturnWebUserRepositoryStub implements WebUserRepository {

    @Override
    public WebUser save(WebUser webUser) {
        return null;
    }

    @Override
    public WebUser findFirstByPhoneNumber(String phoneNumber) {
        return null;
    }

}
