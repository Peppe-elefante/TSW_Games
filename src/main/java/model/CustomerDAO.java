package model;
import java.sql.*;

public class CustomerDOA {
    //Login for the user: controll if the User already exists
    public Customer loginUser(String email, String password){
        try (Connection con = ConPool.getConnection()) {
            Customer c = new Customer();
            PreparedStatement ps = con.prepareStatement("SELECT id, name, last_name, email, password, permission from customer  where email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setLastName(rs.getString(3));
                c.setEmail(rs.getString(4));
                c.setPassword(rs.getString(5));
                c.setPermission(rs.getBoolean(6));
                return c;
            }else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Creation of the account for a new User
    public boolean CreateAccount(Customer c){
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO customer(name, last_name, email, password) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getPassword());
            if(ps.executeUpdate() != 1){
                return false;
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
