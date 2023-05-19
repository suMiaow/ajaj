package com.meme.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@UtilityClass
public class DateUtils {

    public static final String PATTERN_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String PATTERN_28 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String PATTERN_10 = "yyyy-MM-dd";
    public static final String PATTERN_14 = "yyyyMMddHHmmss";
    public static final String PATTERN_19 = "yyyy-MM-dd HH:mm:ss";


    public static ZoneOffset getDefaultZoneOffset() {
        return ZoneId.systemDefault().getRules().getOffset(Instant.now());
    }

    public static LocalDateTime getLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }

    public static String format(LocalDateTime date, String pattern) {
        if (date == null) {
            return null;
        }

        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(ZonedDateTime date, String pattern) {
        if (date == null) {
            return null;
        }

        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(OffsetDateTime date, String pattern) {
        if (date == null) {
            return null;
        }

        return format(date.toLocalDateTime(), pattern);
    }

    public static LocalDateTime toLocalDateTime(Date date) {

        if (date == null) {
            return null;
        }

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime parseLocalDateTime(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static OffsetDateTime parseOffsetDateTime(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        try {
            return OffsetDateTime.parse(dateStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static String formatOffset(String dateStr, String destPattern) {
        return format(parseOffsetDateTime(dateStr), destPattern);
    }
}
