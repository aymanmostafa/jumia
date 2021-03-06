package com.jumia.validator.service.util;

import com.jumia.validator.enums.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Slf4j
public final class RegexUtil {

    private RegexUtil(){}

    /**
     * Validate input string with a regex
     *
     * @param input the input string
     * @param regex the regex to match
     * @return the state enum
     */
    public static StateEnum validateRegex(String input, String regex) {
        try{
            if(isParametersValid(input, regex) && Pattern.matches(regex, input)) {
                return StateEnum.VALID;
            }
        } catch (PatternSyntaxException e){
            log.error("Error in validating regex {}", e);
        }
        return StateEnum.INVALID;
    }

    static boolean isParametersValid(String input, String regex) {
        return StringUtils.isNoneEmpty(input) && StringUtils.isNoneEmpty(regex);
    }
}
