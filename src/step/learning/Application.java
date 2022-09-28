package step.learning;

public class Application
        implements Comparable<Application> {

    private Class<?> appClass;
    private Object appInstance;
    private int priority;

    // region getters and setters
    public int getPriority() {
        return priority;
    }

    public Application setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public Class<?> getAppClass() {
        return appClass;
    }

    public Application setAppClass(Class<?> appClass) {
        this.appClass = appClass;
        return this;
    }

    public Object getAppInstance() {
        return appInstance;
    }

    public Application setAppInstance(Object appInstance) {
        this.appInstance = appInstance;
        return this;
    }
    // endregion

    public Application() {
        setAppInstance(null);
    }

    @Override
    public int compareTo(Application o) {
        return o.getPriority() - this.getPriority();
    }
}
