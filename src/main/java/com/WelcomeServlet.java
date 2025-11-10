package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Pokemon;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		redirectIfNotLoggedIn(request, response);
		addHtmlAndBodyTags(response);
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);

		out.println("<link rel='stylesheet' type='text/css' href='/pokestyle.css'>");

		out.println("<h1 class='pokedex-header'>Welcome Page</h1>");
		String username = (String) session.getAttribute("username");
		if (username != null)
			out.println("<h2 class='pokedex-header'>Hello " + username + "!</h2>");
		
		Pokemon pokemon = getPokemon(session);

		// Show pokedex with static pokemon
		out.println("<div class='pokedex-container'>");
		out.println("  <h2 class='pokedex-header'>My Pokedex</h2>");

		// Pokemon
		out.println("  <div class='pokemon-card'>");
		out.println("    <h3>Latest registration:</h3>");
		out.println("    <div class='pokemon-info'>");

		// Left screen
		out.println("      <div class='pokemon-screen'>");
		out.println("        <div class='pokemon-field'>");
		out.println("          <span class='pokemon-label'>Name:</span>");
		out.println("          <span class='pokemon-value'>" + pokemon.getName() + "</span>");
		out.println("        </div>");
		out.println("        <div class='pokemon-field'>");
		out.println("          <span class='pokemon-label'>Type:</span>");
		out.println("          <span class='pokemon-value type-electric'>" + pokemon.getType() + "</span>");
		out.println("        </div>");
		out.println("      </div>");

		// Right screen
		out.println("      <div class='pokemon-screen'>");
		out.println("        <div class='pokemon-field'>");
		out.println("          <span class='pokemon-label'>Pokedex Number:</span>");
		out.println("          <span class='pokemon-value'>" + pokemon.getPokedexNumber() + "</span>");
		out.println("        </div>");
		out.println("        <div class='pokemon-field'>");
		out.println("          <span class='pokemon-label'>Favorite Move:</span>");
		out.println("          <span class='pokemon-value'>" + pokemon.getMove() + "</span>");
		out.println("        </div>");
		out.println("      </div>");

		out.println("    </div>");
		out.println("  </div>");

		out.println("</div>");

		out.println("<div class='registration-section'>");
		out.println("  <h2>Go to pokemon registration:</h2>");
		out.println("  <form method='POST' action='/welcome'>");
		out.println("    <button type='submit'>Start</button>");
		out.println("  </form>");
		out.println("</div>");

		closeHtmlAndBodyTags(response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		response.sendRedirect("/pokemon/name");
	}
	
	private Pokemon getPokemon(HttpSession session)
	{
		Pokemon pokemon = (Pokemon) session.getAttribute("pokemon");
		//Create pikachu if there is no registered pokemon in the session
		if (pokemon == null)
			pokemon = createPikachu();
		return pokemon;
	}
	
	private Pokemon createPikachu()
	{
		Pokemon pikachu = new Pokemon();
		pikachu.setName("Pikachu");
		pikachu.setType("Electric");
		pikachu.setMove("Thunder Bolt");
		pikachu.setPokedexNumber(25);
		
		return pikachu;
	}
}
