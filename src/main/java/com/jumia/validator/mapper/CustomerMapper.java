package com.jumia.validator.mapper;

import com.jumia.validator.domain.dto.CustomerDTO;
import com.jumia.validator.domain.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer>{
}
