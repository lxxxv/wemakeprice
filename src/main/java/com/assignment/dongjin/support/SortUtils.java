package com.assignment.dongjin.support;

import java.util.*;

public class SortUtils {

    public static String sort(final String value) {
        List<String> values = new ArrayList<String>(Arrays.asList(value.split("")));

        Collections.sort(values, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);

                if (o1.length() == o2.length()) {
                    for (int idx = 0; idx < o1.length(); idx++) {
                        final String o1Char = o1.substring(idx, idx + 1);
                        final String o2Char = o2.substring(idx, idx + 1);
                        if (!o1Char.equals(o2Char)) {
                            if (o1Char.toUpperCase().equals(o2Char.toUpperCase())) {
                                if (o1Char.equals(o1Char.toLowerCase()) && o2Char.equals(o2Char.toUpperCase())) {
                                    return 1;
                                } else {
                                    return -1;
                                }
                            } else {
                                return o1Char.toUpperCase().compareTo(o2Char.toUpperCase());
                            }
                        }
                    }
                    return 0;
                } else {
                    return (o1.length() > o2.length()) ? -1 : +1;
                }
            }
        });

        return String.join("", values);
    }
}
