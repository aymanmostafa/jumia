package com.jumia.validator.service;

import com.jumia.validator.enums.CountryEnum;

import java.util.List;

public interface CountryService {

    /**
     * Get a country from phone number.
     *
     * @param phoneNumber the phone number
     * @return a country enum
     */
    CountryEnum getCountryByPhoneNumber(String phoneNumber);

    /**
     * Get all listed countries.
     *
     * @return a list of countries
     */
    List<String> findAll();
}
