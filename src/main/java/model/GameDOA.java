package model;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDOA {

    //Take game data form the database with the game ID
    public Game getByID(int ID){
        try (Connection con = ConPool.getConnection()) {
            Game p = new Game();
            PreparedStatement ps = con.prepareStatement("SELECT id, game_name, price, category_category from game  where id = ?");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {

                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice( rs.getInt(3));
                p.setCategory(rs.getString(4));
            }

            return p;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Return a list of games by their categories
    public List<Game> retrieveByCategory(String category) {
        try (Connection con = ConPool.getConnection()) {
            List<Game> gameRes = new ArrayList<Game>();
            PreparedStatement ps = con.prepareStatement("SELECT id, game_name, price, category_category from game  where category_category = ?");
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Game p = new Game();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice( rs.getInt(3));
                p.setCategory(rs.getString(4));
                gameRes.add(p);
            }

            return gameRes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Return in a List all the game that are in treanding
    public List<Game> getTrending(){
        try (Connection con = ConPool.getConnection()) {
            List<Game> gameRes = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT id, game_name, price, category_category, is_trending from game where is_trending = true ");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Game p = new Game();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getInt(3));
                p.setCategory(rs.getString(4));
                gameRes.add(p);
            }

            return gameRes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Insert a new game into the database
    public void saveGame(Game game) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO game (game_name, price, category_category) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, game.getName());
            ps.setDouble(2, game.getPrice());
            ps.setString(3, game.getCategory());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            game.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Return the ID of the most bought game
    public int MostBoughtGame() throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            List<Game> boughtGames = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT game_id, quantity from purchase_game");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int gameID = rs.getInt(1), quantity = rs.getInt(2);
                if(CheckGame(boughtGames, gameID)){
                    Game p = new Game();
                    p = getByID(gameID);
                    p.setQuantity(quantity);
                     boughtGames.add(p);
                } else{
                    for(Game game: boughtGames){
                        if(game.getId() == gameID){
                            game.setQuantity(game.getQuantity() + quantity);
                        }
                    }
                }

            }
            return HighestQuantityGame(boughtGames);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    //Check if the game exists in the list
    private boolean CheckGame(List<Game> games, int gameID){
        for(Game game: games){
            if(game.getId() == gameID)
                return false;
        }
        return true;
    }

    //Return the ID of the game with the highest quantity
    private int HighestQuantityGame(List<Game> games){
        int quantity = 0, result = 0;
        for(Game game: games){
            if (game.getQuantity() > quantity){
                result = game.getId();
                quantity = game.getQuantity();
            }
        }
        return result;
    }

    //Return the list of all the bought games
    public List<Game> BoughtGameList() throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            List<Game> boughtGames = new ArrayList<>();
            PreparedStatement ps = con.prepareStatement("SELECT game_id, quantity from purchase_game");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int gameID = rs.getInt(1), quantity = rs.getInt(2);
                if(CheckGame(boughtGames, gameID)){
                    Game p = new Game();
                    p = getByID(gameID);
                    p.setQuantity(quantity);
                    boughtGames.add(p);
                } else{
                    for(Game game: boughtGames){
                        if(game.getId() == gameID){
                            game.setQuantity(game.getQuantity() + quantity);
                        }
                    }
                }

            }
            return boughtGames;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

