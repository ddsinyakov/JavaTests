package step.learning.services;

import javax.inject.Inject;

public class StringService {

    @Inject
    RandomProvider randomProvider;

    public String getString() {
        return "Made in \uD83C\uDDFA\uD83C\uDDE6 " + randomProvider.getN();
    }
}

