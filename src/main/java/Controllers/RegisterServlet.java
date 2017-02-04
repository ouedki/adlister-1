package Controllers;

import Models.DataAccessLayer.DaoFactory;
import Models.DataAccessLayer.Users;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by David on 2/4/17.
 */
@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/users/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Users newUser = DaoFactory.getUsersDao();

        // TODO: ensure the submitted information is valid
        // TODO: create a new user based off of the submitted information
        // TODO: if a user was successfully created, send them to their profile
        if (!request.getParameter("username").isEmpty() & request.getParameter("email").contains("@") & request
                .getParameter("email").contains(".") & !request.getParameter("password").isEmpty()) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            newUser.insert(new User(username, email, password));
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/register");
        }
    }
}
