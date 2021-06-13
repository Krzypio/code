package salesman;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CityLoader {
    protected static ArrayList<City> get(){
        ArrayList<City> cities = new ArrayList<>();
        cities.addAll(Stream.of(
                "1 565 575",
                "2 25 185",
                "3 345 750",
                "4 945 685",
                "5 845 655",
                "6 880 660",
                "7 25 230",
                "8 525 1000",
                "9 580 1175",
                "10 650 1130",
                "11 1605 620",
                "12 1220 580",
                "13 1465 200",
                "14 1530 5",
                "15 845 680",
                "16 725 370",
                "17 145 665",
                "18 415 635",
                "19 510 875",
                "20 560 365",
                "21 300 465",
                "22 520 585",
                "23 480 415",
                "24 835 625",
                "25 975 580",
                "26 1215 245",
                "27 1320 315",
                "28 1250 400",
                "29 660 180",
                "30 410 250",
                "31 420 555",
                "32 575 665",
                "33 1150 1160",
                "34 700 580",
                "35 685 595",
                "36 685 610",
                "37 770 610",
                "38 795 645",
                "39 720 635",
                "40 760 650",
                "41 475 960",
                "42 95 260",
                "43 875 920",
                "44 700 500",
                "45 555 815",
                "46 830 485",
                "47 1170 65",
                "48 830 610",
                "49 605 625",
                "50 595 360",
                "51 1340 725",
                "52 1740 245")
                .map(line -> {
                    String[] split = line.split(" ");
                    City city = new City(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                    return city;
                }).collect(Collectors.toList())
        );//ArrayList.initialization
        return cities;
    }//get()
}
