package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Pokemon;

@WebServlet("/pokemon/name")
public class PokemonNameServlet extends HttpServlet implements AbstractServletInterface
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
		out.println("<h1>New pokemon entry</h1>");
		out.println("<h2>Choose a name:</h2>");
		out.println("<form method='POST' action='/pokemon/name'>");
		out.println("  <label>Naam: <input type='text' name='pokeName' value='" + registeringPokemon.getName() + "' required autofocus></label>");
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

		String pokeName = request.getParameter("pokeName");
		registeringPokemon.setName(pokeName);
		session.setAttribute("registeringPokemon", registeringPokemon);

		response.sendRedirect("/pokemon/type");
	}
	
	private Pokemon getRegisteringPokemon(HttpSession session)
	{
		Pokemon registeringPokemon  = (Pokemon) session.getAttribute("registeringPokemon");
		if (registeringPokemon == null)
			registeringPokemon = new Pokemon();
		return registeringPokemon;
	}
}

