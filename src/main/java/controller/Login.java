package controller;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import model.*;
import java.util.*;

//This servlet is forn the login of the User, if it goes wrong the servlet showup the "error-login.html" page
@WebServlet("/Login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Customer c;
        CustomerDAO checkLogin = new CustomerDAO();
        c = checkLogin.loginUser(email, password);

        if(c == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("error-login.html");
            dispatcher.forward(request, response);
        }else{
            HttpSession ssn = request.getSession(true);
            ssn.setAttribute("User", c);
            ssn.setAttribute("Permission", c.isPermission());

            WishlistDAO getWishlist = new WishlistDAO();
            List<Game> wishlist = new ArrayList<>();
            wishlist = getWishlist.getWishlist(c.getId());
            ssn.setAttribute("Wishlist", wishlist);

            PurchaseDAO getBGames = new PurchaseDAO();
            List<Game> boughtGames = new ArrayList<>();
            boughtGames = getBGames.ViewBoughtGames(c.getId());
            ssn.setAttribute("BoughtGames", boughtGames);


            RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.jsp");
            dispatcher.forward(request, response);
        }
    }
}
