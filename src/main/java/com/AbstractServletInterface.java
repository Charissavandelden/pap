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
		response.getWriter().println("</body></html>");
		return response;
	}

	default HttpServletResponse addPokemonStyling(HttpServletResponse response) throws IOException
	{
		PrintWriter out = response.getWriter();

		out.println("<style>");
		out.println("  body { text-align: center; font-family: Arial, sans-serif; background-color: #f0f0f0; padding: 20px; }");
		out.println("  .pokemon-container { border: 3px solid #333; border-radius: 10px; padding: 30px; max-width: 400px; margin: 0 auto; background-color: white; }");
		out.println("  h1 { color: #dc3545; margin-bottom: 20px; }");
		out.println("  h2 { color: #333; margin-bottom: 15px; }");
		out.println("  label { display: block; margin: 10px 0; }");
		out.println("  input { padding: 5px; margin: 5px; }");
		out.println("  button { padding: 10px 20px; margin: 5px; background-color: #dc3545; color: white; border: none; border-radius: 5px; cursor: pointer; }");
		out.println("  button:hover { background-color: #c82333; }");
		out.println("</style>");

		return response;
	}

}
