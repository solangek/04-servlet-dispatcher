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
 * A servlet that acts as a dispatcher: it forwards the request
 * to another servlet "InternalServlet". While passing the request,
 * the first servlet adds a parameter to the request (setAttribute).
 * The second servlet retrieves the parameter returns the response.
 *
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

        // extract the user name from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        try {
            // build a User object
            User user = new User(firstName, lastName);
            // store User object in request
            request.setAttribute("user", user);
            // store another object in request
            request.setAttribute("message", "Welcome "); // add a message to the request

            // forward request to InternalServlet and pass the request objects
            RequestDispatcher dispatcher = request.getRequestDispatcher("/InternalServlet");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // include the index.html (form) again
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
            //dispatcher.include(request, response);

            // or (better) redirect to original form URL
            response.sendRedirect("/index.html");
        }
    }
}
