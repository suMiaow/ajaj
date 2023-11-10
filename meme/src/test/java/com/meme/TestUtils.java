package com.meme;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class TestUtils {

    private static final Gson gson = new Gson();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows
    public static <T> T readJson(String path, TypeReference<T> type) {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        return objectMapper.readValue(file, type);
    }

    @SneakyThrows
    public static <T> T jacksonReadJson(String path, Class<T> type) {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        return objectMapper.readValue(file, type);
    }

    @SneakyThrows
    public static <T> T readJson(String path, com.alibaba.fastjson.TypeReference<T> type) {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return JSON.parseObject(new String(bytes, StandardCharsets.UTF_8), type);
    }

    @SneakyThrows
    public static String readFile(String path) {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public static <T> T fastjsonReadJson(String path, Class<T> type) {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return JSON.parseObject(new String(bytes, StandardCharsets.UTF_8), type);
    }

    @SneakyThrows
    public <T> T readJson(String path, Type typeOfT) {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, typeOfT);
        }
    }

    public static final String JSON_MOCK_REQUEST_PATH = "mock/json/request/";
    public static final String JSON_MOCK_RESPONSE_PATH = "mock/json/response/";

    @SneakyThrows
    public static String readJsonMockFile(String path) {

        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardCharsets.UTF_8);
    }

    public static <T> T readJsonMockRequest(String jsonName, TypeReference<T> type) {
        return readJson(JSON_MOCK_REQUEST_PATH + jsonName, type);
    }

    public static <T> T readJsonMockRequest(String jsonName, com.alibaba.fastjson.TypeReference<T> type) {
        return readJson(JSON_MOCK_REQUEST_PATH + jsonName, type);
    }

    public static <T> T jacksonReadJsonMockRequest(String jsonName, Class<T> type) {
        return jacksonReadJson(JSON_MOCK_REQUEST_PATH + jsonName, type);
    }

    public static <T> T fastjsonReadJsonMockRequest(String jsonName, Class<T> type) {
        return fastjsonReadJson(JSON_MOCK_REQUEST_PATH + jsonName, type);
    }

    public static <T> T readJsonMockResponse(String jsonName, TypeReference<T> type) {
        return readJson(JSON_MOCK_RESPONSE_PATH + jsonName, type);
    }

    public static <T> T readJsonMockResponse(String jsonName, com.alibaba.fastjson.TypeReference<T> type) {
        return readJson(JSON_MOCK_RESPONSE_PATH + jsonName, type);
    }

    public static <T> T jacksonReadJsonMockResponse(String jsonName, Class<T> type) {
        return jacksonReadJson(JSON_MOCK_RESPONSE_PATH + jsonName, type);
    }

    public static <T> T fastjsonReadJsonMockResponse(String jsonName, Class<T> type) {
        return fastjsonReadJson(JSON_MOCK_RESPONSE_PATH + jsonName, type);
    }

    private static final ParserContext PARSER_CONTEXT = new TemplateParserContext();
    private static StandardEnvironment environment;

    @SneakyThrows
    public static void setValue(Object object) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Value valueAnnotation = declaredField.getAnnotation(Value.class);
            if (valueAnnotation != null) {
                Object defaultValue = getDefaultValue(valueAnnotation);
                if (defaultValue != null) {
                    declaredField.setAccessible(true);
                    setField(declaredField, object, defaultValue);
                }
            }
        }
    }

    @SneakyThrows
    public static Environment getDevEnvironment() {

        if (environment == null) {
            environment = new StandardEnvironment();
            environment.setActiveProfiles("dev");

            MutablePropertySources propertySources = environment.getPropertySources();
            ResourcePropertySource applicationPropertySource = new ResourcePropertySource("application.properties");
            ResourcePropertySource applicationDevPropertySource = new ResourcePropertySource("application-dev.properties");
            propertySources.addLast(applicationPropertySource);
            propertySources.addLast(applicationDevPropertySource);
        }
        return environment;
    }

    private static Object getDefaultValue(Value valueAnnotation) {
        String value = valueAnnotation.value();
        if (StringUtils.isNotBlank(value)) {
            Environment defaultEnvironment = getDevEnvironment();
            SpelExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(defaultEnvironment.resolveRequiredPlaceholders(value), PARSER_CONTEXT);
            return expression.getValue();
        }
        return null;
    }

    @SneakyThrows
    private static void setField(Field field, Object obj, Object value) {
        Class<?> type = field.getType();
        if (value instanceof String) {
            String vStr = (String) value;
            if (Integer.class.equals(type)) {
                value = Integer.parseInt(vStr);
            } else if (Boolean.class.equals(type)) {
                value = Boolean.parseBoolean(vStr);
            }
        }
        field.set(obj, value);
    }

}
