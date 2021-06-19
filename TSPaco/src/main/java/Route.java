import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Route {
    private int actualIndex = -1;
    private double distance = 0;

    private int nTowns;
    private ArrayList<Town> towns;

    public Route(int nTowns){
     this.nTowns = nTowns;
     this.towns = new ArrayList<>(nTowns);
    }

    public void add(Town town){
        towns.add(town);
        actualIndex++;
        if(actualIndex > 0)
            distance += town.measureDistance( towns.get(actualIndex-1) );
        if(actualIndex == nTowns-1)
            distance += town.measureDistance( towns.get(0) );
    }

    public ArrayList<Town> getTowns() {
        return towns;
    }

    public double getDistance() {
        return distance;
    }

    public String toString(){
        return Arrays.toString(towns.toArray()) + " | " + distance;
    }

    public boolean isEdge(Town t1, Town t2){
        int t1Index = towns.indexOf(t1);
        int t2Index = towns.indexOf(t2);
        if ((t1Index+1)%nTowns == t2Index)  //if second index is just after firs or last->0
            return true;
        else
            return false;
    }
}
