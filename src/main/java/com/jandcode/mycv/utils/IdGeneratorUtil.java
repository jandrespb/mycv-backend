package com.jandcode.mycv.utils;

import java.util.concurrent.ThreadLocalRandom;

public final class IdGeneratorUtil {

    private IdGeneratorUtil() {
        // evitar instanciación
    }

    public static String generateRandomId() {
        int random = ThreadLocalRandom.current().nextInt(1, 101); // 1–100
        return String.valueOf(random);
    }
}
