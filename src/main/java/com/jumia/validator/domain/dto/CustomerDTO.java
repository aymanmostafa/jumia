package com.jumia.validator.domain.dto;

import com.jumia.validator.enums.StateEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {

    private Long id;
    private String name;
    private String phone;
    private String country;
    private StateEnum phoneNumberState;
}
