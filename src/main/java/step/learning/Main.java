package step.learning;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {
        // new App().run();
        Injector injector = Guice.createInjector();

        App app = injector.getInstance(App.class);
        app.runDemo();
    }
}