package com.assignment.dongjin.domain;

import com.assignment.dongjin.model.BuildResult;
import com.assignment.dongjin.model.MessageDTO;
import com.assignment.dongjin.model.OutputType;

import java.util.List;

public interface IndexService {

    BuildResult buildRequest(MessageDTO dto);
    List<OutputType> outputTypes();
}
