package com.github.lokic.javaplus;

import java.math.BigDecimal;

public class BigDecimals {
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
