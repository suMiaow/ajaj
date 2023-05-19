package com.meme.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@UtilityClass
public class AjAjTestUtils {
    
    @SneakyThrows
    public static String readFile(String path) {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static void writeFile(String path, String content) {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        Files.write(Paths.get(file.getAbsolutePath()), List.of(content), StandardCharsets.UTF_8);
    }

}
