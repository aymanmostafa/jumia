package com.jumia.validator.service.util;

import com.jumia.validator.enums.StateEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilTest {

    static final String VALID_INPUT = "(212) 698054317";
    static final String VALID_REGEX = "\\(212\\)\\ ?[5-9]\\d{8}$";
    static final String INVALID_INPUT = "(2123) 698054317";

    @Test
    void validateRegex_assertValidRegexWithValidInput_returnValid() {
        String input = VALID_INPUT;
        String regex = VALID_REGEX;

        StateEnum result = RegexUtil.validateRegex(input, regex);

        assertEquals(StateEnum.VALID, result);
    }

    @Test
    void validateRegex_assertNullRegexWithValidInput_returnInValid() {
        String input = VALID_INPUT;
        String regex = null;

        StateEnum result = RegexUtil.validateRegex(input, regex);

        assertEquals(StateEnum.INVALID, result);
    }

    @Test
    void validateRegex_assertValidRegexWithNullInput_returnInValid() {
        String input = null;
        String regex = VALID_REGEX;

        StateEnum result = RegexUtil.validateRegex(input, regex);

        assertEquals(StateEnum.INVALID, result);
    }

    @Test
    void validateRegex_assertNullRegexWithNullInput_returnInValid() {
        String input = null;
        String regex = null;

        StateEnum result = RegexUtil.validateRegex(input, regex);

        assertEquals(StateEnum.INVALID, result);
    }

    @Test
    void validateRegex_assertValidRegexWithInvalidInput_returnInValid() {
        String input = INVALID_INPUT;
        String regex = VALID_REGEX;

        StateEnum result = RegexUtil.validateRegex(input, regex);

        assertEquals(StateEnum.INVALID, result);
    }

    @Test
    void validateRegex_assertInValidRegexWithValidInput_returnInValid() {
        String input = VALID_INPUT;
        String regex = "\\(212\\)\\ ?[5-9]\\d{8}[][[$";

        StateEnum result = RegexUtil.validateRegex(input, regex);

        assertEquals(StateEnum.INVALID, result);
    }

    @Test
    void validateRegex_assertEmptyRegexWithValidInput_returnInValid() {
        String input = INVALID_INPUT;
        String regex = "";

        StateEnum result = RegexUtil.validateRegex(input, regex);

        assertEquals(StateEnum.INVALID, result);
    }

    @Test
    void isParametersValid_assertEmptyRegexWithEmptyInput_returnFalse() {
        String input = "";
        String regex = "";

        boolean result = RegexUtil.isParametersValid(input, regex);

        assertFalse(result);
    }

    @Test
    void isParametersValid_assertValidRegexWithValidInput_returnTrue() {
        String input = INVALID_INPUT;
        String regex = VALID_REGEX;

        boolean result = RegexUtil.isParametersValid(input, regex);

        assertTrue(result);
    }

    @Test
    void isParametersValid_assertEmptyRegexWithNullInput_returnFalse() {
        String input = null;
        String regex = "";

        boolean result = RegexUtil.isParametersValid(input, regex);

        assertFalse(result);
    }
}
