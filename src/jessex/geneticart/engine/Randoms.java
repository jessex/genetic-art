package jessex.geneticart.engine;

import java.util.Random;

public class Randoms {

    private static Random random = new Random();


    public static int randomInt(int max) {
        return random.nextInt(max);
    }

    public static int randomInt(int min, int max) {
        return random.nextInt(max-min) + min;
    }

    public static float randomFloat() {
        return random.nextFloat();
    }

    public static boolean checkRatio(float rate) {
        return random.nextFloat() < rate;
    }

}
