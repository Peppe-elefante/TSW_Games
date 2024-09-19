package controller;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import java.io.IOException;

// This servlet let clean the cart
@WebServlet("/EmptyCart")
public class EmptyCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartDOA emptycart = new CartDOA();
        HttpSession ssn = request.getSession(true);
        Customer user = (Customer) ssn.getAttribute("User");
        emptycart.EmptyCart(user.getId());

        RequestDispatcher view = request.getRequestDispatcher("home-page.jsp");
        view.forward(request, response);
    }
}
