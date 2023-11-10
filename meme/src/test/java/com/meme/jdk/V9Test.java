package com.meme.jdk;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
class V9Test {

    @Test
    void testHttpRequest() {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://www.baidu.com"))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            log.info("request:  {}", httpRequest);
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            log.info("response: {}, body: {}", httpResponse, httpResponse.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testProcess() {
        ProcessHandle current = ProcessHandle.current();
        log.info("pid: {}", current.pid());
        log.info("info: {}", current.info());

    }

}
