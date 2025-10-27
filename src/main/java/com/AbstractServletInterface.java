package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public interface AbstractServletInterface {

	default void addHtmlAndBodyTags(HttpServletResponse response) throws IOException
	{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html");
        out.println("<html><body>");
	}
	
	default void closeHtmlAndBodyTags(HttpServletResponse response) throws IOException
	{
		response.getWriter().println("</body></html>");
	}
	
}
