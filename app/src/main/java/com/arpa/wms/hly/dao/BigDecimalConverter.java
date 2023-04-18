package com.arpa.wms.hly.dao;

import androidx.room.TypeConverter;

import com.arpa.wms.hly.utils.NumberUtils;

import java.math.BigDecimal;

public class BigDecimalConverter {
    @TypeConverter
    public static BigDecimal revertDate(String value) {
        return new BigDecimal(value);
    }

    @TypeConverter
    public static String converterDate(BigDecimal value) {
        return NumberUtils.parseDecimal(value);
    }
}
