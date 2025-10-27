package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/move")
public class PokemonMoveServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		addHtmlAndBodyTags(response);
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		out.println("<h1>move pagina</h1>");
		String pokeName = (String) session.getAttribute("pokeName");
		if (pokeName != null)
			out.println("<h2>Hallo " + pokeName + "</h2>");

		String name = request.getParameter("pokeName");
		String type = request.getParameter("pokeType");

		out.println("<h1>Formulier Demo</h1>");

		out.println("<h2>Vul Move in</h2>");
		out.println("<label> " + name + type + " </label>");
		out.println("<form method='GET' action='/pokedex'>");
		out.println("  <label>Naam: <input type='text' name='pokeName' value=" + name +" ></label><br><br>");
		out.println("  <label>Type: <input type='text' name='pokeType' value=" + type +" ></label><br><br>");
		out.println("  <label>Move: <input type='text' name='pokeMove'></label><br><br>");
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

