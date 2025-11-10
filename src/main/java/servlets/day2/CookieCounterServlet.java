package servlets.day2;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.AbstractServletInterface;

@WebServlet("/cookie-counter")
public class CookieCounterServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
            throws IOException {
		
		int currentCount = 0;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("count") && !cookie.getValue().isEmpty())
                	currentCount = Integer.parseInt(cookie.getValue());
            }
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h1>Cookie counter demo</h1>");
        out.println("<p>Current count: " + currentCount + "</p>");
        out.println("<form method='POST'>");
        out.println("  <button name='action' value='increment'>Increment</button>");
        out.println("  <button name='action' value='reset'>Reset counter</button>");
        out.println("  <button name='action' value='clear'>Clear cookie</button>");
        out.println("</form>");
        out.println("</body></html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) 
            throws IOException {
        
        String action = request.getParameter("action");
        
        if ("clear".equals(action)) {
            for (Cookie cookie : request.getCookies()) {
                	cookie.setValue("");
                	cookie.setMaxAge(0);
            }
        	response.sendRedirect("/cookie-counter");
        	return;
        }
        
        Cookie counterCookie = getCounterCookie(request);
        int currentCount = Integer.parseInt(counterCookie.getValue());
        
        if ("increment".equals(action))
        	currentCount++;
        else if ("reset".equals(action))
        	currentCount = 0;
        
        counterCookie.setValue(String.valueOf(currentCount));
        response.addCookie(counterCookie);
        response.sendRedirect("/cookie-counter");
    }
    
    private Cookie getCounterCookie(HttpServletRequest request)
    {
    	Cookie counterCookie = new Cookie("count", "0");
    	counterCookie.setMaxAge(3600); //1 uur
    	counterCookie.setPath("/");
    	counterCookie.setHttpOnly(true);
    	Cookie[] cookies = request.getCookies();
    	if (cookies != null)
    	{
    		for (Cookie existingCookie : request.getCookies())
    			if (existingCookie.getName().equals("count"))
    				return existingCookie;
    	}
    	return counterCookie;
    }
}

