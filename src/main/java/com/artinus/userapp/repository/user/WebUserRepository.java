package com.artinus.userapp.repository.user;

import com.artinus.userapp.domain.entity.user.WebUser;
import org.springframework.data.repository.Repository;

public interface WebUserRepository extends Repository<WebUser, Long> {

    WebUser save(WebUser webUser);

    WebUser findFirstByPhoneNumber(String phoneNumber);
}
