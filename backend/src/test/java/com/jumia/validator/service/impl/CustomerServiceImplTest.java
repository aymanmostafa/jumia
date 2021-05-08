package com.jumia.validator.service.impl;

import com.jumia.validator.domain.dto.CustomerDTO;
import com.jumia.validator.domain.dto.CustomerFilterDTO;
import com.jumia.validator.domain.entity.Customer;
import com.jumia.validator.enums.CountryEnum;
import com.jumia.validator.enums.StateEnum;
import com.jumia.validator.mapper.CustomerMapper;
import com.jumia.validator.repository.CustomerRepository;
import com.jumia.validator.service.CountryService;
import com.jumia.validator.service.PhoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CustomerServiceImplTest {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CountryService countryService;
    private PhoneService phoneService;
    private CustomerServiceImpl customerService;
    private CustomerServiceImpl customerServiceSpy;

    @BeforeEach
    void init() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerMapper = Mockito.mock(CustomerMapper.class);
        countryService = Mockito.mock(CountryService.class);
        phoneService = Mockito.mock(PhoneService.class);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper, countryService, phoneService);
        customerServiceSpy = Mockito.spy(customerService);
    }

    @Test
    void findAll_assertValidList() {
        CustomerFilterDTO customerFilterDTO = CustomerFilterDTO.builder().country("country").state(StateEnum.VALID).build();
        List<CustomerDTO> customers = getCustomersList();

        doReturn(customers).when(customerServiceSpy).getCustomersDTO(anyList());
        doNothing().when(customerServiceSpy).fillCustomerFields(customers);
        doReturn(customers).when(customerServiceSpy).filterCustomersList(customers, customerFilterDTO);
        List<CustomerDTO> result = customerServiceSpy.findAll(customerFilterDTO);

        assertNotNull(result);
        assertEquals(customers.size(), result.size());
        assertEquals(customers, result);
    }

    @Test
    void findAll_assertValidListWithNullFilters() {
        CustomerFilterDTO customerFilterDTO = null;
        List<CustomerDTO> customers = getCustomersList();

        doReturn(customers).when(customerServiceSpy).getCustomersDTO(anyList());
        doNothing().when(customerServiceSpy).fillCustomerFields(customers);
        doReturn(customers).when(customerServiceSpy).filterCustomersList(customers, customerFilterDTO);
        List<CustomerDTO> result = customerServiceSpy.findAll(customerFilterDTO);

        assertNotNull(result);
        assertEquals(customers.size(), result.size());
        assertEquals(customers, result);
    }

    @Test
    void fillCustomerFields_assertValidCalling() {
        List<CustomerDTO> customers = getCustomersList();

        doNothing().when(customerServiceSpy).fillCustomersCountries(customers);
        doNothing().when(customerServiceSpy).fillCustomersPhoneNumbersState(customers);
        customerServiceSpy.fillCustomerFields(customers);

        verify(customerServiceSpy, times(1)).fillCustomersCountries(customers);
        verify(customerServiceSpy, times(1)).fillCustomersPhoneNumbersState(customers);
    }

    @Test
    void getCustomersDTO_assertValidCalling() {
        List<CustomerDTO> customersDTO = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        doReturn(customersDTO).when(customerMapper).toDto(customers);
        List<CustomerDTO> result = customerServiceSpy.getCustomersDTO(customers);

        verify(customerMapper, times(1)).toDto(customers);
    }

    @Test
    void fillCustomersCountries_assertValidCountry() {
        List<CustomerDTO> customers = getCustomersList();
        int customersSize = customers.size();
        CountryEnum[] countries = CountryEnum.values();
        CountryEnum country = countries[0];

        doReturn(country).when(countryService).findByPhoneNumber(any());
        customerServiceSpy.fillCustomersCountries(customers);

        verify(customerServiceSpy, times(customersSize)).getCustomerCountry(any());
        customers.forEach(customer -> assertEquals(country.getName(),customer.getCountry()));
    }

    @Test
    void fillCustomersCountries_assertInValidCountry() {
        List<CustomerDTO> customers = getCustomersList();
        int customersSize = customers.size();

        doReturn(null).when(countryService).findByPhoneNumber(any());
        customerServiceSpy.fillCustomersCountries(customers);

        verify(customerServiceSpy, times(customersSize)).getCustomerCountry(any());
        customers.forEach(customer -> assertNull( customer.getCountry()));
    }

    @Test
    void fillCustomersPhoneNumbersState_assertValidCountry() {
        List<CustomerDTO> customers = getCustomersList();
        int customersSize = customers.size();

        doReturn(StateEnum.VALID).when(phoneService).getPhoneNumberState(any(), any());
        customerServiceSpy.fillCustomersPhoneNumbersState(customers);

        verify(customerServiceSpy, times(customersSize)).getCustomerPhoneNumberState(any());
        customers.forEach(customer -> assertEquals(StateEnum.VALID,customer.getPhoneNumberState()));
    }

    @Test
    void fillCustomersPhoneNumbersState_assertInValidCountry() {
        List<CustomerDTO> customers = getCustomersList();
        int customersSize = customers.size();

        doReturn(StateEnum.INVALID).when(phoneService).getPhoneNumberState(any(), any());
        customerServiceSpy.fillCustomersPhoneNumbersState(customers);

        verify(customerServiceSpy, times(customersSize)).getCustomerPhoneNumberState(any());
        customers.forEach(customer -> assertEquals(StateEnum.INVALID,customer.getPhoneNumberState()));
    }

    @Test
    void filterCustomersList_assertCountryFilter() {
        String country = "Cameroon";
        List<CustomerDTO> customers = prepareFilterCustomerList();
        CustomerFilterDTO customerFilterDTO = CustomerFilterDTO.builder().country(country).build();

        List<CustomerDTO> result = customerServiceSpy.filterCustomersList(customers, customerFilterDTO);

        verify(customerServiceSpy, times(1)).getStateFilterPredicate(customerFilterDTO);
        verify(customerServiceSpy, times(1)).getCountryFilterPredicate(customerFilterDTO);
        assertEquals(2, result.size());
    }

    @Test
    void filterCustomersList_assertStateFilter() {
        List<CustomerDTO> customers = prepareFilterCustomerList();
        CustomerFilterDTO customerFilterDTO = CustomerFilterDTO.builder().state(StateEnum.VALID).build();

        List<CustomerDTO> result = customerServiceSpy.filterCustomersList(customers, customerFilterDTO);

        verify(customerServiceSpy, times(1)).getStateFilterPredicate(customerFilterDTO);
        verify(customerServiceSpy, times(1)).getCountryFilterPredicate(customerFilterDTO);
        assertEquals(1, result.size());
    }

    @Test
    void filterCustomersList_assertStateAndCountryFilter() {
        String country = "Cameroon";
        List<CustomerDTO> customers = prepareFilterCustomerList();
        CustomerFilterDTO customerFilterDTO = CustomerFilterDTO.builder().state(StateEnum.INVALID).country(country).build();

        List<CustomerDTO> result = customerServiceSpy.filterCustomersList(customers, customerFilterDTO);

        verify(customerServiceSpy, times(1)).getStateFilterPredicate(customerFilterDTO);
        verify(customerServiceSpy, times(1)).getCountryFilterPredicate(customerFilterDTO);
        assertEquals(1, result.size());
    }

    @Test
    void filterCustomersList_assertEmptyFilter() {
        List<CustomerDTO> customers = prepareFilterCustomerList();
        CustomerFilterDTO customerFilterDTO = new CustomerFilterDTO();

        List<CustomerDTO> result = customerServiceSpy.filterCustomersList(customers, customerFilterDTO);

        verify(customerServiceSpy, times(1)).getStateFilterPredicate(customerFilterDTO);
        verify(customerServiceSpy, times(1)).getCountryFilterPredicate(customerFilterDTO);
        assertEquals(2, result.size());
    }

    private List<CustomerDTO> prepareFilterCustomerList() {
        String country = "Cameroon";
        List<CustomerDTO> customers = getCustomersList();
        customers.get(0).setCountry(country);
        customers.get(0).setPhoneNumberState(StateEnum.VALID);
        customers.get(1).setCountry(country);
        customers.get(1).setPhoneNumberState(StateEnum.INVALID);

        return customers;
    }

    private List<CustomerDTO> getCustomersList() {
        List<CustomerDTO> customers = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            CustomerDTO customerDTO = CustomerDTO.builder().id(Long.valueOf(i)).name("name").phone("(212) 6007989253").build();
            customers.add(customerDTO);
        }
        return customers;
    }

    private CustomerDTO getCustomerDTO() {
        return CustomerDTO.builder().id(0L).name("name").phone("(212) 6007989253").build();
    }
}
