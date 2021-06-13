import org.junit.jupiter.api.Test;
import salesman.City;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class CityTest {

    @Test
    public void distanceTest1(){
        final City city1 = new City(1, 0, 0);
        final City city2 = new City(2, 3, 4);
        assertEquals(5, city1.measureDistance(city2));
    }
}
