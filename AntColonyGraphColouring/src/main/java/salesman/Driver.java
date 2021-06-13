package salesman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Driver {
    private int antIdDesirableLength = 0;

    static final int NUMBER_OF_ANTS = 100;
    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    static ExecutorCompletionService<Ant> executorCompletionService = new ExecutorCompletionService<Ant>(executorService);
    private Route shortestRoute = null;
    private int activeAnts = 0;

    protected static ArrayList<City> initialRoute = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print("> " + NUMBER_OF_ANTS + " Artifical Ants ...");
        initialRoute = CityLoader.get();
        Driver driver = new Driver();
        driver.printHeading();
        AntColonyOptimization aco = new AntColonyOptimization();
        //jesli activeAnts = 0 to podnieś semafor, inaczej zamknięty
        //---------------------------------------------------------------------
        IntStream.range(0, NUMBER_OF_ANTS).forEach(x -> {
            executorCompletionService.submit(new Ant(aco, x + 1, 1));
            driver.activeAnts++;
        });//IntStream.range
        driver.processAnts();
        //---------------------------------------------------------------------
        driver.awaitTerminationAfterShutdown(executorService);
        executorService.shutdownNow();

        System.out.println("\nOptimal Route : " + Arrays.toString(driver.shortestRoute.getCities().toArray()));
        System.out.println("\tDistance : " + String.format("%,.2f", driver.shortestRoute.getDistance()) );
    }//main()

    private void processAnts(){
        while(activeAnts > 0){
            try {
                Ant ant = executorCompletionService.take().get();
                Route antRoute = ant.getRoute();
                if(shortestRoute == null || antRoute.getDistance() < shortestRoute.getDistance()){
                    shortestRoute = antRoute;
                    StringBuffer sb = new StringBuffer();
                    sb.append(ant.getAntNum());
                    for (int x=Integer.toString(ant.getAntNum()).length(); x<antIdDesirableLength; x++) sb.append(" ");
                    sb.append( "| " + String.format("%,.2f", antRoute.getDistance()));
                    System.out.println(sb.toString());
                }//if
            } catch(Exception e){
                e.printStackTrace();
            }//exception
            activeAnts--;
        }//while
    }//processAnts()

    private void printHeading(){
        String antIdHeading = "\nAnt #";
        this.antIdDesirableLength = Math.max(antIdHeading.length(), Integer.toString(NUMBER_OF_ANTS).length());
        System.out.print(antIdHeading);
        for (int x=antIdHeading.length(); x<antIdDesirableLength; x++) System.out.print(" ");
        System.out.println(" | Distance");
    }//printHeading()

    private void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        if(threadPool == null){
            System.out.print("Driver - awaitTerminationAfterShutdown: DANO PUSTY THREAD_POOL");
            return;
        }
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
                System.out.print("*************************SHUTDOWN THREAD********************");
            }//if
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }//catch
    }//awaitTerminationAfterShutdown()

}//Driver class
