package com.jumia.validator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CountryEnum {

    CAMEROON("Cameroon", "237", "\\(237\\)\\ ?[2368]\\d{7,8}$"),
    ETHIOPIA("Ethiopia", "251", "\\(251\\)\\ ?[1-59]\\d{8}$"),
    MOROCCO("Morocco", "212", "\\(212\\)\\ ?[5-9]\\d{8}$"),
    MOZAMBIQUE("Mozambique", "258", "\\(258\\)\\ ?[28]\\d{7,8}$"),
    UGANDA("Uganda", "256", "\\(256\\)\\ ?\\d{9}$"),;

    private String name;
    private String phoneCode;
    private String phoneRegex;

    public static CountryEnum findByPhoneCode(String phoneCode) {
        for (CountryEnum country : CountryEnum.values()) {
            if (StringUtils.equals(country.getPhoneCode(), phoneCode)) {
                return country;
            }
        }
        return null;
    }

    public static CountryEnum findByName(String name) {
        for (CountryEnum country : CountryEnum.values()) {
            if (StringUtils.equalsIgnoreCase(country.getName(), name)) {
                return country;
            }
        }
        return null;
    }
}
