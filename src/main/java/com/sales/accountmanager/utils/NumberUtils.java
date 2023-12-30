package com.sales.accountmanager.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberUtils {

    public static String formatBigDecimal(BigDecimal number) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        return formatter.format(number);
    }

    public static BigDecimal parseFormattedString(String number) {
        try {
            NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
            Number parsedNumber = formatter.parse(number);
            return new BigDecimal(parsedNumber.toString());
        } catch (Exception e) {
            return new BigDecimal(0);
        }
    }

}
