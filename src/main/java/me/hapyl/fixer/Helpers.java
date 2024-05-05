package me.hapyl.fixer;

import java.lang.reflect.Type;

public final class Helpers {

    public static String toEnumName(String string) {
        final StringBuilder builder = new StringBuilder();

        final char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            final char c = chars[i];

            if (Character.isUpperCase(c) && i != 0) {
                builder.append("_");
            }

            builder.append(Character.toUpperCase(c));
        }

        return builder.toString();
    }

}
