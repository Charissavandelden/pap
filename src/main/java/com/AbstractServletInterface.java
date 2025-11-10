package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface AbstractServletInterface {

	default void redirectIfNotLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession(false);
		//Redirect to registration/login page if not (fully) logged in.
		if (session == null || (session != null && session.getAttribute("username") == null))
			response.sendRedirect("/register");
	}

	default String encodeString(String message)
	{
		return URLEncoder.encode(message, StandardCharsets.UTF_8);
	}

	default String decodeString(String message)
	{
		return URLDecoder.decode(message, StandardCharsets.UTF_8);
	}

	default HttpServletResponse addHtmlAndBodyTags(HttpServletResponse response) throws IOException
	{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><body>");

        return response;
	}

	default HttpServletResponse closeHtmlAndBodyTags(HttpServletResponse response) throws IOException
	{
		response.getWriter().println("<script src=\"script.js\"></script>");
		response.getWriter().println("</body></html>");
		return response;
	}

	default void addThemedPokemonStyling(HttpServletResponse response, HttpServletRequest request) throws IOException
	{
		PrintWriter out = response.getWriter();

		// Read theme from cookies
		String currentTheme = "light";
		javax.servlet.http.Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (javax.servlet.http.Cookie cookie : cookies) {
				if (cookie.getName().equals("theme")) {
					currentTheme = cookie.getValue();
					break;
				}
			}
		}

		// Theme-based colors
		String bgColor = currentTheme.equals("dark") ? "#222" : "#f0f0f0";
		String containerBg = currentTheme.equals("dark") ? "#333" : "white";
		String textColor = currentTheme.equals("dark") ? "#fff" : "#333";
		String borderColor = currentTheme.equals("dark") ? "#555" : "#333";

		out.println("<style>");
		out.println("  body { text-align: center; font-family: Arial, sans-serif; background-color: " + bgColor + "; color: " + textColor + "; padding: 20px; }");
		out.println("  .pokemon-container { border: 3px solid " + borderColor + "; border-radius: 10px; padding: 30px; max-width: 400px; margin: 0 auto; background-color: " + containerBg + "; }");
		out.println("  h1 { color: #dc3545; margin-bottom: 20px; }");
		out.println("  h2 { color: " + textColor + "; margin-bottom: 15px; }");
		out.println("  label { display: block; margin: 10px 0; color: " + textColor + "; }");
		out.println("  input { padding: 5px; margin: 5px; background-color: " + containerBg + "; color: " + textColor + "; border: 1px solid " + borderColor + "; }");
		out.println("  button { padding: 10px 20px; margin: 5px; background-color: #dc3545; color: white; border: none; border-radius: 5px; cursor: pointer; }");
		out.println("  button:hover { background-color: #c82333; }");
		out.println("  a { color: #dc3545; text-decoration: none; }");
		out.println("  a:hover { text-decoration: underline; }");
		out.println("</style>");
	}

	default HttpServletResponse addThemedWelcomeStyling(HttpServletResponse response, HttpServletRequest request) throws IOException
	{
		PrintWriter out = response.getWriter();

		// Read theme from cookies
		String currentTheme = "light";
		javax.servlet.http.Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (javax.servlet.http.Cookie cookie : cookies) {
				if (cookie.getName().equals("theme")) {
					currentTheme = cookie.getValue();
					break;
				}
			}
		}

		// Theme-based colors
		String bgColor = currentTheme.equals("dark") ? "#222" : "#f0f0f0";
		String containerBg = currentTheme.equals("dark") ? "#333" : "white";
		String textColor = currentTheme.equals("dark") ? "#fff" : "#333";
		String borderColor = currentTheme.equals("dark") ? "#555" : "#333";

		out.println("<style>");
		out.println("  body { text-align: center; font-family: Arial, sans-serif; background-color: " + bgColor + "; color: " + textColor + "; padding: 20px; }");
		out.println("  .pokemon-container { border: 3px solid " + borderColor + "; border-radius: 10px; padding: 30px; max-width: 400px; margin: 0 auto; background-color: " + containerBg + "; }");
		out.println("  h1 { color: #dc3545; margin-bottom: 20px; }");
		out.println("  h2 { color: " + textColor + "; margin-bottom: 15px; }");
		out.println("  label { display: block; margin: 10px 0; color: " + textColor + "; }");
		out.println("  input { padding: 5px; margin: 5px; background-color: " + containerBg + "; color: " + textColor + "; border: 1px solid " + borderColor + "; }");
		out.println("  button { padding: 10px 20px; margin: 5px; background-color: #dc3545; color: white; border: none; border-radius: 5px; cursor: pointer; }");
		out.println("  button:hover { background-color: #c82333; }");
		out.println("  a { color: #dc3545; text-decoration: none; }");
		out.println("  a:hover { text-decoration: underline; }");
		// WelcomeServlet-specific overrides
		out.println("  .pokedex-container { background-color: " + containerBg + " !important; color: " + textColor + " !important; }");
		out.println("  .pokemon-card { background-color: " + containerBg + " !important; color: " + textColor + " !important; }");
		out.println("  .pokemon-screen { background-color: " + (currentTheme.equals("dark") ? "#444" : "#f8f9fa") + " !important; }");
		out.println("  .registration-section { background-color: " + containerBg + " !important; color: " + textColor + " !important; }");
		out.println("  .pokemon-label { color: " + textColor + " !important; }");
		out.println("  .pokedex-header { color: " + textColor + " !important; }");
		out.println("</style>");

		return response;
	}

}
