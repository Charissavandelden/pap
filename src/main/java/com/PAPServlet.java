package com;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import entities.Pokemon;

@WebServlet("/pap")
public class PAPServlet extends HttpServlet implements AbstractServletInterface
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

		// Get current language
		String language = getCurrentLanguage(request);

		out.println("<h1 class='pokedex-header'>" + getText("welcome", language) + " Page</h1>");
		String username = (String) session.getAttribute("username");
		if (username != null)
			out.println("<h2 class='pokedex-header'>" + getText("hello_trainer", language) + " " + username + "!</h2>");

		Pokemon pokemon = getPokemon(session);

		// Show pokedex with static pokemon
		out.println("<div class='pokedex-container' id='pokedex' style='display:block'>");
		out.println("  <h2 class='pokedex-header'>" + getText("my_pokedex", language) + "</h2>");

		// Pokemon
		out.println("  <div class='pokemon-card'>");
		out.println("    <h3>Latest registration:</h3>");
		out.println("    <div class='pokemon-info'>");

		// Left screen
		out.println("      <div class='pokemon-screen'>");
		out.println("        <div class='pokemon-field'>");
		out.println("          <span class='pokemon-label'>" + getText("name", language) + ":</span>");
		out.println("          <span class='pokemon-value'>" + pokemon.getName() + "</span>");
		out.println("        </div>");
		out.println("        <div class='pokemon-field'>");
		out.println("          <span class='pokemon-label'>" + getText("type", language) + ":</span>");
		out.println("          <span class='pokemon-value type-electric'>" + pokemon.getType() + "</span>");

		out.println("        </div>");
		out.println("      </div>");

		// Right screen
		out.println("      <div class='pokemon-screen'>");
		out.println("        <div class='pokemon-field'>");
		out.println("          <span class='pokemon-label'>" + getText("pokedex_number", language) + ":</span>");
		out.println("          <span class='pokemon-value'>" + pokemon.getPokedexNumber() + "</span>");
		out.println("        </div>");
		out.println("        <div class='pokemon-field'>");
		out.println("          <span class='pokemon-label'>" + getText("favorite_move", language) + ":</span>");
		out.println("          <span class='pokemon-value'>" + pokemon.getMove() + "</span>");

		out.println("        </div>");
		out.println("      </div>");

		out.println("    </div>");
		out.println("  </div>");

		out.println("</div>");

		out.println("<div class='registration-section' id='stap1'>");
		out.println("  <h2>" + getText("pokemon_registration", language) + ":</h2>");
		out.println("  <form id='registratie-form'>");
		out.println("    <button type='submit' id='registratie-button'>Start</button>");
		out.println("  </form>");
		out.println("  <br>");
		out.println("  <a href='/preferences'>⚙️ " + getText("preferences", language) + "</a>");
		out.println("  <br><br>");
		out.println("  <div style='text-align: center;'>");
		out.println("    <form method='POST' action='/pap' style='display: inline;'>");
		out.println("      <input type='hidden' name='action' value='changeLanguage'>");
		out.println("      <label>" + getText("language", language) + ": ");
		out.println("        <select name='language' onchange='this.form.submit()'>");
		out.println("          <option value='en'" + (language.equals("en") ? " selected" : "") + ">English</option>");
		out.println("          <option value='nl'" + (language.equals("nl") ? " selected" : "") + ">Nederlands</option>");
		out.println("        </select>");
		out.println("      </label>");
		out.println("    </form>");
		out.println("  </div>");
		out.println("</div>");

		out.println("<div class='pokedex-container' id='stap2' style='display:none'>");
		out.println("  <h1 class='pokedex-header'>" + getText("new_pokemon_entry", language) + "</h1>");
		out.println("  <div class='pokemon-card'>");
		out.println("    <h3>" + getText("choose_name", language) + ":</h3>");
		out.println("    <div class='registration-section'>");
		out.println("      <form id='name-form'>");
		out.println("        <div class='input-group'>");
		out.println("          <label class='form-label'>" + getText("pokemon_name", language) + ":</label>");
		out.println("          <input class='form-input' type='text' name='pokeName' value='' required autofocus>");
		out.println("        </div>");
		out.println("        <button class='form-button' type='submit'>" + getText("next", language) + "</button>");
		out.println("      </form>");
		out.println("    </div>");
		out.println("  </div>");
		out.println("</div>");

		out.println("<div class='pokedex-container' id='stap3' style='display:none'>");
		out.println("  <h1 class='pokedex-header'>" + getText("pokemon_type", language) + "</h1>");
		out.println("  <div class='pokemon-card'>");
		out.println("    <h3>" + getText("choose_type", language) + "</h3>");
		out.println("    <div class='registration-section'>");
		out.println("      <form id='type-form'>");
		out.println("        <div class='input-group'>");
		out.println("          <label class='form-label'>" + getText("type", language) + ":</label>");
		out.println("          <input class='form-input' type='text' name='pokeType' value='' required autofocus>");
		out.println("        </div>");
		out.println("        <button class='form-button' type='submit'>" + getText("next", language) + "</button>");
		out.println("      </form>");
		out.println("    </div>");
		out.println("  </div>");
		out.println("</div>");

		out.println("<div class='pokedex-container' id='stap4' style='display:none'>");
		out.println("  <h1 class='pokedex-header'>" + getText("pokemon_move", language) + "</h1>");
		out.println("  <div class='pokemon-card'>");
		out.println("    <h3>" + getText("choose_move", language) + "</h3>");
		out.println("    <div class='registration-section'>");
		out.println("      <form id='move-form'>");
		out.println("        <div class='input-group'>");
		out.println("          <label class='form-label'>" + getText("move", language) + ":</label>");
		out.println("          <input class='form-input' type='text' name='pokeMove' value='' required autofocus>");
		out.println("        </div>");
		out.println("        <button class='form-button' type='submit'>" + getText("next", language) + "</button>");
		out.println("      </form>");
		out.println("    </div>");
		out.println("  </div>");
		out.println("</div>");

		out.println("<div class='pokedex-container' id='stap5' style='display:none'>");
		out.println("  <h1 class='pokedex-header'>" + getText("pokedex_number", language) + "</h1>");
		out.println("  <div class='pokemon-card'>");
		out.println("    <h3>" + getText("enter_pokedex_number", language) + "</h3>");
		out.println("    <div class='registration-section'>");
		out.println("      <form id='pokedex-form'>");
		out.println("        <div class='input-group'>");
		out.println("          <label class='form-label'>" + getText("number", language) + ":</label>");
		out.println("          <input class='form-input' type='number' name='pokeNumber' value='' required autofocus>");
		out.println("        </div>");
		out.println("        <button class='form-button' type='submit'>" + getText("show_overview", language) + "</button>");
		out.println("      </form>");
		out.println("    </div>");
		out.println("  </div>");
		out.println("</div>");

		closeHtmlAndBodyTags(response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		String action = request.getParameter("action");

		if ("changeLanguage".equals(action)) {
			String newLanguage = request.getParameter("language");
			Cookie languageCookie = new Cookie("language", newLanguage.toUpperCase());
			languageCookie.setMaxAge(86400); // 24 hours
			languageCookie.setPath("/");
			languageCookie.setHttpOnly(true);
			response.addCookie(languageCookie);
			response.sendRedirect("/pap");
		} else {
			response.sendRedirect("/pokemon/name");
		}
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
