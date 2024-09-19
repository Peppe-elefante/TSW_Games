package controller;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import model.*;
import java.sql.SQLException;
import java.util.*;

//This servlet take all the game data, and pass them to the admin page
@WebServlet("/CreateAdmin")
public class CreateAdmin extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GameDOA getGames = new GameDOA();
        ServletContext cont = getServletContext();
        String contextPath = cont.getRealPath("/Games.txt");
        System.out.println(contextPath);
        try {
            List<Game> games = getGames.BoughtGameList();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(contextPath))){
                for(Game game: games){
                    writer.write(game.getName()+ " has been bought " + game.getQuantity() + " times.");
                    writer.newLine();
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("administrator-page.jsp");
        dispatcher.forward(request, response);
    }
}
