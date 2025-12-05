package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface AbstractServletInterface {

	default void redirectIfNotLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession(false);
		//Redirect to registration/login page if not (fully) logged in.
		if (session == null || (session != null && session.getAttribute("username") == null))
			response.sendRedirect("/register");
	}

	default String encodeString(String message)
	{
		return URLEncoder.encode(message, StandardCharsets.UTF_8);
	}

	default String decodeString(String message)
	{
		return URLDecoder.decode(message, StandardCharsets.UTF_8);
	}

	default HttpServletResponse addHtmlAndBodyTags(HttpServletResponse response) throws IOException
	{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><body>");

        return response;
	}

	default HttpServletResponse closeHtmlAndBodyTags(HttpServletResponse response) throws IOException
	{
		response.getWriter().println("<script src=\"script.js\"></script>");
		response.getWriter().println("</body></html>");
		return response;
	}

	default String getCurrentLanguage(HttpServletRequest request) {
		String language = "en"; // Default language
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("language")) {
					language = cookie.getValue().toLowerCase();
					break;
				}
			}
		}
		return language;
	}

	default String getText(String key, String language) {
		switch (key) {
			case "welcome":
				return language.equals("nl") ? "Welkom" : "Welcome";
			case "hello_trainer":
				return language.equals("nl") ? "Hallo trainer" : "Hello trainer";
			case "my_pokedex":
				return language.equals("nl") ? "Mijn Pokedex" : "My Pokedex";
			case "name":
				return language.equals("nl") ? "Naam" : "Name";
			case "type":
				return language.equals("nl") ? "Type" : "Type";
			case "pokedex_number":
				return language.equals("nl") ? "Pokedex Nummer" : "Pokedex Number";
			case "favorite_move":
				return language.equals("nl") ? "Favoriete Aanval" : "Favorite Move";
			case "pokemon_registration":
				return language.equals("nl") ? "Ga naar pokemon registratie" : "Go to pokemon registration";
			case "submit":
				return language.equals("nl") ? "Versturen" : "Submit";
			case "new_pokemon_entry":
				return language.equals("nl") ? "Nieuwe Pokemon Invoer" : "New Pokemon Entry";
			case "choose_name":
				return language.equals("nl") ? "Kies een naam" : "Choose a name";
			case "pokemon_name":
				return language.equals("nl") ? "Pokemon Naam" : "Pokemon Name";
			case "next":
				return language.equals("nl") ? "Volgende" : "Next";
			case "pokemon_type":
				return language.equals("nl") ? "Pokemon Type" : "Pokemon Type";
			case "choose_type":
				return language.equals("nl") ? "Kies type voor je Pokemon" : "Choose type for your Pokemon";
			case "pokemon_move":
				return language.equals("nl") ? "Pokemon Aanval" : "Pokemon Move";
			case "choose_move":
				return language.equals("nl") ? "Kies aanval voor je Pokemon" : "Choose move for your Pokemon";
			case "move":
				return language.equals("nl") ? "Aanval" : "Move";
			case "enter_pokedex_number":
				return language.equals("nl") ? "Voer Pokedex nummer in" : "Enter Pokedex Number";
			case "number":
				return language.equals("nl") ? "Nummer" : "Number";
			case "show_overview":
				return language.equals("nl") ? "Toon Overzicht" : "Show Overview";
			case "preferences":
				return language.equals("nl") ? "Voorkeuren" : "Preferences";
			case "current_theme":
				return language.equals("nl") ? "Huidig thema" : "Current theme";
			case "current_language":
				return language.equals("nl") ? "Huidige taal" : "Current language";
			case "theme":
				return language.equals("nl") ? "Thema" : "Theme";
			case "language":
				return language.equals("nl") ? "Taal" : "Language";
			case "light":
				return language.equals("nl") ? "Licht" : "Light";
			case "dark":
				return language.equals("nl") ? "Donker" : "Dark";
			case "save_preferences":
				return language.equals("nl") ? "Voorkeuren Opslaan" : "Save Preferences";
			case "home":
				return language.equals("nl") ? "Thuis" : "Home";
			case "page_not_found":
				return language.equals("nl") ? "Pagina Niet Gevonden" : "Page Not Found";
			case "pokemon_escaped":
				return language.equals("nl") ? "Oeps! Deze Pokémon is Ontsnapt!" : "Oops! This Pokémon Escaped!";
			case "page_not_found_message":
				return language.equals("nl") ? "De pagina die je zoekt bestaat niet. Misschien is deze Pokémon weggelopen!" : "The page you're looking for doesn't exist. Maybe this Pokémon ran away!";
			case "back_to_pokedex":
				return language.equals("nl") ? "Terug naar Pokédex" : "Back to Pokédx";
			case "video_not_supported":
				return language.equals("nl") ? "Je browser ondersteunt deze video niet." : "Your browser does not support this video.";
			case "server_error":
				return language.equals("nl") ? "Server Fout" : "Server Error";
			case "blastoise_overwhelmed":
				return language.equals("nl") ? "Oeps! Blastoise is Overweldigd!" : "Oops! Blastoise is Overwhelmed!";
			case "server_error_message":
				return language.equals("nl") ? "Er is iets misgegaan op de server. Blastoise probeert het probleem het onderwater op te lossen!" : "Something went wrong on the server. Blastoise is trying to fix the problem under water!";
			default:
				return key;
		}
	}

	default void addThemedPokemonStyling(HttpServletResponse response, HttpServletRequest request) throws IOException {
		PrintWriter out = response.getWriter();

		// voeg de css aan de class toe
		out.println("<link rel='stylesheet' type='text/css' href='/pokestyle.css'>");

		// haal het huidige gekozen thema op
		String currentTheme = "light";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("theme")) {
					currentTheme = cookie.getValue();
					break;
				}
			}
		}

		// voeg de thema styling toe
		String bgColor = currentTheme.equals("dark") ? "#222" : "#fff";
		String textColor = currentTheme.equals("dark") ? "#fff" : "#000";

		out.println("<style>");
		out.println("  body { background-color: " + bgColor + " !important; color: " + textColor + " !important; }");
		out.println("  .pokedex-container { background-color: " + bgColor + "; color: " + textColor + "; }");
		out.println("  .pokemon-card { background-color: " + bgColor + "; color: " + textColor + "; }");
		out.println("  .pokemon-container { background-color: " + bgColor + "; color: " + textColor + "; }");
		out.println("  .pokemon-entry-card { background-color: " + (currentTheme.equals("dark") ? "#333" : "#f8f9fa") + "; color: " + textColor + "; }");
		out.println("  .pokemon-entry-form { background-color: " + (currentTheme.equals("dark") ? "rgba(50, 50, 50, 0.9)" : "rgba(255, 255, 255, 0.9)") + "; }");
		out.println("  .pokemon-field .pokemon-value { color: " + textColor + " !important; }");
		out.println("  .pokemon-field .pokemon-label { color: " + textColor + " !important; }");
		out.println("  .pokemon-screen .pokemon-value { color: " + textColor + " !important; }");
		out.println("  .pokemon-screen .pokemon-label { color: " + textColor + " !important; }");
		out.println("  .pokedex-header { color: " + textColor + " !important; }");
		out.println("  .registration-section { background-color: " + bgColor + "; color: " + textColor + "; }");
		out.println("  .form-label { color: " + textColor + " !important; }");
		out.println("  .pokemon-field { background-color: " + (currentTheme.equals("dark") ? "rgba(50, 50, 50, 0.9)" : "rgba(255, 255, 255, 0.9)") + " !important; }");
		out.println("  .pokemon-selector { background-color: " + bgColor + "; }");
		out.println("  .pokemon-dropdown { background-color: " + (currentTheme.equals("dark") ? "#444" : "#fff") + "; color: " + textColor + "; border-color: " + (currentTheme.equals("dark") ? "#cc0000" : "#cc0000") + "; }");
		out.println("  .image-placeholder-text { color: " + textColor + " !important; }");
		out.println("  .image-placeholder-subtext { color: " + (currentTheme.equals("dark") ? "#aaa" : "#666") + " !important; }");
		out.println("  .error-title { color: " + (currentTheme.equals("dark") ? "#ff6666" : "#cc0000") + " !important; }");
		out.println("  .error-pokemon { background-color: " + (currentTheme.equals("dark") ? "rgba(255, 255, 255, 0.05)" : "rgba(255, 255, 255, 0.1)") + "; border-color: " + (currentTheme.equals("dark") ? "#ff6666" : "#cc0000") + "; }");
		out.println("  .error-pokemon h2 { color: " + (currentTheme.equals("dark") ? "#ff6666" : "#cc0000") + " !important; }");
		out.println("  .error-message { color: " + textColor + " !important; }");
		out.println("  .error-video-container { background-color: " + (currentTheme.equals("dark") ? "rgba(255, 255, 255, 0.05)" : "rgba(0, 0, 0, 0.1)") + "; border-color: " + (currentTheme.equals("dark") ? "#ff6666" : "#cc0000") + "; }");
		out.println("  .error-video { border-color: " + (currentTheme.equals("dark") ? "#555" : "#333") + "; }");
		out.println("  .home-icon-link:hover { opacity: 0.8; }");
		out.println("</style>");
	}

}
