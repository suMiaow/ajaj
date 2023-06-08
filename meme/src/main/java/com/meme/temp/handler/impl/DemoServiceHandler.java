package com.meme.temp.handler.impl;

import com.meme.temp.handler.ServiceHandler;
import com.meme.temp.handler.model.request.DemoRequest;
import com.meme.temp.handler.model.response.DemoResponse;
import org.springframework.stereotype.Component;

@Component
public class DemoServiceHandler implements ServiceHandler<DemoRequest, DemoResponse> {
    @Override
    public DemoResponse handle(DemoRequest demoRequest) {
        return new DemoResponse();
    }
}
