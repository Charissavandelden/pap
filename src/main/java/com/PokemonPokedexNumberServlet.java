package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Pokemon;

@WebServlet("/pokemon/pokedex")
public class PokemonPokedexNumberServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		redirectIfNotLoggedIn(request, response);
		addHtmlAndBodyTags(response);
		addThemedPokemonStyling(response, request);
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		Pokemon registeringPokemon = getRegisteringPokemon(session);

		out.println("<div class='pokemon-container'>");
		out.println("<h1>Pokedex Number</h1>");

		out.println("<h2>Vul Pokedex number in</h2>");
		out.println("<form method='POST' action='/pokemon/pokedex'>");
		out.println("  <label>Number: <input type='number' name='pokeNumber' value='" + registeringPokemon.getPokedexNumber() + "' required autofocus></label>");
		out.println("  <button type='submit'>Show overview</button>");

		out.println("</form>");
		out.println("</div>");

		closeHtmlAndBodyTags(response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		HttpSession session = request.getSession();

		Pokemon registeringPokemon = getRegisteringPokemon(session);
		registeringPokemon.setPokedexNumber(Integer.parseInt(request.getParameter("pokeNumber")));
		session.setAttribute("registeringPokemon", registeringPokemon);

		response.sendRedirect("/pokemon/overview");
	}

	private Pokemon getRegisteringPokemon(HttpSession session)
	{
		return (Pokemon) session.getAttribute("registeringPokemon");
	}
}

