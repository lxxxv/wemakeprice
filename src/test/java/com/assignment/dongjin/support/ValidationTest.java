package com.assignment.dongjin.support;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ValidationTest {

    @Test
    public void testValidation() {
        boolean validationCheck = Validation.isNull(null);

        validationCheck = Validation.nonNull(null);
        validationCheck = Validation.isNull(new Object());
        validationCheck = Validation.nonNull(new Object());

        validationCheck = Validation.isEmpty(null);
        validationCheck = Validation.nonEmpty(null);
        validationCheck = Validation.isEmpty("");
        validationCheck = Validation.nonEmpty("1");

        validationCheck = Validation.urlCheck("111");
        validationCheck = Validation.urlCheck("https://www.google.com/");
        validationCheck = Validation.urlCheck("http://www.google.com/");
        validationCheck = Validation.urlCheck("https://google.com/");
        validationCheck = Validation.urlCheck("google.com/");

        validationCheck = Validation.indexOutOfBound(0, "111".toCharArray());
        validationCheck = Validation.indexOutOfBound(3, "111".toCharArray());
        validationCheck = Validation.indexOutOfBound(4, "111".toCharArray());
    }
}
