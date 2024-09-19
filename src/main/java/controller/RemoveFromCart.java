package controller;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import model.*;
import java.util.*;

//This servlet let the User remove from the cart all the games that were in there
@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ssn = request.getSession(true);
        Customer user = (Customer) ssn.getAttribute("User");
        List<Game> cart = (List<Game>) ssn.getAttribute("Cart");
        int gameID = Integer.parseInt(request.getParameter("gameID"));
        CartDOA updateCart = new CartDOA();

        if(updateCart.RemoveFromCart(user.getId(), gameID, cart)){
            cart = updateCart.getCart(user.getId());
            ssn.setAttribute("Cart", cart);
            GetCart priceUpdate = new GetCart();
            ssn.setAttribute("Price", priceUpdate.TotalPrice(cart));
            RequestDispatcher view = request.getRequestDispatcher("view-cart.jsp");
            view.forward(request, response);
        } else{
            request.getRequestDispatcher("/WEB-INF/error404.jsp").forward(request, response);
        }
    }
}
