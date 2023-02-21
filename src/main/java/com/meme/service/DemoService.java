package com.meme.service;

import com.meme.manager.DemoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    @Autowired
    private DemoManager demoManager;


    public String fun() {
        return demoManager.demo();
    }
}
