package com.assignment.dongjin.support;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SortUtilsTest {

    @Test
    public void sortUtilsTest() {
        String testValue = "zZBbkKoO";
        log.info(testValue + " => " + SortUtils.sort(testValue));
        testValue = "987654321555444";
        log.info(testValue + " => " + SortUtils.sort(testValue));
    }
}
