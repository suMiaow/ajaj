package com.meme.pojo;

import lombok.Data;

@Data
public class TestUser<T> {

    private String name;

    private T data;
}
