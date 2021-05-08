package com.jumia.validator.service.impl;

import com.jumia.validator.enums.CountryEnum;
import com.jumia.validator.enums.StateEnum;
import com.jumia.validator.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class PhoneServiceImplTest {

    private PhoneServiceImpl phoneService;
    private PhoneServiceImpl phoneServiceSpy;
    private CountryService countryService;

    @BeforeEach
    void init() {
        countryService = Mockito.mock(CountryService.class);
        phoneService = new PhoneServiceImpl(countryService);
        phoneServiceSpy = Mockito.spy(phoneService);
    }

    @Test
    void findAll_assertValidList() {
        int expSize = StateEnum.values().length;

        List<String> result = phoneServiceSpy.findAll();

        assertNotNull(result);
        assertEquals(expSize, result.size());
    }

    @Test
    void getPhoneNumberState_assertValidPhoneNumberWithValidCountry_returnValidState() {
        CountryEnum[] countries = CountryEnum.values();
        CountryEnum country = countries[0];
        String phoneNumber = "123";

        doReturn(country).when(countryService).findByName(country.getName());
        doReturn(StateEnum.VALID).when(phoneServiceSpy).validatePhoneNumber(phoneNumber, country);
        StateEnum result = phoneServiceSpy.getPhoneNumberState(phoneNumber, country.getName());

        assertNotNull(result);
        assertEquals(StateEnum.VALID, result);
    }

    @Test
    void getPhoneNumberState_assertValidPhoneNumberWithInValidCountry_returnInvalidState() {
        String country = "cou";
        String phoneNumber = "123";

        doReturn(null).when(countryService).findByName(country);
        StateEnum result = phoneServiceSpy.getPhoneNumberState(phoneNumber, country);

        assertNotNull(result);
        assertEquals(StateEnum.INVALID, result);
    }

    @Test
    void getPhoneNumberState_assertNullPhoneNumberWithNullCountry_returnInvalidState() {
        String country = null;
        String phoneNumber = null;

        doReturn(null).when(countryService).findByName(country);
        StateEnum result = phoneServiceSpy.getPhoneNumberState(phoneNumber, country);

        assertNotNull(result);
        assertEquals(StateEnum.INVALID, result);
    }

    @Test
    void getPhoneNumberState_assertNullPhoneNumberWithValidCountry_returnInValidState() {
        CountryEnum[] countries = CountryEnum.values();
        CountryEnum country = countries[0];
        String phoneNumber = "123";

        doReturn(country).when(countryService).findByName(country.getName());
        doReturn(StateEnum.INVALID).when(phoneServiceSpy).validatePhoneNumber(phoneNumber, country);
        StateEnum result = phoneServiceSpy.getPhoneNumberState(phoneNumber, country.getName());

        assertNotNull(result);
        assertEquals(StateEnum.INVALID, result);
    }
}
