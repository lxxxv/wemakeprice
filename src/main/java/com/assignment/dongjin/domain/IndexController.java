package com.assignment.dongjin.domain;

import com.assignment.dongjin.model.BuildResult;
import com.assignment.dongjin.model.MessageDTO;
import com.assignment.dongjin.model.OutputType;
import com.assignment.dongjin.support.HttpRequest;
import com.assignment.dongjin.support.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final IndexService indexService;

    @ModelAttribute("outputTypes")
    public List<OutputType> outputTypes() {
        return indexService.outputTypes();
    }

    @GetMapping("/")
    public ModelAndView main(Model model) {
        model.addAttribute("url", "https://www.google.com/");
        model.addAttribute("outputType", outputTypes());
        model.addAttribute("printGroup", 100);
        return new ModelAndView("index");
    }

    @PostMapping("/buildRequest")
    public String buildRequest(Model model, MessageDTO dto) {

        BuildResult buildResult = indexService.buildRequest(dto);

        if (Validation.nonEmpty(buildResult.getErrorMessage())) {
            model.addAttribute("errorMessage", buildResult.getErrorMessage());
            return "resultPrint";
        }

        model.addAttribute("groupInner", buildResult.getGroupInner());
        model.addAttribute("groupOuter", buildResult.getGroupOuter());

        return "resultPrint";
    }
}
