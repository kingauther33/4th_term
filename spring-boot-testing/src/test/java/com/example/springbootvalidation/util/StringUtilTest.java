package com.example.springbootvalidation.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class StringUtilTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void validateEmail() {
        assertThat(StringUtil.validateEmail("andinh@gmail.com")).isEqualTo(true);
        assertThat(StringUtil.validateEmail("andinhgmail.com")).isEqualTo(false);
        assertThat(StringUtil.validateEmail("andinh@gmail   ")).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({
            "andinh123@gmail.com,true",
            "andinhgmail.com,false",
            "andinh@gmail,false",
    })
    void validateEmailWithParameter(String email, boolean expectedResult) {
        assertThat(StringUtil.validateEmail(email)).isEqualTo(expectedResult);
    }
}