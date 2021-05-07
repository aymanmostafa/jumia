package com.jumia.validator.domain.dto;

import com.jumia.validator.enums.PhoneNumberStateEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerFilterDTO {

    private String country;
    private PhoneNumberStateEnum state;
}
