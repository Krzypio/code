package salesman;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class Driver {
    static final int NUMBER_OF_ANTS = 100;
    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    static ExecutorCompletionService<Ant> executorCompletionService = new ExecutorCompletionService<Ant>(executorService);
    private int activeAnts = 0;

    public static void main(String[] args) {
        Driver driver = new Driver();
        System.out.print("> Driver.main: enter activate ants loop ...");
        IntStream.range(1, NUMBER_OF_ANTS).forEach(x -> {
            System.out.println("\nDriver.main: executorCompletionService.submint(new Ant())");
            executorCompletionService.submit(new Ant());
            driver.activeAnts++;
            while(driver.activeAnts > 0){
                System.out.println("Driver.main: executorCompletionService.take()");
                try {
                    executorCompletionService.take();
                } catch(Exception e){
                    e.printStackTrace();
                }//exception
                driver.activeAnts--;
            }//while
        });//IntStream.range
        System.out.println("\n> Driver.main: exit activate ants loop ...");
        executorService.shutdownNow();

        /*System.out.print("TSP ACO");
        Graph<Integer, Double> graph = new SparseMultigraph<Integer, Double>(); //vertex int, edge double for distance
        for (int i=0; i<3; i++)
            graph.addVertex(i+1);

        graph.addEdge(0.1, 1,2);
        graph.addEdge(7.0,2,3);


        System.out.println("The graph g = " + graph.toString());*/
    }//main()
}//Driver class
