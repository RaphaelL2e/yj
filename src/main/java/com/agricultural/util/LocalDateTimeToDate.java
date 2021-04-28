package com.agricultural.util;

import java.time.LocalDateTime;

/**
 * @author:leeyf
 * @create: 2019-04-02 20:31
 * @Description:
 */
public class LocalDateTimeToDate {

    public static StringBuilder date( LocalDateTime date) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.getYear()+"-"+date.getMonth()+"-"+date.getDayOfMonth()+" "+date.getHour()+":"+date.getMinute()+":"+date.getSecond());
        return stringBuilder;
    }
}