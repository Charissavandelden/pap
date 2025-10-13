package com;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/pokedex")
public class PokemonPokedexNumberServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("pokeName");
		String type = request.getParameter("pokeType");
//		String move = request.getParameter("pokeMove");

		out.println("<!DOCTYPE html>");
		out.println("<html><body>");
		out.println("<h1>Formulier Demo</h1>");

		out.println("<h2>Vul Move in</h2>");
		out.println("<label> " + name + type + " </label>");
		out.println("<form method='GET' action='/move'>");
		out.println("  <label>Naam: <input type='hidden' name='pokeName' value=" + name +" ></label><br><br>");
		out.println("  <label>Type: <input type='hidden' name='pokeType' value=" + type +" ></label><br><br>");
		out.println("  <label>Move: <input type='text' name='pokeMove'></label><br><br>");
		out.println("  <button type='submit'>Verzenden (GET)</button>");

		out.println("</form>");

		out.println("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");

		out.println("<!DOCTYPE html>");
		out.println("<html><body>");
		out.println("<h1>Formulier Demo</h1>");

		out.println("<h2>Placeholder h2</h2>");
		out.println("<form method='POST' action='/pokename'>");
		out.println("  <label>Naam: <input type='text' name='pokemonNaam'></label><br><br>");
		out.println("  <button type='submit'>Verzenden (POST)</button>");

		out.println("</form>");
		out.println("</body></html>");
	}
}

