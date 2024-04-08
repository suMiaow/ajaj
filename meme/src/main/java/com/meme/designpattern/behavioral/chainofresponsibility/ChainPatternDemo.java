package com.meme.designpattern.behavioral.chainofresponsibility;

public class ChainPatternDemo {

    private static AbstractLogger getChainOfLoggers() {
        ErrorLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        FileLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        ConsoleLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.log(AbstractLogger.INFO, "This is an info message");
        loggerChain.log(AbstractLogger.DEBUG, "This is a debug message");
        loggerChain.log(AbstractLogger.ERROR, "This is an error message");
    }
}
