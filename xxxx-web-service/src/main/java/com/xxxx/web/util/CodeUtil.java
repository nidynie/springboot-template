package com.xxxx.web.util;

import java.util.Random;

public class CodeUtil {

    public static int NUMBERS[] = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 6, 7, 8, 9};


    private CodeUtil() {
    }

    public static String getRandomValidateCode(int length) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(19);
            int current = NUMBERS[index];
            builder.append(String.valueOf(current));
        }
        return builder.toString();
    }

    public static void main(String args[]) {
        long current = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println(getRandomValidateCode(6));
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - current));

    }
}
