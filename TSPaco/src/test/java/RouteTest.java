import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteTest {
    @Test
    public void isEdgeTest1(){
        Town t1 = new Town("first",0,0);
        Town t2 = new Town("second",0,0);
        Town t3 = new Town("third",0,0);
        Route route = new Route(3);
        route.add(t1);
        route.add(t2);
        route.add(t3);
        assertEquals(true, route.isEdge(t1, t2));
    }

    @Test
    public void isEdgeTest2(){
        Town t1 = new Town("first",0,0);
        Town t2 = new Town("second",0,0);
        Town t3 = new Town("third",0,0);
        Route route = new Route(3);
        route.add(t1);
        route.add(t2);
        route.add(t3);
        assertEquals(false, route.isEdge(t1, t3));
    }

    @Test
    public void isEdgeTest3(){
        Town t1 = new Town("first",0,0);
        Town t2 = new Town("second",0,0);
        Town t3 = new Town("third",0,0);
        Route route = new Route(3);
        route.add(t1);
        route.add(t2);
        route.add(t3);
        assertEquals(true, route.isEdge(t2, t3));
    }

    @Test
    public void isEdgeTest4(){
        Town t1 = new Town("first",0,0);
        Town t2 = new Town("second",0,0);
        Town t3 = new Town("third",0,0);
        Route route = new Route(3);
        route.add(t1);
        route.add(t2);
        route.add(t3);
        assertEquals(true, route.isEdge(t3, t1));
    }
}
