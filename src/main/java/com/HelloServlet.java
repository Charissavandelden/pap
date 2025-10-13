package com;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html><body>");
		out.println("<h1>Formulier Demo</h1>");

		// GET Form - data komt in URL
		out.println("<h2>1. GET Form (data in URL)</h2>");
		out.println("<form method='GET' action='/form'>");
		out.println("  <label>Naam: <input type='text' name='name'></label><br><br>");
		out.println("  <label>Email: <input type='email' name='email'></label><br><br>");
		out.println("  <button type='submit'>Verzenden (GET)</button>");
		out.println("</form>");

		out.println("<hr>");
	}
}

