package com.artinus.userapp.global.utils;

import static com.artinus.userapp.global.utils.DateTimeUtils.toyyyyMMdd;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DateTimeUtilsTest {

    @Test
    void toyyyyMMddTest() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 3, 27, 0, 0, 0);
        Assertions.assertEquals("20240327", toyyyyMMdd(localDateTime));
    }

}
