package com.jumia.validator.controller;

import com.jumia.validator.domain.dto.CustomerDTO;
import com.jumia.validator.domain.dto.CustomerFilterDTO;
import com.jumia.validator.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    /**
     * GET : get all the filtered customers.
     *
     * @param customerFilterDTO the filters information
     * @return the ResponseEntity with status 200 (OK) and the list of customers in body
     */
    @ApiOperation(value = "get all the filtered customers")
    @GetMapping("")
    public ResponseEntity<List<CustomerDTO>> getCustomers(CustomerFilterDTO customerFilterDTO) {
        log.info("REST request to get a list of customers with filters {}", customerFilterDTO);
        return ResponseEntity.ok(customerService.findAll(customerFilterDTO));
    }
}
