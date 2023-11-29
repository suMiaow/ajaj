package com.meme.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;

@UtilityClass
public class SqlUtil {


    public static <T> String getTableFields(Class<T> clazz) {

        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder();

        for (Field field : fields) {
            String columnName = getColumnName(field);
            if (StringUtils.isNotBlank(columnName)) {
                if (StringUtils.isBlank(sb)) {
                    sb.append(" ").append(columnName);
                } else {
                    sb.append(", ").append(columnName);
                }
            }
        }
        if (StringUtils.isNotBlank(sb)) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String getColumnName(Field field) {

        Column columnAnno = field.getAnnotation(Column.class);
        if (columnAnno != null) {
            String columnName = columnAnno.name();
            if (StringUtils.isNotBlank(columnName)) {
                return columnName;
            } else {
                return field.getName();
            }
        }
        Id idAnno = field.getAnnotation(Id.class);
        if (idAnno != null) {
            return field.getName();
        }
        return null;
    }

}
