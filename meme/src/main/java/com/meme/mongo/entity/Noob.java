package com.meme.mongo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Noob {

    private String name;
    private String hobby;
}
