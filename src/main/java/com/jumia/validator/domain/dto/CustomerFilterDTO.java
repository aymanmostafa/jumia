package com.jumia.validator.domain.dto;

import com.jumia.validator.enums.StateEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerFilterDTO {

    private String country;
    private StateEnum state;
}
