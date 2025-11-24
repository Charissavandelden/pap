package com;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

       addHtmlAndBodyTags(response);
       PrintWriter out = response.getWriter();

       out.println("<link rel='stylesheet' type='text/css' href='/pokestyle.css'>");

       // Theme-based styling for dynamic elements
       String bgColor = currentTheme.equals("dark") ? "#222" : "#fff";
       String textColor = currentTheme.equals("dark") ? "#fff" : "#000";

       out.println("<style>");
       out.println("  body { background-color: " + bgColor + " !important; color: " + textColor + " !important; }");
       out.println("  .pokedex-container { background-color: " + bgColor + "; color: " + textColor + "; }");
       out.println("  .pokemon-card { background-color: " + bgColor + "; color: " + textColor + "; }");
	   	out.println("  .pokemon-field .pokemon-value { color: " + textColor + " !important; }");
       out.println("  .pokemon-field .pokemon-label { color: " + textColor + " !important; }");
       out.println("  .pokemon-screen .pokemon-value { color: " + textColor + " !important; }");
       out.println("  .pokemon-screen .pokemon-label { color: " + textColor + " !important; }");
       out.println("  .pokedex-header { color: " + textColor + " !important; }");
       out.println("  .registration-section { background-color: " + bgColor + "; color: " + textColor + "; }");
       out.println("  .form-label { color: " + textColor + " !important; }");
       out.println("  .pokemon-field { background-color: " + (currentTheme.equals("dark") ? "rgba(50, 50, 50, 0.9)" : "rgba(255, 255, 255, 0.9)") + " !important; }");
       out.println("</style>");
       out.println("<div class='pokedex-container'>");
       out.println("  <h1 class='pokedex-header'>Preferences</h1>");
       out.println("  <div class='pokemon-card'>");
       out.println("    <div class='pokemon-info'>");
       out.println("      <div class='pokemon-screen'>");
       out.println("        <div class='pokemon-field'>");
       out.println("          <span class='pokemon-label'>Current theme:</span>");
       out.println("          <span class='pokemon-value'>" + currentTheme + "</span>");
       out.println("        </div>");
       out.println("        <div class='pokemon-field'>");
       out.println("          <span class='pokemon-label'>Current language:</span>");
       out.println("          <span class='pokemon-value'>" + currentLanguage + "</span>");
       out.println("        </div>");
       out.println("      </div>");
       out.println("    </div>");
       out.println("  </div>");
       out.println("</div>");
       out.println("<div class='registration-section'>");
       out.println("  <h2 class='pokedex-header'>Update Preferences</h2>");
       out.println("  <form method='POST' action='/preferences'>");
       out.println("    <div class='input-group'>");
       out.println("      <label class='form-label'>Theme:</label>");
       out.println("      <input type='radio' name='theme' value='light' " +
          (currentTheme.equals("light") ? "checked" : "") + "> Light");
       out.println("      <input type='radio' name='theme' value='dark' " +
          (currentTheme.equals("dark") ? "checked" : "") + "> Dark");
       out.println("    </div>");
       out.println("    <div class='input-group'>");
       out.println("      <label class='form-label' for='language'>Language:</label>");
       out.println("      <select class='form-input' name='language'>");
       out.println("        <option value='EN'" + (currentLanguage.equals("EN") ? " selected" : "") + ">English</option>");
       out.println("        <option value='NL'" + (currentLanguage.equals("NL") ? " selected" : "") + ">Nederlands</option>");
       out.println("      </select>");
       out.println("    </div>");
       out.println("    <button class='form-button' type='submit'>Save preferences</button>");
       out.println("  </form>");
       out.println("</div>");
       out.println("<div class='registration-section'>");
       out.println("  <button class='form-button' onclick=\"location.href='/welcome'\">Back to Home</button>");
       out.println("</div>");

       closeHtmlAndBodyTags(response);
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
