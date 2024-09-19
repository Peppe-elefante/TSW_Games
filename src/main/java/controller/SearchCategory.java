package controller;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import model.*;
import java.util.*;

//This servlet search games by their categories
@WebServlet("/SearchCategory")
public class SearchCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("category");
        GameDOA gameSearch = new GameDOA();
        List<Game> games = gameSearch.retrieveByCategory(s);

        request.setAttribute("search", s);
        request.setAttribute("games", games);
        RequestDispatcher view = request.getRequestDispatcher("resultCategory.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
