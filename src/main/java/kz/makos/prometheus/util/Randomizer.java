package kz.makos.prometheus.util;

import lombok.experimental.UtilityClass;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class Randomizer {

    public static long getRandomSeconds(Integer min, Integer max) {
        // Генерация случайного числа от min до max включительно
        return ThreadLocalRandom.current().nextLong(min, max + 1L) * 100L;
    }

    public static int getRandomHttpStatusCode() {
        // Генерация случайного числа от 200 до 599 включительно
        return ThreadLocalRandom.current().nextInt(200, 600);
    }
}
