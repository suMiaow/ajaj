package com.meme.jvm;

import org.springframework.asm.ClassWriter;
import org.springframework.asm.Opcodes;

public class MethodAreaDemo extends ClassLoader{

    public static void main(String[] args) {
        MethodAreaDemo test = new MethodAreaDemo();
        int j = 0;
        try {
        for (int i = 0; i < 10000; i++, j++) {
            ClassWriter cw = new ClassWriter(0);
            cw.visit(Opcodes.V17, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
            byte[] code = cw.toByteArray();
            test.defineClass("Class" + i, code, 0, code.length);
        }
        } finally {
            System.out.println(j);
        }
    }
}
