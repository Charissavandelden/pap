package servlets.day2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.AbstractServletInterface;

@WebServlet("/counter")
public class CounterServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
            throws IOException {
        
        // TODO: Haal (of maak) session
        HttpSession session = request.getSession(true);
        
        // TODO: Haal counter uit session (of start bij 0)
        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) {
            counter = 0;
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h1>Session counter</h1>");
        out.println("<p>Current count: " + counter + "</p>");
        out.println("<p>Session ID: " + session.getId() + "</p>");
        out.println("<form method='POST'>");
        out.println("  <button name='action' value='increment'>Increment</button>");
        out.println("  <button name='action' value='reset'>Reset</button>");
        out.println("  <button name='action' value='clear'>Clear session</button>");
        out.println("</form>");
        out.println("</body></html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) 
            throws IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        if ("clear".equals(action)) {
        	session.invalidate();
        	response.sendRedirect("/counter");
        	return;
        }
        
        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) counter = 0;
        
        // TODO: Implementeer increment/reset
        if ("increment".equals(action)) {
        	counter++;
        } else if ("reset".equals(action)) {
        	counter = null;
        }
        // TODO: Sla counter op in session
        session.setAttribute("counter", counter);
        // TODO: Redirect naar GET
        response.sendRedirect("/counter");
    }
}

