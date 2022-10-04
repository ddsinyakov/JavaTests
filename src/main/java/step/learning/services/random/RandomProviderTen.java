package step.learning.services.random;

import java.util.Random;

public class RandomProviderTen implements RandomProvider {
    private final int n = new Random().nextInt(10);

    public int getN() {
        return n;
    }
}
