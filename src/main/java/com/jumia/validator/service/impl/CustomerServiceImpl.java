package com.jumia.validator.service.impl;

import com.jumia.validator.domain.dto.CustomerDTO;
import com.jumia.validator.domain.dto.CustomerFilterDTO;
import com.jumia.validator.domain.entity.Customer;
import com.jumia.validator.mapper.CustomerMapper;
import com.jumia.validator.repository.CustomerRepository;
import com.jumia.validator.service.CountryService;
import com.jumia.validator.service.CustomerService;
import com.jumia.validator.service.PhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CountryService countryService;
    private PhoneService phoneService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper,
                               CountryService countryService, PhoneService phoneService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.countryService = countryService;
        this.phoneService = phoneService;
    }

    /**
     * Get all the customers.
     *
     * @param customerFilterDTO the filters information
     * @return the list of customers
     */
    @Override
    public List<CustomerDTO> findAll(CustomerFilterDTO customerFilterDTO) {
        log.info("Request to get a list of customers with filters {}", customerFilterDTO);

        List<CustomerDTO> customers = getCustomersDTO(customerRepository.findAll());
        fillCustomerFields(customers);
        customers = filterCustomersList(customers, customerFilterDTO);

        return customers;
    }

    private void fillCustomerFields(List<CustomerDTO> customers) {
        fillCustomersCountries(customers);
        fillCustomersPhoneNumbersState(customers);
    }

    private List<CustomerDTO> getCustomersDTO(List<Customer> customers) {
        return customerMapper.toDto(customers);
    }

    private void fillCustomersCountries(List<CustomerDTO> customers) {
        customers.stream()
                .map(customer -> {
                    String countryName = countryService.getCountryByPhoneNumber(customer.getPhone()).getName();
                    customer.setCountry(countryName);
                    return customer;
                }).collect(Collectors.toList());
    }

    private void fillCustomersPhoneNumbersState(List<CustomerDTO> customers) {
        customers.stream()
                .map(customer -> {
                    customer.setPhoneNumberState(phoneService.getPhoneNumberState(customer.getPhone(), customer.getCountry()));
                    return customer;
                }).collect(Collectors.toList());
    }

    private List<CustomerDTO> filterCustomersList(List<CustomerDTO> customers, CustomerFilterDTO customerFilterDTO) {
        return customers.stream()
                .filter(getCountryFilterPredicate(customerFilterDTO))
                .filter(getStateFilterPredicate(customerFilterDTO))
                .collect(Collectors.toList());
    }

    private Predicate<CustomerDTO> getStateFilterPredicate(CustomerFilterDTO customerFilterDTO) {
        return customer -> customerFilterDTO.getState() == null || customer.getPhoneNumberState().equals(customerFilterDTO.getState());
    }

    private Predicate<CustomerDTO> getCountryFilterPredicate(CustomerFilterDTO customerFilterDTO) {
        return customer -> StringUtils.isEmpty(customerFilterDTO.getCountry()) || StringUtils.equalsIgnoreCase(customer.getCountry(), customerFilterDTO.getCountry());
    }
}
