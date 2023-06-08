package com.meme.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {

    @Value("${meme.elasticsearch.host-list:localhost}")
    private String hostList;

    /*
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

    @Bean
    public ElasticsearchClient elasticsearchClient() {

        // low-level client
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();

        // transport with a Jackson mapper
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // API client
        return new ElasticsearchClient(transport);
    }
     */
}


