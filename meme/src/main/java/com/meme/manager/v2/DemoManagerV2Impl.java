package com.meme.manager.v2;

import com.meme.manager.DemoManager;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DemoManagerV2Impl implements DemoManager {
    @Override
    public String demo() {
        return "v2";
    }
}
