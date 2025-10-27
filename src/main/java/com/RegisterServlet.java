package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet implements AbstractServletInterface
{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		addHtmlAndBodyTags(response);
		PrintWriter out = response.getWriter();

		out.println("<h1>Register</h1>");

		out.println("<h2>Login:</h2>");
		out.println("<form method='POST' action='/home'>");
		out.println("  <label>Username: <input type='text' name='userName'></label><br>");
		out.println("  <label>Password: <input type='password' name='password'></label><br>");
		out.println("  <button type='submit'>Verzenden</button>");

		out.println("</form>");
		closeHtmlAndBodyTags(response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		response.sendRedirect("/home");
	}
}

