package com.meme.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

@UtilityClass
public class BeanUtils {

    @SneakyThrows
    public static void copyPropertiesNonEmpty(Object dest, Object orig) {
        Class<?> origClazz = orig.getClass();
        Class<?> destClazz = dest.getClass();

        Field[] origFields = origClazz.getDeclaredFields();

        for (Field origField : origFields) {
            Field destField;
            try {
                destField = destClazz.getDeclaredField(origField.getName());
            } catch (NoSuchFieldException e) {
                continue;
            }
            Type origType = origField.getGenericType();
            Type destType = destField.getGenericType();
            if (origType.equals(destType)) {
                Object fieldValue = getField(origField, orig);
                if (fieldValue != null) {
                    if (fieldValue instanceof String) {
                        if (StringUtils.isNotBlank((String) fieldValue)) {
                            setField(destField, dest, fieldValue);
                        }
                    } else if (fieldValue instanceof Collection) {
                        if (CollectionUtils.isNotEmpty((Collection<?>) fieldValue)) {
                            setField(destField, dest, fieldValue);
                        }
                    } else if (fieldValue instanceof Map) {
                        if (!((Map<?, ?>) fieldValue).isEmpty()) {
                            setField(destField, dest, fieldValue);
                        }
                    } else {
                        setField(destField, dest, fieldValue);
                    }
                }
            }
        }

    }

    private void setField(Field field, Object obj, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method destSetMethod = obj.getClass().getDeclaredMethod(
                "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1),
                field.getType()
        );
        destSetMethod.invoke(obj, value);
    }

    private Object getField(Field field, Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method destSetMethod = obj.getClass().getDeclaredMethod(
                "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)
        );
        return destSetMethod.invoke(obj);
    }

}
