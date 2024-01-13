package io.github.colindj1120.materialdesignui.utils;

public class NumberUtils {
    /**
     * Simple utility function which clamps the given value to be strictly
     * between the min and max values.
     */
    public static int clamp(int min, int value, int max) {
        if (min > max) {
            throw new IllegalArgumentException(min + " > " + max);
        }
        return Math.min(max, Math.max(value, min));
    }
}
