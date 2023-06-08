package com.meme.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    public static void export(String absPath, String fileContent) throws IOException {

        Path file = Paths.get(absPath);
        Files.createDirectories(file.getParent());
        Files.write(file, fileContent.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        try {
            export("D://temp/a/b/c/d.json", "{}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
