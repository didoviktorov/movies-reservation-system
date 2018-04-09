package org.movies.system.utils;

public class EscapeCharacters {

    private static final String[] SPECIAL_CHARACTERS =
            {"&", "<",">", "\"", "'"};

    private static final String[] REPLACEMENTS =
            {"&amp;", "&lt;", "&gt;", "&quot;", " &#39;"};

    public static String escape(String str) {
        for (int i = 0; i < SPECIAL_CHARACTERS.length; i++) {
            str = str.replaceAll(SPECIAL_CHARACTERS[i], REPLACEMENTS[i]);
        }

        return str;
    }

    public static String unescape(String str) {
        for (int i = 0; i < SPECIAL_CHARACTERS.length; i++) {
            str = str.replaceAll(REPLACEMENTS[i], SPECIAL_CHARACTERS[i]);
        }

        return str;
    }

}
