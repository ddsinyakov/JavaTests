package step.learning;

import com.google.inject.Guice;
import com.google.inject.Injector;
import step.learning.services.ConfigModule;
import step.learning.services.IoCDemo;

public class Main {

    public static void main(String[] args) {
        // new App().run();
        Injector injector = Guice.createInjector(new ConfigModule());

        IoCDemo app = injector.getInstance(IoCDemo.class);
        app.run();
    }
}