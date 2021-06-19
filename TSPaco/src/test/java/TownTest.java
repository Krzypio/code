import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TownTest {
    @Test
    public void distanceTest1(){
        final Town t1 = new Town("first", 0, 0);
        final Town t2 = new Town("second", 3, 4);
        assertEquals(5, t1.measureDistance(t2));
    }
}
