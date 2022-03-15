/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hac;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet that acts as a dispatcher: it forwards the request to anotehr servlet "InternalServlet"
 * or uses (include) a resource to send the response
 * Note that the URL does not change for the user!
 * @author solangekarsenty
 * http://localhost/DispatcherServlet?userName=asdas
 */
@WebServlet(urlPatterns = {"/DispatcherServlet"},
        initParams = {@WebInitParam(name="userName", value="defaultUser")},
        loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        try {
            User user = new User(firstName, lastName);
            request.setAttribute("user", user);
            request.setAttribute("message", "Welcome "); // add a message to the request

            RequestDispatcher dispatcher = request.getRequestDispatcher("/InternalServlet");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // include the index.html (form) again
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
            dispatcher.include(request, response);
        }


    }
}
