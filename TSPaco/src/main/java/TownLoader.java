import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class contain static method to load towns from File
 */
public class TownLoader {
    /**
     * return a towns List created as ArrayList from initialFile source
     * @param resourcesDir directory of our fileName, can be finished with or without "/"
     * @param fileName
     * @return list of towns
     */
    protected static List<Town> get(String resourcesDir, String fileName) {
        List<Town> towns = new ArrayList<>();
        //try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(initialFile)))){
        try (BufferedReader br = Files.newBufferedReader(Paths.get(resourcesDir,"/", fileName))){
            int nTowns = Integer.parseInt(br.readLine());   //first row of a file is number of Towns
            towns = new ArrayList<>(nTowns);
            towns.addAll(br.lines().map(line -> {
                        String[] split = line.split(" ");
                        String name = split[0];
                        int x = Integer.parseInt(split[1]);
                        int y = Integer.parseInt(split[2]);
                        Town town = new Town(name, x, y);
                        return town;
                    }).collect(Collectors.toList())
            );//addAll
        } catch (IOException e){
            e.printStackTrace();
        }

        return towns;
    }
}
