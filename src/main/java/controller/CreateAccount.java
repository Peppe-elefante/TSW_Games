package controller;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.*;
import model.*;

//This servlet take the data and create a new User in the database
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Customer c = new Customer(name, lastName, email, password);
        CustomerDAO create = new CustomerDAO();


        if(create.CreateAccount(c)){
            HttpSession ssn = request.getSession(true);
            ssn.setAttribute("User", c);
            RequestDispatcher dispatcher = request.getRequestDispatcher("home-page.jsp");
            dispatcher.forward(request, response);
        }
        else{
            out.println("<html><body>");
            out.println("<p>ERROR: INVALID INPUT</p>");
            out.println("</html></body>");
        }
    }
}
