package com.jumia.validator.service.util;

import com.jumia.validator.enums.PhoneNumberStateEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Slf4j
public final class RegexUtil {

    private RegexUtil(){}

    public static PhoneNumberStateEnum validateRegex(String input, String regex) {
        try{
            if(Pattern.matches(regex, input)) {
                return PhoneNumberStateEnum.VALID;
            }
        } catch (PatternSyntaxException e){
            log.error("Error in validating regex {}", e);
        }
        return PhoneNumberStateEnum.INVALID;
    }
}
