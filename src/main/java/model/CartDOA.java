package model;
import java.sql.*;
import java.util.*;

public class CartDOA {
    // Take the game data and then, add them into the database
    public boolean AddToCart(int gameID, int userID){
        try (Connection con = ConPool.getConnection()) {
            CallableStatement cs = con.prepareCall("{call add_to_cart(?, ?, ?)}");
            cs.setInt(1, gameID);
            cs.setInt(2, userID);
            cs.setInt(3, 1);

            boolean hasResultSet = cs.execute();

            if (!hasResultSet) {
                return true;
            } else {
                ResultSet rs = cs.getResultSet();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding item to cart: " + e.getMessage(), e);
        }
    }

    //Take the game in the user cart from the database and return them
    public List<Game> getCart(int customer_id){
        try (Connection con = ConPool.getConnection()) {


            List<Game> gameRes = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT game_id,quantity from shopping_cart where customer_id = ?");
            ps.setInt(1, customer_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Game p = new Game();
                GameDOA getGame = new GameDOA();
                int id = rs.getInt(1);
                int quantity = rs.getInt(2);
                p = getGame.getByID(id);
                p.setQuantity(quantity);
                gameRes.add(p);
            }
            return gameRes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Delete one game from the User cart
    public boolean RemoveFromCart(int userID, int gameID, List<Game> cart){
        try (Connection con = ConPool.getConnection()){
            for(Game cartGame : cart){
                if(cartGame.getId() == gameID){
                    String queryString = new String();
                    if(cartGame.getQuantity() == 1) {
                        queryString = "DELETE FROM shopping_cart WHERE game_id = ? and customer_id = ?";
                    }else{
                        queryString = "UPDATE shopping_cart set quantity = quantity- 1 where game_id = ? and customer_id = ?";
                    }
                    PreparedStatement ps = con.prepareStatement(queryString);
                    ps.setInt(1, cartGame.getId());
                    ps.setInt(2, userID);
                    int rs = ps.executeUpdate();
                    return true;
                }
            }
            return false;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Delete all the games from the User cart
    public void EmptyCart(int userID){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("DELETE FROM shopping_cart WHERE  customer_id = ?");
            ps.setInt(1, userID);
            int rs = ps.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
