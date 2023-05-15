package com.meme.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class AjAjTestUtils {
    
    @SneakyThrows
    public static String readFile(String path) {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
