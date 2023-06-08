package com.meme.es;

/*
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.meme.es.dto.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class EsTest {
    ElasticsearchClient client;

    @Before
    public void initClient() {

        // low-level client
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();

        // transport with a Jackson mapper
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // API client
        client = new ElasticsearchClient(transport);
    }

    @Test
    public void test1() {
        try {
            SearchResponse<Book> search = client.search(s -> s
                            .index("book")
                    , Book.class);

            for (Hit<Book> hit : search.hits().hits()) {
                log.info("hit.source: {}", hit.source());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        GetRequest getRequest = new GetRequest.Builder().index("book").id("1").build();

        try {
            GetResponse<Book> bookGetResponse = client.get(getRequest, Book.class);
            log.info("index: {}", bookGetResponse.index());
            log.info("id: {}", bookGetResponse.id());
            log.info("version: {}", bookGetResponse.version());
            log.info("source: {}",bookGetResponse.source());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*

    RestHighLevelClient client;

    @Before
    public void initClient() {
        client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

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


}
     */

