package com;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/preferences")
public class PreferencesServlet extends HttpServlet implements AbstractServletInterface
{

    private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request,
          HttpServletResponse response)
          throws IOException
    {
       //Default values
       String currentTheme = "light";
       String currentLanguage = "EN";

       Cookie[] cookies = request.getCookies();
       if (cookies != null) {
          for (Cookie cookie : cookies) {
             if (cookie.getName().equals("theme")) {
                currentTheme = cookie.getValue();
             }
             else if (cookie.getName().equals("language")) {
                currentLanguage = cookie.getValue();
             }
          }
       }

       response.setContentType("text/html");
       response.setCharacterEncoding("UTF-8");
       PrintWriter out = response.getWriter();

       // Theme-based styling
       String bgColor = currentTheme.equals("dark") ? "#222" : "#fff";
       String textColor = currentTheme.equals("dark") ? "#fff" : "#000";

       out.println("<!DOCTYPE html>");
       out.println("<html>");
       out.println("<head>");
       out.println("  <style>");
       out.println("    body { background-color: " + bgColor + "; color: " + textColor + "; font-family: Arial, sans-serif; padding: 20px; }");
       out.println("    label { display: block; margin: 10px 0; }");
       out.println("    button { margin-top: 15px; padding: 10px 20px; cursor: pointer; }");
       out.println("  </style>");
       out.println("</head>");
       out.println("<body>");
       out.println("<h1>Preferences</h1>");
       out.println("Current theme: " + currentTheme + "<br>");
       out.println("Current language: " + currentLanguage + "<br>");
       out.println("<br>");
       out.println("<form method='POST' action='/preferences'>");
       out.println("  <label>Theme: ");
       out.println("    <input type='radio' name='theme' value='light' " +
          (currentTheme.equals("light") ? "checked" : "") + "> Light");
       out.println("    <input type='radio' name='theme' value='dark' " +
          (currentTheme.equals("dark") ? "checked" : "") + "> Dark");
       out.println("  </label>");
       out.println("  <label for='language'>Language: ");
       out.println("    <select name='language'>");
       out.println("      <option value='EN'>English</option>");
       out.println("      <option value='NL'>Nederlands</option>");
       out.println("    </select>");
       out.println("  </label>");
       out.println("  <button type='submit'>Save preferences</button>");
       out.println("</form>");
       out.println("<hr>");
       out.println("<button onclick=\"location.href='/welcome'\">Home</button>");
       out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
          HttpServletResponse response)
          throws IOException {

       String newTheme = request.getParameter("theme");
       String newlanguage = request.getParameter("language");

       Cookie themeCookie = new Cookie("theme", newTheme);
       themeCookie.setMaxAge(86400); // 24 uur
       themeCookie.setPath("/");
       themeCookie.setHttpOnly(true);
       response.addCookie(themeCookie);

       Cookie languageCookie = new Cookie("language", newlanguage);
       languageCookie.setMaxAge(86400); // 24 uur
       languageCookie.setPath("/");
       languageCookie.setHttpOnly(true);
       response.addCookie(languageCookie);

       // TODO: Redirect terug naar GET
       response.sendRedirect("/preferences");
    }
}
