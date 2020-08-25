package com.hds.yarcot.util;

public class StringUtilities {

    /**
     * Makes the first character of a string uppercase
     * @param str The string that should be capitalized
     * @return The new capitalized string
     */
    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * Gets the capitalized name of a blockID (namespace:block_name)
     * @param blockId The id of the block
     * @return The capitalized name of the block (namespace:block_name -> Block Name)
     */
    public static String getBlockName(String blockId) {
        String[] blockWords = blockId.split("_");
        for (int i = 0; i < blockWords.length; i++) {
            blockWords[i] = capitalize(blockWords[i]);
        }

        return String.join(" ", blockWords);
    }
}
