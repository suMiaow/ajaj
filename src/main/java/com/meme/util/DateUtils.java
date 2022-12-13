package com.meme.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@UtilityClass
public class DateUtils {

    public static final String PATTERN_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        try {
            return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static ZoneOffset getDefaultZoneOffset() {
        return ZoneId.systemDefault().getRules().getOffset(Instant.now());
    }

    public static LocalDateTime getLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }

}
