package com;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/500")
public class Error500Servlet extends HttpServlet implements AbstractServletInterface
{

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        addHtmlAndBodyTags(response);
        addThemedPokemonStyling(response, request);
        PrintWriter out = response.getWriter();

        String language = getCurrentLanguage(request);

        out.println("<div class='pokemon-container'>");
        out.println("  <a href='/register' class='home-icon-link'></a>");
        out.println("  <h1 class='error-title'>500 - " + getText("server_error", language) + "</h1>");
        out.println("  <div class='error-pokemon'>");
        out.println("    <div class='error-icon'>⚠️</div>");
        out.println("    <h2>" + getText("blastoise_overwhelmed", language) + "</h2>");
        out.println("    <p class='error-message'>" + getText("server_error_message", language) + "</p>");
        out.println("    <div class='error-video-container'>");
        out.println("      <video id='errorVideo' class='error-video' autoplay muted loop playsinline>");
        out.println("        <source src='/Blastoise.mp4' type='video/mp4'>");
        out.println("        " + getText("video_not_supported", language));
        out.println("      </video>");
        out.println("    </div>");
        out.println("    <script>");
        out.println("      document.addEventListener('DOMContentLoaded', function() {");
        out.println("        const video = document.getElementById('errorVideo');");
        out.println("        if (video) {");
        out.println("          video.play().catch(function(error) {");
        out.println("            console.log('Video autoplay failed:', error);");
        out.println("          });");
        out.println("        }");
        out.println("      });");
        out.println("    </script>");
        out.println("  </div>");
        out.println("  <div class='error-actions'>");
        out.println("    <a href='/pap' class='error-button'>" + getText("back_to_pokedex", language) + "</a>");
        out.println("  </div>");
        out.println("</div>");

        closeHtmlAndBodyTags(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
