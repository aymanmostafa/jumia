package com.jumia.validator.service.util;

import org.apache.commons.lang3.StringUtils;

public final class PhoneNumberUtil {

    private static final String OPEN_BRACKETS = "(";
    private static final String CLOSED_BRACKETS = ")";

    private PhoneNumberUtil(){}

    /**
     * Get country code using a phone number
     *
     * @param number the phone number
     * @return the country code, null if not found
     */
    public static String getCountryCode(String number) {
        return StringUtils.substringBetween(number, OPEN_BRACKETS, CLOSED_BRACKETS);
    }
}
