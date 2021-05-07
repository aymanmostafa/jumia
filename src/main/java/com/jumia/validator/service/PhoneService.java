package com.jumia.validator.service;

import com.jumia.validator.enums.PhoneNumberStateEnum;

import java.util.List;

public interface PhoneService {

    /**
     * Get the phone number validation state.
     *
     * @param phoneNumber the phone number
     * @param country the country of the phone number
     * @return a validation enum
     */
    PhoneNumberStateEnum getPhoneNumberState(String phoneNumber, String country);

    /**
     * Get all listed phone number states.
     *
     * @return a list of phone number states
     */
    List<String> findAll();
}
