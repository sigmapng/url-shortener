package com.urlshortener.servlet;

import com.urlshortener.entity.UrlMapping;
import com.urlshortener.service.UrlShortenerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Use a more specific URL pattern to avoid conflicts with other servlets
@WebServlet(urlPatterns = {"/", "/go/*"})
public class RedirectServlet extends HttpServlet {

    @Inject
    private UrlShortenerService urlShortenerService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();

        // Handle root context requests directly
        if (requestURI.equals(contextPath) || requestURI.equals(contextPath + "/")) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        // Extract the short code from the URL
        String shortCode;

        // Check if using the /go/* pattern
        if (requestURI.startsWith(contextPath + "/go/")) {
            shortCode = requestURI.substring((contextPath + "/go/").length());
        } else {
            shortCode = requestURI.substring(contextPath.length() + 1);
        }

        // Skip processing for certain paths
        if (shortCode.contains("/") ||
            shortCode.startsWith("WEB-INF") ||
            shortCode.startsWith("META-INF") ||
            shortCode.equals("favicon.ico") ||
            shortCode.equals("index.jsp") ||
            shortCode.endsWith(".jsp")) {
            // Let the default handlers take over
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Try to redirect using the short URL
        UrlMapping urlMapping = urlShortenerService.getOriginalUrl(shortCode);

        if (urlMapping != null) {
            // If we find a match, redirect to the original URL
            response.sendRedirect(urlMapping.getOriginalUrl());
        } else {
            // If no match is found, set an error and show the main page
            request.setAttribute("error", "Short URL not found: " + shortCode);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
