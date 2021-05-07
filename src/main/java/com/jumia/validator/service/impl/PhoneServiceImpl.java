package com.jumia.validator.service.impl;

import com.jumia.validator.enums.CountryEnum;
import com.jumia.validator.enums.PhoneNumberStateEnum;
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

    /**
     * Get the phone number validation state.
     *
     * @param phoneNumber the phone number
     * @param country the country of the phone number
     * @return a validation enum
     */
    @Override
    public PhoneNumberStateEnum getPhoneNumberState(String phoneNumber, String country) {
        String phoneNumberRegex = CountryEnum.findByName(country).getPhoneRegex();
        return RegexUtil.validateRegex(phoneNumber, phoneNumberRegex);
    }

    /**
     * Get all listed phone number states.
     *
     * @return a list of phone number states
     */
    @Override
    public List<String> findAll() {
        return Stream.of(PhoneNumberStateEnum.values())
                .map(PhoneNumberStateEnum::name)
                .collect(Collectors.toList());
    }
}
