package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/pokename")
public class PokemonNaamServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		addHtmlAndBodyTags(response);
		addPokemonStyling(response);

		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();

		out.println("<div class='pokemon-container'>");
		out.println("<h1>New pokemon entree</h1>");
		out.println("<h2>Name</h2>");
		out.println("<form method='POST' action='/pokename'>");
		out.println("  <label>Naam: <input type='text' name='pokeName'></label><br><br>");
		out.println("  <button type='submit'>Verzenden</button>");
		out.println("</form>");
		out.println("</div>");
		closeHtmlAndBodyTags(response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		HttpSession session = request.getSession();

		String pokeName = request.getParameter("pokeName");
		session.setAttribute("pokeName", pokeName);

		response.sendRedirect("/type");
	}
}

