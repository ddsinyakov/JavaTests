package step.learning.services;

import step.learning.anno.DemoClass;
import step.learning.anno.EntryPoint;
import step.learning.services.hash.HashService;
import step.learning.services.random.RandomProvider;
import step.learning.services.random.RandomProviderMax;

import javax.inject.Inject;
import javax.inject.Named;

@DemoClass(priority = 7)
public class IoCDemo {

    @Inject @Named("MsConnectionString")
    private String MsConnectionString;
    @Inject @Named("OracleConnectionString")
    private String OracleConnectionString;

    private final StringService stringService;
    private final TimeService timeService;
    private final RandomProvider randomProvider;
    private final HashService hash128;
    private final HashService hash160;

    @Inject
    public IoCDemo(TimeService timeService,
                   StringService stringService,
                   RandomProvider randomProvider,
                   @Named("128") HashService hash128,
                   @Named("160") HashService hash160) {
        this.randomProvider = randomProvider;
        this.stringService = stringService;
        this.timeService = timeService;
        this.hash128 = hash128;
        this.hash160 = hash160;
    }

    @EntryPoint
    public void run() {
        System.out.println("IoC Demo");
        System.out.println("String Service: " + stringService.getString());
        System.out.println("Random number: " + randomProvider.getN());

        System.out.println("Current data: " + timeService.getDate());
        System.out.println("Current time: " + timeService.getTime());

        System.out.println("HashService (128bit): " + hash128.hash("Hello"));
        System.out.println("HashService (160bit): " + hash160.hash("Hello"));

        System.out.println("Oracle connection String: " + OracleConnectionString);
        System.out.println("MS connection String: " + MsConnectionString);
    }
}
