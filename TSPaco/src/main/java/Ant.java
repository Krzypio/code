import java.sql.Driver;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Ant {
    //It chooses the town to go with a probability that is a function of the town distance and of the amount of trail present on the connecting edge
    //to force the ant to make legal tours, transitions to already visited towns are disallowed until a tour is completed (this is controlled by a tabu list)
    //when it completes a tour, it lays a substance called trail on each edge (i,j) visited.
    private AntColonyOptimization aco;
    private HashMap<String, Boolean> visitedTabu;
    private Route route;
    private int actualTown;
    private int mAntsTotal; //total number of ants
    private int chooseNextTown(/*int actualTown, HashMap<Integer, Boolean> visitedTabu*/){
        //Each ant at time t chooses the next town, where it will be at time t+1
        //Therefore, if we call an iteration of the AS algorithm the m movies carried out by the m ants in the interval (t, t+1),
        //the every n iterations of the algorithm (which we call a cycle)
        return 0;
    }

    public Ant(AntColonyOptimization aco, int startTownName) {
        this.aco = aco;
        int nTowns = aco.getTowns().size();
        route = new Route(nTowns);
        visitedTabu = new HashMap<>(nTowns);
        IntStream.range(0, nTowns).forEach(x -> visitedTabu.put(aco.getTowns().get(x).getName(), false));
        //ant in starting town
        route.add(aco.getTowns().get(startTownName));
        visitedTabu.put(aco.getTowns().get(startTownName).getName(), true);
    }

    public Route getRoute() {
        return route;
    }
}
