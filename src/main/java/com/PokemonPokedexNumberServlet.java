package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pokedex")
public class PokemonPokedexNumberServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		addHtmlAndBodyTags(response);
		PrintWriter out = response.getWriter();

		String name = request.getParameter("pokeName");
		String type = request.getParameter("pokeType");
//		String move = request.getParameter("pokeMove");

		out.println("<h1>Formulier Demo</h1>");

		out.println("<h2>Vul Move in</h2>");
		out.println("<label> " + name + type + " </label>");
		out.println("<form method='GET' action='/move'>");
		out.println("  <label>Naam: <input type='hidden' name='pokeName' value=" + name +" ></label><br><br>");
		out.println("  <label>Type: <input type='hidden' name='pokeType' value=" + type +" ></label><br><br>");
		out.println("  <label>Move: <input type='text' name='pokeMove'></label><br><br>");
		out.println("  <button type='submit'>Verzenden (GET)</button >");

		out.println("</form>");

		closeHtmlAndBodyTags(response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		addHtmlAndBodyTags(response);
		PrintWriter out = response.getWriter();

		String name = request.getParameter("pokeName");
		String type = request.getParameter("pokeType");
		String move = request.getParameter("pokeMove");
		String number = request.getParameter("dexint");

		out.println("<h1>Formulier Demo</h1>");

		out.println("<h1>Overview</h1>");

		out.println("  <label>Name " + name + " </label><br>");
		out.println("  <label>Type: " + type + " </label><br>");
		out.println("  <label>Move: " + move + " </label><br>");
		out.println("  <label>Number: " + number + " </label><br>");

		out.println("</form>");
		closeHtmlAndBodyTags(response);
	}
}

