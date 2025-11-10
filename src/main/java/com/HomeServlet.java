package com;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class HomeServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		addHtmlAndBodyTags(response);
		addThemedPokemonStyling(response, request);
		PrintWriter out = response.getWriter();

		out.println("<div class='pokemon-container'>");
		out.println("<h1>Pokemon Login</h1>");

		out.println("<h2>Login or Register:</h2>");
		out.println("<form method='POST' action='/home'>");
		out.println("  <label>Username: <input type='text' name='name'></label><br>");
		out.println("  <label>Password: <input type='password' name='password'></label><br><br>");

		out.println("  <button type='submit' name='action' value='login'>Login</button>");
		out.println("  <button type='submit' name='action' value='register'>Register</button>");

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

		if ("register".equals(action))
			response.sendRedirect("/register");
		else {
			String name = request.getParameter("name");
			String password = request.getParameter("password");

			session.setAttribute("name", name);
			session.setAttribute("password", password);

			response.sendRedirect("/welcome");
		}
	}
}
