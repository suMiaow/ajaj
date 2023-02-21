package com.meme.exception;

public class DemoException1 extends RuntimeException{


    public DemoException1() {
        super();
    }
    public DemoException1(String message) {
        super(message);
    }
    public DemoException1(String message, Throwable cause) {
        super(message, cause);
    }

    public DemoException1(Throwable cause) {
        super(cause);
    }
}
