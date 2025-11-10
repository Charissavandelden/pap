package servlets.day2;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.AbstractServletInterface;

@WebServlet("/theme-demo")
public class ThemeServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, 
                        HttpServletResponse response) 
            throws IOException {
        
        // TODO: Lees "theme" cookie
        String currentTheme = "black"; // default
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("theme") && !cookie.getValue().isEmpty())
                	currentTheme = cookie.getValue();
            }
        }
        
        addHtmlAndBodyTags(response);
		PrintWriter out = response.getWriter();
		out.println("<h1>Theme Demo</h1>");

		// TODO: Genereer HTML met current theme
		out.println("<p> Current theme:</p>");
		out.println("<p style='color:" + currentTheme + "'>" + currentTheme + " </p>");
		
		// TODO: Toon form met light/dark selector
		out.println("<h2>Vul je gewenste theme in:</h2>");
		out.println("<form method='POST' action='/theme'>");
		out.println("  <label>Black: <input type='radio' name='theme' value='black'></label><br>");
		out.println("  <label>Red: <input type='radio' name='theme' value='red'></label><br>");
		out.println("  <label>Blue: <input type='radio' name='theme' value='blue'></label><br>");
		out.println("  <label>Violet: <input type='radio' name='theme' value='violet'></label><br>");
		out.println("  <br>");
		out.println("  <button type='submit'>Verzenden (POST)</button>");
		out.println("</form>");
		
		out.println("<form method='POST' action='/theme'>");
		out.println("  <label>Reset: <input type='checkbox' name='reset' /></label>");
		out.println("  <button type='submit'>Reset all the cookies!</button>");
		out.println("</form>");
		
		closeHtmlAndBodyTags(response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, 
                         HttpServletResponse response) 
            throws IOException {
        
    	// Clear and expire all cookies
    	if (request.getParameter("reset") != null && request.getCookies() != null)
    	{
    		for (Cookie cookie : request.getCookies())
    		{
    			cookie.setValue("");
    			cookie.setMaxAge(0);
    		}
            response.sendRedirect("/theme");
    	}
    	
        // TODO: Lees nieuwe theme uit form
        String newTheme = request.getParameter("theme");
        
        // TODO: Maak cookie en zet deze
        Cookie themeCookie = new Cookie("theme", newTheme);
        themeCookie.setMaxAge(86400); // 24 uur
        themeCookie.setPath("/theme");
        themeCookie.setHttpOnly(true);
        response.addCookie(themeCookie);
        
        // TODO: Redirect terug naar GET
        response.sendRedirect("/theme");
    }
}

