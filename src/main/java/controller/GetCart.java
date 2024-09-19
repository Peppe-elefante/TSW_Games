package controller;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import model.*;
import java.util.*;

//This servlet drop all the info of the cart
@WebServlet("/GetCart")
public class GetCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ssn = request.getSession(true);
        if((Customer) ssn.getAttribute("User") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request, response);
        }
        else{
            Customer user = (Customer) ssn.getAttribute("User");
            List<Game> cart = new ArrayList<>();
            CartDOA getCart = new CartDOA();
            cart = getCart.getCart(user.getId());
            ssn.setAttribute("Cart", cart);
            ssn.setAttribute("Price", TotalPrice(cart));
            RequestDispatcher view = request.getRequestDispatcher("view-cart.jsp");
            view.forward(request, response);
        }
    }

    public double TotalPrice(List<Game> cart){
        double priceTotal = 0;
        for(Game game : cart){
            priceTotal += game.getPrice() * game.getQuantity();
        }
        return priceTotal;
    }
}
