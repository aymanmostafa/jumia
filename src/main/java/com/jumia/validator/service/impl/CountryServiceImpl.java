package com.jumia.validator.service.impl;

import com.jumia.validator.enums.CountryEnum;
import com.jumia.validator.service.CountryService;
import com.jumia.validator.service.util.PhoneNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

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

    /**
     * Get Country by its name
     *
     * @param name the country name
     * @return the country enum, null if not found
     */
    @Override
    public CountryEnum findByName(String name) {
        return Stream.of(CountryEnum.values())
                .filter(country -> StringUtils.equalsIgnoreCase(country.getName(), name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get a country from phone number.
     *
     * @param phoneNumber the phone number
     * @return a country enum, null if not found
     */
    @Override
    public CountryEnum findByPhoneNumber(String phoneNumber) {
        String countryCode = PhoneNumberUtil.getCountryCode(phoneNumber);
        return getCountryByPhoneCode(countryCode);
    }

    private CountryEnum getCountryByPhoneCode(String phoneCode) {
        return Stream.of(CountryEnum.values())
                .filter(country -> StringUtils.equals(country.getPhoneCode(), phoneCode))
                .findFirst()
                .orElse(null);
    }
}
