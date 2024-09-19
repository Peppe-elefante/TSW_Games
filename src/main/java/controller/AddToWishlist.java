package controller;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import model.*;
import java.util.*;

//This servlet controlls if the User is logged in and let them add games into the wishlist
@WebServlet("/AddToWishlist")
public class AddToWishlist extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ssn = request.getSession(true);
        if((Customer) ssn.getAttribute("User") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request, response);
        }
        else{
            Customer user = (Customer) ssn.getAttribute("User");
            WishlistDOA getWishlist = new WishlistDOA();
            List<Game> wishlist = new ArrayList<>();
            int userID = user.getId();
            int gameID = Integer.parseInt(request.getParameter("gameID"));
            getWishlist.addToWishlist(gameID,userID);
            wishlist = getWishlist.getWishlist(userID);
            ssn.setAttribute("Wishlist", wishlist);

            RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.jsp");
            dispatcher.forward(request, response);
        }
    }
}
