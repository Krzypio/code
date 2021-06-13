package salesman;

import java.util.concurrent.Callable;

public class Ant implements Callable<Ant> {
    private Route route = null;
    public Route getRoute(){return route;};
    @Override
    public Ant call() throws Exception {
        System.out.println("Ant.call() invoked ...");
        return null;
    }
}
