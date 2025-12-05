package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

    private static final HashMap<String, String> USERS = bootstrapHardcodedUsers();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		addHtmlAndBodyTags(response);
		addThemedPokemonStyling(response, request);
		PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        // User is already logged in
        if (session != null && session.getAttribute("username") != null)
            response.sendRedirect("/pap");

		out.println("<div class='pokemon-container'>");
		out.println("  <a href='/register' class='home-icon-link'></a>");
		out.println("<h1>Welcome trainer!</h1>");

		if (Boolean.parseBoolean(request.getParameter("success")))
            out.println("<p style='color:green'>Registration successful!</p>");

		if (request.getParameter("error") != null) {
			String decodedError = decodeString(request.getParameter("error").toString());
			out.println("<p style='color:red'>" + decodedError + "</p>");
		}

		out.println("<h2>Login or register:</h2>");
		out.println("<form method='POST' action='/register'>");
		out.println("  <label>Username: <input type='text' name='username' required autofocus></label>");
		out.println("  <label>Password: <input type='password' name='password' required></label><br>");

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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String action = request.getParameter("action");

		if ("register".equals(action))
		{
			if(USERS.containsKey(username))
				response.sendRedirect("/register?error=" + encodeString("Username is taken"));
			else
			{
				USERS.put(username, password);
				response.sendRedirect("/register?success=true");
			}
		}
		else
		{
			if(!USERS.containsKey(username) || !USERS.get(username).equals(password))
				response.sendRedirect("/register?error=" + encodeString("Invalid credentials!"));

			HttpSession session = request.getSession(true);
			session.setAttribute("username", username);

			response.sendRedirect("/pap");
		}
	}

	private static HashMap<String, String> bootstrapHardcodedUsers()
	{
		HashMap<String, String> standardUsers = new HashMap<String, String>();
		standardUsers.put("test", "test");
		standardUsers.put("ash_ketchum", "verybest");
		return standardUsers;
	}
}

