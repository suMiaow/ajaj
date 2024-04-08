package com.meme.designpattern.behavioral.chainofresponsibility;

import lombok.Setter;

public abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;

    @Setter
    private AbstractLogger nextLogger;

    public void log(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.log(level, message);
        }
    }

    abstract protected void write(String message);
}
