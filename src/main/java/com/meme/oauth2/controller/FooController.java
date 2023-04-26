package com.meme.oauth2.controller;

import com.meme.oauth2.model.FooDto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/foos")
public class FooController {


    @CrossOrigin(origins = "http://localhost:8089")
    @GetMapping(value = "/{id}")
    public FooDto findOne(@PathVariable Long id) {
        return new FooDto();
    }

    @GetMapping
    public Collection<FooDto> findAll() {
        return List.of(new FooDto());
    }

}