package com.github.lokic.javaplus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BigDecimals {

    public static String format(BigDecimal value, String format) {
        return new DecimalFormat(format).format(value);
    }

    public static BigDecimal pctChange(BigDecimal current, BigDecimal pre, int scale, RoundingMode roundingMode) {
        if (current == null || pre == null || pre.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        return current.subtract(pre).multiply(BigDecimal.valueOf(100)).divide(pre.abs(), scale, roundingMode);
    }

    public static BigDecimal ratio(BigDecimal numerator, BigDecimal denominator, int scale, RoundingMode roundingMode) {
        if (numerator == null || denominator == null || denominator.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        return numerator.multiply(BigDecimal.valueOf(100)).divide(denominator, scale, roundingMode);
    }


    public static boolean eq(BigDecimal a, Integer b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(BigDecimal.valueOf(b)) == 0;
    }

    public static boolean eq(BigDecimal a, Long b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(BigDecimal.valueOf(b)) == 0;
    }

    public static boolean eq(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(b) == 0;
    }


    public static boolean le(BigDecimal a, Integer b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(BigDecimal.valueOf(b)) <= 0;
    }

    public static boolean le(BigDecimal a, Long b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(BigDecimal.valueOf(b)) <= 0;
    }

    public static boolean le(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(b) <= 0;
    }

    public static boolean lt(BigDecimal a, Integer b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(BigDecimal.valueOf(b)) < 0;
    }

    public static boolean lt(BigDecimal a, Long b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(BigDecimal.valueOf(b)) < 0;
    }

    public static boolean lt(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(b) < 0;
    }

    public static boolean ge(BigDecimal a, Integer b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(BigDecimal.valueOf(b)) >= 0;
    }

    public static boolean ge(BigDecimal a, Long b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(BigDecimal.valueOf(b)) >= 0;
    }

    public static boolean ge(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(b) >= 0;
    }

    public static boolean gt(BigDecimal a, Integer b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(BigDecimal.valueOf(b)) > 0;
    }

    public static boolean gt(BigDecimal a, Long b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(BigDecimal.valueOf(b)) > 0;
    }

    public static boolean gt(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return false;
        }
        return a.compareTo(b) > 0;
    }

}
