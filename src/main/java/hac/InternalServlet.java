package hac;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * this servlet is not accessed directly.
 *
 * A servlet reading parameters:
 * the parameters are passed by another Servlet (DispatcherServlet_
 * It receives the request parameter "userName" and a request attribute "someparam"
 * from the Dispatcher and returns a response to the client.
 *
 */
@WebServlet(name = "Internal Servlet", urlPatterns = "/InternalServlet")
public class InternalServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try  {
            // read the information passed by the DispatcherServlet
            User u = (User) request.getAttribute("user");
            // build the response
            out.println("<h1>" + request.getAttribute("message") + " " + u.getName() + "</h1>");
        } catch (Exception e) {
            // if some param is missing
            out.println("<h1>Hello World</h1>");
        } finally {
            out.close();
        }
    }
}
