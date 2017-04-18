package com.hezy.live.util;

/**
 * Created by whatisjava on 16-12-7.
 */

public class UIDUtil {

    public static String generatorUID(String uid) {
        StringBuilder sb = new StringBuilder("");
        String temp;
        if (uid.length() > 8) {
            temp = uid.substring(0, 8);
        } else {
            temp = uid;
        }
        for (int i = 0; i < temp.length(); i++) {
            if (Character.isDigit(temp.charAt(i))) {
                sb.append(temp.charAt(i));
            } else if (Character.isLetter(temp.charAt(i))) {
                sb.append(temp.charAt(i) % 10);
            }
        }
        return sb.toString();
    }

}
