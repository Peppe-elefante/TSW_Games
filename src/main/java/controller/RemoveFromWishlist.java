package controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.WishlistDOA;
import model.Customer;
import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//This servlet let the User remove the game that are in the wishlist
@WebServlet("/RemoveFromWishlist")
public class RemoveFromWishlist extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ssn = request.getSession(true);
        Customer user = (Customer) ssn.getAttribute("User");
        int gameID = Integer.parseInt(request.getParameter("gameID"));
        WishlistDOA updateWishlist = new WishlistDOA();
        List<Game> wishlist = new ArrayList<Game>();

        updateWishlist.RemoveFromWishlist(user.getId(), gameID);
        wishlist = updateWishlist.getWishlist(user.getId());
        ssn.setAttribute("Wishlist", wishlist);

        RequestDispatcher view = request.getRequestDispatcher("profile-page.jsp");
        view.forward(request, response);

    }
}
