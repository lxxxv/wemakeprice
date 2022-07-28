package com.assignment.dongjin.support;

import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

public class Validation {

    public static boolean isNull(Object value) {
        return Objects.isNull(value);
    }

    public static boolean nonNull(Object value) {
        return !Validation.isNull(value);
    }

    public static boolean isEmpty(final String value) {
        if (Validation.nonNull(value)) {
            return (value.isEmpty());
        } else {
            return true;
        }
    }

    public static boolean nonEmpty(final String value) {
        return !Validation.isEmpty(value);
    }

    public static boolean urlCheck(final String url) {
        if (Validation.isEmpty(url)) return false;

        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        return url.matches(regex);
    }

    public static boolean validStatusCode(Map<Integer, String> response) {
        if (Validation.nonNull(response)) {
            if (response.size() > 0) {
                for(Integer key : response.keySet()) {
                    if (key == 200) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static boolean indexOutOfBound(final int idx, char[] target) {
        if (idx >= target.length) {
            return true;
        }
        return false;
    }
}
