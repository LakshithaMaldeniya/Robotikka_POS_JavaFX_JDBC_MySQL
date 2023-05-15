package com.devstack.pos.util;

import java.util.Random;

public class QrDataGenerator {
    private final static String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@#$%&*";

    public static String generateQr(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(ALPHA.charAt(new Random().nextInt(42)));
        }
        return stringBuilder.toString();
    }
}

