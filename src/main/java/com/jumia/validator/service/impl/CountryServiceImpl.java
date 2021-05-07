package com.jumia.validator.service.impl;

import com.jumia.validator.enums.CountryEnum;
import com.jumia.validator.service.CountryService;
import com.jumia.validator.service.util.PhoneNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

    /**
     * Get a country from phone number.
     *
     * @param phoneNumber the phone number
     * @return a country enum
     */
    @Override
    public CountryEnum getCountryByPhoneNumber(String phoneNumber) {
        String countryCode = PhoneNumberUtil.getCountryCode(phoneNumber);
        return CountryEnum.findByPhoneCode(countryCode);
    }

    /**
     * Get all listed countries.
     *
     * @return a list of countries
     */
    @Override
    public List<String> findAll() {
        return Stream.of(CountryEnum.values())
                .map(CountryEnum::getName)
                .collect(Collectors.toList());
    }
}
