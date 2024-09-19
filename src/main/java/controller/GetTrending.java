package controller;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import model.*;
import java.util.*;

//This servlet find all the treanding games
@WebServlet("/GetTrending")
public class GetTrending extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GameDOA gameSearch = new GameDOA();
        HttpSession ssn = request.getSession(true);
        List<Game> games = gameSearch.getTrending();

        ssn.setAttribute("games", games);
        RequestDispatcher view = request.getRequestDispatcher("home-page.jsp");
        view.forward(request, response);
    }
}
