package com.assignment.dongjin.domain;

import com.assignment.dongjin.model.BuildResult;
import com.assignment.dongjin.model.MessageDTO;
import com.assignment.dongjin.model.OutputType;
import com.assignment.dongjin.support.HttpRequest;
import com.assignment.dongjin.support.SortUtils;
import com.assignment.dongjin.support.StringUtils;
import com.assignment.dongjin.support.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    @Override
    public BuildResult buildRequest(MessageDTO dto) {

        if (!Validation.urlCheck(dto.getUrl())) {
            BuildResult buildResult = new BuildResult();
            buildResult.setErrorMessage("URL이 정상적이지 않습니다.");
            log.error(buildResult.getErrorMessage());
            return buildResult;
        }

        String body = "";
        Map<Integer, String> result = HttpRequest.request(HttpRequest.HttpMethod.GET, dto.getUrl(), null, null, null, "");
        if (Validation.validStatusCode(result)) {
            body = result.get(200);
        } else {
            int statusCode = 0;
            for(Integer key : result.keySet()) {
                statusCode = key;
            }
            BuildResult buildResult = new BuildResult();
            buildResult.setErrorMessage(String.format("서버로부터 응답을 실패하였습니다. Status Code : [%d] URL : [%s] ", statusCode, dto.getUrl()));
            log.error(buildResult.getErrorMessage());
            return buildResult;
        }

        if (dto.getOutputType().equals("EXPORTTAG")) {
            body = StringUtils.exportHTMLTag(body);
        }
        log.info(body);

        BuildResult buildResult = new BuildResult();

        String englishChar = StringUtils.exportEnglishChar(body);
        log.info(String.format("englishChar : %s", englishChar));

        String numberChar = StringUtils.exportNumber(body);
        log.info(String.format("numberChar : %s", numberChar));

        englishChar = SortUtils.sort(englishChar);
        log.info(String.format("sort englishChar : %s", englishChar));

        numberChar = SortUtils.sort(numberChar);
        log.info(String.format("sort numberChar : %s", numberChar));

        String merge = StringUtils.mixString(englishChar, numberChar);
        log.info(String.format("merge : %s", merge));

        if (dto.getGroup() >= merge.length()) {
            buildResult.setGroupInner(merge);
            buildResult.setGroupOuter("");
        } else {
            buildResult.setGroupInner(merge.substring(0, dto.getGroup()));
            buildResult.setGroupOuter(merge.substring(dto.getGroup(), merge.length()));
        }

        return buildResult;
    }

    @Override
    public List<OutputType> outputTypes() {
        List<OutputType> outputTypes = new ArrayList<>();
        outputTypes.add(new OutputType("EXPORTTAG", "HTML 태그 제외"));
        outputTypes.add(new OutputType("ALLTEXT", "Text 전체"));
        return outputTypes;
    }
}
