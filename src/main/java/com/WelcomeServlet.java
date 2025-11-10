package com;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import entities.Pokemon;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		redirectIfNotLoggedIn(request, response);
		addHtmlAndBodyTags(response);
		addThemedWelcomeStyling(response, request);
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);

		out.println("<link rel='stylesheet' type='text/css' href='/pokestyle.css'>");

		out.println("<h1 class='pokedex-header'>Welcome Page</h1>");
		String username = (String) session.getAttribute("username");
		if (username != null)
			out.println("<h2 class='pokedex-header'>Hello " + username + "!</h2>");

		Pokemon pokemon = getPokemon(session);

		// Show pokedex with static pokemon
		out.println("<div class='pokedex-container' id='pokedex' style='display:block'>");
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
		out.println("          <span class='pokemon-value'></span>");

		out.println("        </div>");
		out.println("        <div class='pokemon-field'>");
		out.println("          <span class='pokemon-label'>Type:</span>");
		out.println("          <span class='pokemon-value type-electric'>" + pokemon.getType() + "</span>");
		out.println("          <span class='pokemon-value type-electric'></span>");

		out.println("        </div>");
		out.println("      </div>");

		// Right screen
		out.println("      <div class='pokemon-screen'>");
		out.println("        <div class='pokemon-field'>");
		out.println("          <span class='pokemon-label'>Pokedex Number:</span>");
		out.println("          <span class='pokemon-value'>" + pokemon.getPokedexNumber() + "</span>");
		out.println("          <span class='pokemon-value'></span>");

		out.println("        </div>");
		out.println("        <div class='pokemon-field'>");
		out.println("          <span class='pokemon-label'>Favorite Move:</span>");
		out.println("          <span class='pokemon-value'>" + pokemon.getMove() + "</span>");
		out.println("          <span class='pokemon-value'></span>");

		out.println("        </div>");
		out.println("      </div>");

		out.println("    </div>");
		out.println("  </div>");

		out.println("</div>");

		out.println("<div class='registration-section' id='stap1'>");
		out.println("  <h2>Go to pokemon registration:</h2>");
		out.println("  <form id='registratie-form'>");
		out.println("    <button type='submit' id='registratie-button'>Start</button>");
		out.println("  </form>");
		out.println("  <br>");
		out.println("  <a href='/preferences'>⚙️ Theme Settings</a>");
		out.println("</div>");

		out.println("<div class='pokemon-container' id='stap2' style='display:none'>");
		out.println("<h1>New pokemon entry</h1>");
		out.println("<h2>Choose a name:</h2>");
		out.println("<form id='name-form' >");
//		out.println("  <label>Naam: <input type='text' name='pokeName' value='" + registeringPokemon.getName() + "' required autofocus></label>");
		out.println("  <button type='submit'>Next</button>");
		out.println("</form>");
		out.println("</div>");

		out.println("<div class='pokemon-container' id='stap3' style='display:none'>");
		out.println("<h1>Pokemon Type</h1>");
//		out.println("<h2>Choose type for " + registeringPokemon.getName() + "</h2>");
		out.println("<h2>Choose type for </h2>");
		out.println("<form id='type-form'>");
//		out.println("  <label>Type: <input type='text' name='pokeType' value='" + registeringPokemon.getType() + "' required autofocus></label>");
		out.println("  <label>Type: <input type='text' name='pokeType' value='' required autofocus></label>");
		out.println("  <button type='submit'>Next</button>");
		out.println("</form>");
		out.println("</div>");

		out.println("<div class='pokemon-container' id='stap4' style='display:none'>");
		out.println("<h1>Move Pagina</h1>");
//		out.println("<h2>Choose move for " + registeringPokemon.getName() + " (" + registeringPokemon.getType() + ")</h2>");
		out.println("<h2>Choose move for  ()</h2>");
		out.println("<form id='move-form'>");
		out.println("<label>Move: <input type='text' name='pokeMove' value='' required autofocus></label>");
		out.println("<button type='submit'>Next</button>");
		out.println("</form>");
		out.println("</div>");

		out.println("<div class='pokemon-container' id='stap5' style='display:none'>");
		out.println("<h1>Pokedex Number</h1>");
		out.println("<h2>Vul Pokedex number in</h2>");
		out.println("<form id='pokedex-form'>");
//		out.println("  <label>Number: <input type='number' name='pokeNumber' value='" + registeringPokemon.getPokedexNumber() + "' required autofocus></label>");
		out.println("  <label>Number: <input type='number' name='pokeNumber' value='' required autofocus></label>");
		out.println("  <button type='submit'>Show overview</button>");
		out.println("</form>");
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
