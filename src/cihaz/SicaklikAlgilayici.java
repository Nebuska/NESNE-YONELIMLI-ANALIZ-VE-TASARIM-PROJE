package cihaz;

import java.util.Random;

public class SicaklikAlgilayici implements ISicaklikAlgilayici {
    @Override
    public float GetSicaklik() {
        Random random = new Random();
        return random.nextFloat() + random.nextInt(9) + 20;
    }
}
