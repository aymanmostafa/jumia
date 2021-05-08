package com.jumia.validator.service.impl;

import com.jumia.validator.enums.CountryEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class CountryServiceImplTest {

    private CountryServiceImpl countryService;
    private CountryServiceImpl countryServiceSpy;

    @BeforeEach
    void init() {
        countryService = new CountryServiceImpl();
        countryServiceSpy = Mockito.spy(countryService);
    }

    @Test
    void findAll_assertValidList() {
        int expSize = CountryEnum.values().length;

        List<String> result = countryServiceSpy.findAll();

        assertNotNull(result);
        assertEquals(expSize, result.size());
    }

    @Test
    void findByName_assertValidName_returnCorrectEnum() {
        CountryEnum[] countries = CountryEnum.values();
        CountryEnum country = countries[0];

        CountryEnum result = countryServiceSpy.findByName(country.getName());

        assertNotNull(result);
        assertEquals(country, result);
    }

    @Test
    void findByName_assertInValidName_returnNull() {
        String name = "";

        CountryEnum result = countryServiceSpy.findByName(name);

        assertNull(result);
    }

    @Test
    void findByName_assertNullName_returnNull() {
        String name = null;

        CountryEnum result = countryServiceSpy.findByName(name);

        assertNull(result);
    }

    @Test
    void findByPhoneNumber_assertValidPhoneNumber_returnCorrectEnum() {
        CountryEnum[] countries = CountryEnum.values();
        CountryEnum country = countries[0];
        String phoneNumber = "123";

        doReturn(country.getPhoneCode()).when(countryServiceSpy).getCountryCode(phoneNumber);
        doReturn(country).when(countryServiceSpy).getCountryByPhoneCode(country.getPhoneCode());
        CountryEnum result = countryServiceSpy.findByPhoneNumber(phoneNumber);

        assertNotNull(result);
        assertEquals(country, result);
    }

    @Test
    void findByPhoneNumber_assertInValidPhoneNumber_returnNull() {
        CountryEnum[] countries = CountryEnum.values();
        CountryEnum country = countries[0];
        String phoneNumber = "123";

        doReturn(country.getPhoneCode()).when(countryServiceSpy).getCountryCode(phoneNumber);
        doReturn(null).when(countryServiceSpy).getCountryByPhoneCode(country.getPhoneCode());
        CountryEnum result = countryServiceSpy.findByPhoneNumber(phoneNumber);

        assertNull(result);
    }

    @Test
    void findByPhoneNumber_assertNullPhoneNumber_returnNull() {
        CountryEnum[] countries = CountryEnum.values();
        CountryEnum country = countries[0];
        String phoneNumber = null;

        doReturn(country.getPhoneCode()).when(countryServiceSpy).getCountryCode(phoneNumber);
        doReturn(null).when(countryServiceSpy).getCountryByPhoneCode(country.getPhoneCode());
        CountryEnum result = countryServiceSpy.findByPhoneNumber(phoneNumber);

        assertNull(result);
    }

    @Test
    void getCountryByPhoneCode_assertNullPhoneCode_returnNull() {
        String phoneCode = null;

        CountryEnum result = countryServiceSpy.getCountryByPhoneCode(phoneCode);

        assertNull(result);
    }

    @Test
    void getCountryByPhoneCode_assertValidPhoneCode_returnCorrectCountry() {
        CountryEnum[] countries = CountryEnum.values();
        CountryEnum country = countries[0];

        CountryEnum result = countryServiceSpy.getCountryByPhoneCode(country.getPhoneCode());

        assertNotNull(result);
        assertEquals(country, result);
    }
}
