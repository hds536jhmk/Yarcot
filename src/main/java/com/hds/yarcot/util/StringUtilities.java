package com.hds.yarcot.util;

public class StringUtilities {

    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String getBlockName(String blockId) {
        String[] blockWords = blockId.split("_");
        for (int i = 0; i < blockWords.length; i++) {
            blockWords[i] = capitalize(blockWords[i]);
        }

        return String.join(" ", blockWords);
    }
}
