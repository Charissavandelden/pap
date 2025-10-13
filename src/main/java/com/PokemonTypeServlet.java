package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/type")
public class PokemonTypeServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		addHtmlAndBodyTags(response);
		PrintWriter out = response.getWriter();

		String name = request.getParameter("pokeName");
		String type = request.getParameter("pokeType");

		out.println("<h1>Formulier Demo</h1>");

		out.println("<h2>Vul type in</h2>");
		out.println("<label> " + name + " </label>");
		out.println("<form method='GET' action='/move'>");
		out.println("  <label>Naam: <input type='disabled' name='pokeName' value=" + name +" ></label><br><br>");
		out.println("  <label>Type: <input type='disabled' name='pokeType'></label><br><br>");
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
}

