package infra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;

public class UserGateway {
    private final ConnectionFactory factory;
    
    public UserGateway(ConnectionFactory factory) {
        this.factory = factory;
    }

    public void store(User user){
        String sql = "insert into tb_user(email, name, passkey) values(?,?,?)";
        try {
            Connection connection = factory.createConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPasskey());
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void update(User user){
        String sql = "update tb_user set name=?, passkey=? where email=?";
        try {
            Connection connection = factory.createConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPasskey());
            ps.setString(3, user.getEmail());
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void remove(User user){
        String sql = "delete from tb_user where email=?";
        try {
            Connection connection = factory.createConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public User find(String email){
        String sql = "select * from tb_user where email = ?";
        //
        try {
            Connection connection = factory.createConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            //
            if (rs.next()){
                String e = rs.getString("email");
                String n = rs.getString("name");
                String p = rs.getString("passkey");
                User user = new User();
                user.setEmail(e);
                user.setName(n);
                user.setPasskey(p);
                return user;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        //
        return null;
    }
    
}
