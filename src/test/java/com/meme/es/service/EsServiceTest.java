package com.meme.es.service;

/*
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import com.meme.es.dto.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EsServiceTest {

    /*
    @Autowired
    private RestHighLevelClient client;

    @Test
    public void test3() {
        try {
            GetResponse getResponse = client.get(new GetRequest("book", "1"), RequestOptions.DEFAULT);
            log.info("index: {}", getResponse.getIndex());
            log.info("id: {}", getResponse.getId());
            log.info("version: {}", getResponse.getVersion());
            log.info("source: {}", getResponse.getSourceAsString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Autowired
    private ElasticsearchClient client;

    @Test
    public void test4() {

        GetRequest getRequest = new GetRequest.Builder()
                .index("book")
                .id("1")
//                .sourceIncludes("name", "price")
//                .sourceExcludes("description")
                .build();

        try {
            GetResponse<Object> bookGetResponse = client.get(getRequest, Object.class);
            log.info("index: {}", bookGetResponse.index());
            log.info("id: {}", bookGetResponse.id());
            log.info("version: {}", bookGetResponse.version());
            log.info("source: {}", bookGetResponse.source());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/

