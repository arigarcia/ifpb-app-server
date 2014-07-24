package infra;

import junit.framework.TestCase;
import domain.Coordinate;
import domain.Route;

public class RouteGatewayTest extends TestCase {

    public void testSaveRoute(){
        ConnectionFactory factory = new ConnectionFactory();
        RouteGateway gateway = new RouteGateway(factory);
        //
        Integer id = gateway.newIdentity();
        Route route =  new Route(id);
        Coordinate[] coordinates = new Coordinate[2];
        coordinates[0] = new Coordinate(11111, 22222);
        coordinates[1] = new Coordinate(33333, 33333);
        route.setCoordinates(coordinates);
        route.setTime(System.currentTimeMillis());
        //
        gateway.store(route);
        //
        Route route1 = gateway.find(id);
        assertNotNull(route1);
        assertEquals(2, route1.getCoordinates().length);
        //
//        gateway.remove(route1);
//        Route route2 = gateway.find(id);
//        assertNull(route2);
    }
}
