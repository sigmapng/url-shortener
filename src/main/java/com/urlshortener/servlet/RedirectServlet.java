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

// Using "/*" pattern to catch all paths not matched by other servlets
@WebServlet("/*")
public class RedirectServlet extends HttpServlet {

    @Inject
    private UrlShortenerService urlShortenerService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String shortCode = null;

        // Handle the root path
        if (requestURI.equals(contextPath) || requestURI.equals(contextPath + "/")) {
            // If path is the root, forward to the main page
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        // Extract shortCode from the URI
        shortCode = requestURI.substring(contextPath.length() + 1);

        // Skip if this is another servlet or JSP
        if (shortCode.contains("/") || shortCode.equals("create") ||
            shortCode.equals("list") || shortCode.endsWith(".jsp")) {
            // Let the request continue to other servlets
            request.getRequestDispatcher(shortCode).forward(request, response);
            return;
        }

        UrlMapping urlMapping = urlShortenerService.getOriginalUrl(shortCode);

        if (urlMapping != null) {
            // Redirect to original URL
            response.sendRedirect(urlMapping.getOriginalUrl());
        } else {
            // If short code not found
            request.setAttribute("error", "Short URL not found");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
