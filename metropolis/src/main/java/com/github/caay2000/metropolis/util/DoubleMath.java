package com.github.caay2000.metropolis.util;

public class DoubleMath {

    //Class copied and refactored directly from google guava library, so i don't need to add it the the jar file

    public static int fuzzyCompare(double a, double b, double tolerance) {
        if (fuzzyEquals(a, b, tolerance)) {
            return 0;
        } else if (a < b) {
            return -1;
        } else {
            return a > b ? 1 : compareBoolean(Double.isNaN(a), Double.isNaN(b));
        }
    }

    private static boolean fuzzyEquals(double a, double b, double tolerance) {
        if (tolerance < 0.0D) {
            throw new IllegalArgumentException("tolerance" + " (" + tolerance + ") must be >= 0");
        }
        return Math.copySign(a - b, 1.0D) <= tolerance || a == b || Double.isNaN(a) && Double.isNaN(b);
    }

    private static int compareBoolean(boolean a, boolean b) {
        return a == b ? 0 : (a ? 1 : -1);
    }

}
