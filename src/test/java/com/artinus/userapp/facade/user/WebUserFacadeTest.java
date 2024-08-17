package com.artinus.userapp.facade.user;

import static org.junit.jupiter.api.Assertions.*;

import com.artinus.userapp.exception.ArtinusException;
import com.artinus.userapp.facade.user.stub.repository.NullReturnWebUserRepositoryStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WebUserFacadeTest {

    private final NullReturnWebUserRepositoryStub repositoryStub = new NullReturnWebUserRepositoryStub();

    private final WebUserFacade webUserFacade = new WebUserFacade(repositoryStub, null);

    @Test
    @DisplayName("사용자를 찾지 못했을 경우 USER NOT FOUND Exception 을 발생 한다.")
    void findFirstByPhoneNumberTest() {
        assertThrows(ArtinusException.class, () -> {
            webUserFacade.findByPhoneNumber("01012341234");
        });
    }
}
