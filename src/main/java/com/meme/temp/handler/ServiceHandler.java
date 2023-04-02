package com.meme.temp.handler;

public interface ServiceHandler<T, R> {

    R handle(T t);
}
