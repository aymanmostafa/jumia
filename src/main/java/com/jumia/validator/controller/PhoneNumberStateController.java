package com.jumia.validator.controller;

import com.jumia.validator.service.CountryService;
import com.jumia.validator.service.PhoneService;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/phone-number-states")
public class PhoneNumberStateController {

    private PhoneService phoneService;

    @Autowired
    public PhoneNumberStateController(PhoneService phoneService)
    {
        this.phoneService = phoneService;
    }

    /**
     * GET : get all listed phone number states.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of phone number states in body
     */
    @ApiOperation(value = "get all the listed phone number states")
    @GetMapping("")
    public ResponseEntity<List<String>> getStates() {
        log.info("REST request to get a list of phone number states");
        return ResponseEntity.ok(phoneService.findAll());
    }
}
