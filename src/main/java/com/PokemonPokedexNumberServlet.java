package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Pokemon;

@WebServlet("/pokedex")
public class PokemonPokedexNumberServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		redirectIfNotLoggedIn(request, response);
		addHtmlAndBodyTags(response);
		addPokemonStyling(response);
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		Pokemon registeringPokemon = getPokemon(session);

		out.println("<div class='pokemon-container'>");
		out.println("<h1>Pokedex Number</h1>");
		out.println("<h2>Pokemon Overview:</h2>");
		out.println("  <label>Name: " + registeringPokemon.getName() + " </label><br>");
		out.println("  <label>Type: " + registeringPokemon.getType() + " </label><br>");
		out.println("  <label>Move: " + registeringPokemon.getMove() + " </label><br>");

		out.println("<h2>Vul Pokedex number in</h2>");
		out.println("<form method='POST' action='/pokedex'>");
		out.println("  <label>Number: <input type='number' name='pokeNumber'></label><br><br>");
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
		Pokemon registeringPokemon = getPokemon(session);
		registeringPokemon.setPokedexNumber(Integer.parseInt(request.getParameter("pokeNumber")));
		session.setAttribute("pokemon", registeringPokemon);

		out.println("<div class='pokemon-container'>");
		out.println("<h1>Complete Pokemon Entry</h1>");
		out.println("<h2>Your Pokemon:</h2>");
		out.println("  <label>Name: " + registeringPokemon.getName() + " </label><br>");
		out.println("  <label>Type: " + registeringPokemon.getType() + " </label><br>");
		out.println("  <label>Move: " + registeringPokemon.getMove() + " </label><br>");
		out.println("  <label>Pokedex Number: " + registeringPokemon.getPokedexNumber() + " </label><br><br>");

		out.println("<a href='/welcome'>Back to my Pokedex</a>");
		out.println("</div>");
		closeHtmlAndBodyTags(response);
	}
	
	private Pokemon getPokemon(HttpSession session)
	{
		return (Pokemon) session.getAttribute("pokemon");
	}
}

