package com.meme.manager.v1;

import com.meme.manager.DemoManager;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class DemoManagerV1Impl implements DemoManager {


    @Override
    public String demo() {
        return "v1";
    }
}
