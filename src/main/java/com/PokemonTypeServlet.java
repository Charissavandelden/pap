package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Pokemon;

@WebServlet("/pokemon/type")
public class PokemonTypeServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		redirectIfNotLoggedIn(request, response);
		addHtmlAndBodyTags(response);
		addPokemonStyling(response);
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		Pokemon registeringPokemon = getRegisteringPokemon(session);

		out.println("<div class='pokemon-container'>");
		out.println("<h1>Pokemon Type</h1>");

		out.println("<h2>Choose type for " + registeringPokemon.getName() + "</h2>");
		out.println("<form method='POST' action='/pokemon/type'>");
		out.println("  <label>Type: <input type='text' name='pokeType' value='" + registeringPokemon.getType() + "' required autofocus></label><br><br>");
		out.println("  <button type='submit'>Next</button>");

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
		registeringPokemon.setType(request.getParameter("pokeType"));
		session.setAttribute("registeringPokemon", registeringPokemon);

		response.sendRedirect("/pokemon/move");
	}
	
	private Pokemon getRegisteringPokemon(HttpSession session)
	{
		return (Pokemon) session.getAttribute("registeringPokemon");
	}
}

