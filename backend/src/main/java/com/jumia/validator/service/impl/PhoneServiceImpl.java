package com.jumia.validator.service.impl;

import com.jumia.validator.enums.CountryEnum;
import com.jumia.validator.enums.StateEnum;
import com.jumia.validator.service.CountryService;
import com.jumia.validator.service.PhoneService;
import com.jumia.validator.service.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    private CountryService countryService;

    public PhoneServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Get the phone number validation state.
     *
     * @param phoneNumber the phone number
     * @param country the country of the phone number
     * @return a validation enum
     */
    @Override
    public StateEnum getPhoneNumberState(String phoneNumber, String country) {
        CountryEnum countryEnum = countryService.findByName(country);
        if(countryEnum == null) {
            return StateEnum.INVALID;
        }
        return validatePhoneNumber(phoneNumber, countryEnum);
    }

    StateEnum validatePhoneNumber(String phoneNumber, CountryEnum countryEnum) {
        String phoneNumberRegex = countryEnum.getPhoneRegex();
        return RegexUtil.validateRegex(phoneNumber, phoneNumberRegex);
    }

    /**
     * Get all listed phone number states.
     *
     * @return a list of phone number states
     */
    @Override
    public List<String> findAll() {
        return Stream.of(StateEnum.values())
                .map(StateEnum::name)
                .collect(Collectors.toList());
    }
}
