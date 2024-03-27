package io.github.colindj1120.enhancedfx.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EFXStringUtils {
    private EFXStringUtils() {}

    public enum IndentationLevel {
        LEVEL_0(0),
        LEVEL_1(4),
        LEVEL_2(8),
        LEVEL_3(12),
        LEVEL_4(16),
        LEVEL_5(20),
        LEVEL_6(24),
        LEVEL_7(28),
        LEVEL_8(32),
        LEVEL_9(36);

        private final int numberOfSpaces;

        IndentationLevel(int numberOfSpaces) {
            this.numberOfSpaces = numberOfSpaces;
        }

        public int getNumberOfSpaces() {
            return numberOfSpaces;
        }
    }

    public static String addSpacesToEveryLine(String input, IndentationLevel indentationLevel) {
        String space = " ".repeat(indentationLevel.getNumberOfSpaces());

        return Arrays.stream(input.split("\\n", -1))
                     .map(line -> space + line)
                     .collect(Collectors.joining("\n"));
    }
}
