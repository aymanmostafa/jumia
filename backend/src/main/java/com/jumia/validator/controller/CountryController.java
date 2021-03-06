package com.jumia.validator.controller;

import com.jumia.validator.service.CountryService;
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
@RequestMapping("/api/v1/countries")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService)
    {
        this.countryService = countryService;
    }

    /**
     * GET : get all listed countries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of countries in body
     */
    @ApiOperation(value = "get all the listed countries")
    @GetMapping("")
    public ResponseEntity<List<String>> getCountries() {
        log.info("REST request to get a list of countries");
        return ResponseEntity.ok(countryService.findAll());
    }
}
