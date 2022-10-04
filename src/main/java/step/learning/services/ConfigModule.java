package step.learning.services;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import step.learning.services.hash.HashService;
import step.learning.services.hash.MD5HashService;
import step.learning.services.random.RandomProvider;
import step.learning.services.hash.Sha1HashService;
import step.learning.services.symbol.CharService;
import step.learning.services.symbol.SymbolService;
import step.learning.services.random.RandomProviderMax;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SymbolService.class)
                .to(CharService.class);

        bind(HashService.class)
                .annotatedWith(Names.named("128"))
                .to(MD5HashService.class);

        bind(HashService.class)
                .annotatedWith(Names.named("160"))
                .to(Sha1HashService.class);

        bind(String.class)
                .annotatedWith(Names.named("MsConnectionString"))
                .toInstance("Ms Connection String...");

        bind(String.class)
                .annotatedWith(Names.named("OracleConnectionString"))
                .toInstance("Oracle Connection String...");
    }

    @Provides
    RandomProvider getRandomProvider() {
        return new RandomProviderMax();
    }
}
