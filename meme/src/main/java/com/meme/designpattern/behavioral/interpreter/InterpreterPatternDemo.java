package com.meme.designpattern.behavioral.interpreter;

public class InterpreterPatternDemo {

    public static Expression getMaleExpression() {
        TerminalExpression robert = new TerminalExpression("Robert");
        TerminalExpression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    public static Expression getMarriedWomanExpression() {
        TerminalExpression julie = new TerminalExpression("Julie");
        TerminalExpression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        Expression male = getMaleExpression();
        Expression marriedWoman = getMarriedWomanExpression();

        System.out.println("John is male? " + male.interpret("John"));
        System.out.println("Julie married woman? " + marriedWoman.interpret("Julie Married"));
    }
}
