package com.assignment.dongjin.support;

import org.jsoup.Jsoup;

public class StringUtils {

    public static String exportNumber(final String value) {
        if (Validation.nonEmpty(value)) {
            return value.replaceAll("[^0-9]","");
        }

        return "";
    }

    public static String exportEnglishChar(final String value) {
        if (Validation.nonEmpty(value)) {
            return value.replaceAll("[^a-zA-Z]","");
        }

        return "";
    }

    public static String mixString(final String englishStr, final String numberStr) {
        if (Validation.nonEmpty(englishStr) && Validation.nonEmpty(numberStr)) {
            char[] englishChar = englishStr.toCharArray();
            char[] numberChar = numberStr.toCharArray();
            char[] mixedChar = new char[englishChar.length + numberChar.length];

            boolean isEngTarget = true;
            int idxEng = 0;
            int idxNum = 0;
            int idx = 0;
            while (idx < mixedChar.length) {
                if (isEngTarget) {
                    if (!Validation.indexOutOfBound(idxEng, englishChar)) {
                        mixedChar[idx] = englishChar[idxEng];
                        idxEng++;
                    } else {
                        mixedChar[idx] = numberChar[idxNum];
                        idxNum++;
                    }
                    isEngTarget = false;
                } else {
                    if (!Validation.indexOutOfBound(idxNum, numberChar)) {
                        mixedChar[idx] = numberChar[idxNum];
                        idxNum++;

                    } else {
                        mixedChar[idx] = englishChar[idxEng];
                        idxEng++;
                    }
                    isEngTarget = true;
                }
                idx++;
            }
            return String.valueOf(mixedChar);
        }

        return "";
    }

    public static String exportHTMLTag(final String html) {
        if (Validation.nonEmpty(html)) {
            return Jsoup.parse(html).text();
        }

        return "";
    }
}
