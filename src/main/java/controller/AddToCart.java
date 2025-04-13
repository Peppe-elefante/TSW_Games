package controller;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import model.*;

// This servlet controll if the user is already logged in and let them add games into the cart
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ssn = request.getSession(true);
        if((Customer) ssn.getAttribute("User") == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request, response);
        }
        else{

            Customer user = (Customer) ssn.getAttribute("User");
            CartDAO cart = new CartDAO();
            int userID = user.getId();
            int gameID = Integer.parseInt(request.getParameter("gameID"));
            try{
                cart.AddToCart(gameID, userID);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding game to cart");
                return;
            }


            RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.jsp");
            dispatcher.forward(request, response);
        }
    }
}
