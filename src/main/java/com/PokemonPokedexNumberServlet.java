package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/pokedex")
public class PokemonPokedexNumberServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		addHtmlAndBodyTags(response);
		addPokemonStyling(response);
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		String pokeName = (String) session.getAttribute("pokeName");
		String pokeType = (String) session.getAttribute("pokeType");
		String pokeMove = (String) session.getAttribute("pokeMove");

		out.println("<div class='pokemon-container'>");
		out.println("<h1>Pokedex Number</h1>");
		out.println("<h2>Pokemon Overview:</h2>");
		out.println("  <label>Name: " + pokeName + " </label><br>");
		out.println("  <label>Type: " + pokeType + " </label><br>");
		out.println("  <label>Move: " + pokeMove + " </label><br>");

		out.println("<h2>Vul Pokedex number in</h2>");
		out.println("<form method='POST' action='/pokedex'>");
		out.println("  <label>Number: <input type='text' name='pokeNumber'></label><br><br>");
		out.println("  <button type='submit'>Complete Pokemon</button>");

		out.println("</form>");
		out.println("</div>");

		closeHtmlAndBodyTags(response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		addHtmlAndBodyTags(response);
		addPokemonStyling(response);
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		String pokeName = (String) session.getAttribute("pokeName");
		String pokeType = (String) session.getAttribute("pokeType");
		String pokeMove = (String) session.getAttribute("pokeMove");
		String pokeNumber = request.getParameter("pokeNumber");

		out.println("<div class='pokemon-container'>");
		out.println("<h1>Complete Pokemon Entry</h1>");
		out.println("<h2>Your Pokemon:</h2>");
		out.println("  <label>Name: " + pokeName + " </label><br>");
		out.println("  <label>Type: " + pokeType + " </label><br>");
		out.println("  <label>Move: " + pokeMove + " </label><br>");
		out.println("  <label>Pokedex Number: " + pokeNumber + " </label><br><br>");

		out.println("<a href='/welcome'>Back to my Pokedex</a>");
		out.println("</div>");
		closeHtmlAndBodyTags(response);
	}
}

