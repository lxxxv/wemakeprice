package com.assignment.dongjin.model;

import lombok.Data;

@Data
public class OutputType {

    private String code;
    private String displayName;

    public OutputType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
