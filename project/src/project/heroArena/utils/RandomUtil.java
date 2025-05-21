package project.heroArena.utils;

import java.util.Random;
import java.util.List;

public class RandomUtil {
    private static Random random = new Random();

    public static boolean chance(int percent) {
        return random.nextInt(100) < percent;
    }

    public static <T> T getRandomElement(List<T> list) {
        if (list.isEmpty()) return null;
        return list.get(random.nextInt(list.size()));
    }
}
