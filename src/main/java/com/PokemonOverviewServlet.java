package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Pokemon;

@WebServlet("/pokemon/overview")
public class PokemonOverviewServlet extends HttpServlet implements AbstractServletInterface
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
		out.println("<h2>Pokemon Overview:</h2>");
		out.println("  <label>Name: " + registeringPokemon.getName() + " </label>");
		out.println("  <label>Type: " + registeringPokemon.getType() + " </label>");
		out.println("  <label>Move: " + registeringPokemon.getMove() + " </label>");
		out.println("  <label>Pokedex number: " + registeringPokemon.getPokedexNumber() + " </label>");

		out.println("<h2>Is this correct?</h2>");
		out.println("<form method='POST' action='/pokemon/overview'>");
		out.println("  <button type='submit' name='action' value='complete'>Complete Pokemon</button>");
		out.println("  <button type='submit' name='action' value='edit'>Edit Pokemon</button>");

		out.println("</form>");
		out.println("</div>");

		closeHtmlAndBodyTags(response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if(action.equals("complete"))
		{
			session.setAttribute("pokemon", getRegisteringPokemon(session));
			session.setAttribute("registeringPokemon", null);
			response.sendRedirect("/welcome");
		}
		else
			response.sendRedirect("/pokemon/name");
	}
	
	private Pokemon getRegisteringPokemon(HttpSession session)
	{
		return (Pokemon) session.getAttribute("registeringPokemon");
	}
}

