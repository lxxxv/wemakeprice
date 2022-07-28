package com.assignment.dongjin.domain;

import com.assignment.dongjin.model.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class IndexServiceTest {

    @Autowired
    private IndexService indexService;

    @Test
    public void testIndexService() {
        indexService.buildRequest(new MessageDTO("https://www.google.com/", "ALLTEXT", 6));
        indexService.buildRequest(new MessageDTO("https://www.naver.com/", "EXPORTTAG", 41));
        indexService.buildRequest(new MessageDTO("https://www.google.com/", "EXPORTTAG", 41));
    }
}
