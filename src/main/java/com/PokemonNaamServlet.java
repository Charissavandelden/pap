package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pokename")
public class PokemonNaamServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		addHtmlAndBodyTags(response);
		PrintWriter out = response.getWriter();

		out.println("<h1>Formulier Demo</h1>");

		out.println("<h2>Pokemon naam</h2>");
		out.println("<form method='GET' action='/type'>");
		out.println("  <label>Naam: <input type='text' name='pokeName'></label><br><br>");
		out.println("  <button type='submit'>Verzenden (GET)</button>");

		out.println("</form>");
		closeHtmlAndBodyTags(response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		addHtmlAndBodyTags(response);
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");

		out.println("<h1>Formulier Demo</h1>");

		out.println("<h2>Placeholder h2</h2>");
		out.println("<form method='POST' action='/pokename'>");
		out.println("  <label>Naam: <input type='text' name='pokemonNaam'></label><br><br>");
		out.println("  <button type='submit'>Verzenden (POST)</button>");

		out.println("</form>");
		closeHtmlAndBodyTags(response);
	}

//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws IOException
//	{
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//
//		String name = request.getParameter("name");
//
//		out.println("<!DOCTYPE html>");
//		out.println("<html><body>");
//		out.println("<h1>Formulier Demo</h1>");
//
//		out.println("<h2>Placeholder h2</h2>");
//		out.println("<form method='POST' action='/pokename'>");
//		out.println("  <label>Naam: <input type='text' name='pokemonNaam'></label><br><br>");
//		out.println("  <button type='submit'>Verzenden (POST)</button>");
//
//		out.println("</form>");
//		out.println("</body></html>");
//	}
}

