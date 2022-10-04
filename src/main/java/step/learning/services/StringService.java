package step.learning.services;

import step.learning.services.random.RandomProvider;
import step.learning.services.symbol.SymbolService;

import javax.inject.Inject;

public class StringService {

    @Inject
    RandomProvider randomProvider;
    @Inject
    SymbolService symbolService;

    public String getString() {
        return "Imposter " + symbolService.getChar() + "! " + randomProvider.getN();
    }
}

