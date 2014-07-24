package infra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Coordinate;
import domain.Route;

public class RouteGateway {
    private final ConnectionFactory factory;
    
    public Integer newIdentity(){
        String sql = "select max(id) from tb_route";
        try {
            Connection connection = factory.createConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                return rs.getInt(1) + 1;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
    
    public RouteGateway(ConnectionFactory factory) {
        this.factory = factory;
    }

    public void store(Route route){
        String sql1 = "insert into tb_route(id, dat) values(?,?)";
        String sql2 = "insert into tb_route_coordinate(id, latitude, longitude) " +
        		"values(?,?,?)";
        try {
            Connection connection = factory.createConnection();
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setInt(1, route.getIdentity());
            ps1.setLong(2, route.getTime());
            if (ps1.executeUpdate() > 0){
                for (int i = 0; i < route.getCoordinates().length; i++) {
                    PreparedStatement ps2 = connection.prepareStatement(sql2);
                    ps2.setInt(1, route.getIdentity());
                    ps2.setLong(2, route.getCoordinates()[i].getLatitude());
                    ps2.setLong(3, route.getCoordinates()[i].getLongitude());
                    ps2.execute();
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void remove(Route route){
        String sql1 = "delete from tb_route where id=?";
        String sql2 = "delete from tb_route_coordinate where id=?";
        try {
            Connection connection = factory.createConnection();
            PreparedStatement ps1 = connection.prepareStatement(sql2);
            ps1.setInt(1, route.getIdentity());
            if (ps1.executeUpdate() > 0){
                PreparedStatement ps2 = connection.prepareStatement(sql1);
                ps2.setInt(1, route.getIdentity());
                ps2.execute();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Route find(Integer identity){
        String sql = "select a.id, a.dat, b.longitude, b.latitude " +
        		"from tb_route a join tb_route_coordinate b " +
        		"on a.id = b.id where a.id = ?";
        try {
            Connection connection = factory.createConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, identity);
            ResultSet rs = ps.executeQuery();
            //
            if (rs.next()){
                //
                Route route = new Route(identity);
                route.setTime(rs.getLong("dat"));
                //
                rs.last();
                int count = rs.getRow();
                rs.beforeFirst();
                //
                Coordinate[] coordinates = new Coordinate[count];
                for (int i = 0; i < count; i++) {
                    //
                    rs.next();
                    //
                    Long latitude = rs.getLong("latitude");
                    Long longitude = rs.getLong("longitude");
                    //
                    Coordinate c = new Coordinate(latitude, longitude);
                    coordinates[i] = c;
                }
                route.setCoordinates(coordinates);
                //
                return route;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;        
    }

}
