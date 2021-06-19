import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static List<Town> towns;
    private static int nTowns;

    public static void main(String[] args) throws IOException {
        System.out.print("Traveling Salesman Problem - Ant Colony Optimization\n");

        //load towns
        List<Town> towns = TownLoader.get("src/main/resources", "berlin52.txt");
        nTowns = towns.size();

        System.out.print(towns);
    }
}
