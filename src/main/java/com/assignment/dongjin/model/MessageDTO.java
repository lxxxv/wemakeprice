package com.assignment.dongjin.model;

import lombok.Data;

@Data
public class MessageDTO {

    private String url;
    private String outputType;
    private int group;

    public MessageDTO(String url, String outputType, int group) {
        this.url = url;
        this.outputType = outputType;
        this.group = group;
    }

    public String getUrl() {
        return url;
    }

    public String getOutputType() {
        return outputType;
    }

    public int getGroup() {
        return group;
    }
}
