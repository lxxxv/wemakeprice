package com.assignment.dongjin.support;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Slf4j
public class HttpRequestTest {

    @Test
    public void testHttpRequest() {
        String url = "https://www.google.com";
        if (Validation.urlCheck(url)) {
            Map<Integer, String> result = HttpRequest.request(HttpRequest.HttpMethod.GET, url, null, null, null, "");
            if (Validation.validStatusCode(result)) {
                log.info("OK");
            } else {
                log.info("ERROR");
            }
        }
    }
}
