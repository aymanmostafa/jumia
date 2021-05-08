package com.jumia.validator.service.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberUtilTest {

    @Test
    void getCountryCode_assertValidNumber_returnCode() {
        String number = "(212) 698054317";
        String code = "212";

        String result = PhoneNumberUtil.getCountryCode(number);

        assertEquals(code, result);
    }

    @Test
    void getCountryCode_assertInValidNumber_returnNull() {
        String number = "(212 698054317";

        String result = PhoneNumberUtil.getCountryCode(number);

        assertNull(result);
    }

    @Test
    void getCountryCode_assertNullNumber_returnNull() {
        String number = null;

        String result = PhoneNumberUtil.getCountryCode(number);

        assertNull(result);
    }
}
