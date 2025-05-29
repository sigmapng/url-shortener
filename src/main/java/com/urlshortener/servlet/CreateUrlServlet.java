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

@WebServlet("/create")
public class CreateUrlServlet extends HttpServlet {

    @Inject
    private UrlShortenerService urlShortenerService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String originalUrl = request.getParameter("originalUrl");

        if (originalUrl == null || originalUrl.trim().isEmpty()) {
            request.setAttribute("error", "URL cannot be empty");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        // Add protocol if missing - using HTTPS by default for better security
        if (!originalUrl.startsWith("http://") && !originalUrl.startsWith("https://")) {
            originalUrl = "https://" + originalUrl;
        }

        try {
            UrlMapping urlMapping = urlShortenerService.createShortUrl(originalUrl);

            String shortUrl = request.getScheme() + "://" +
                    request.getServerName() + ":" +
                    request.getServerPort() +
                    request.getContextPath() + "/" +
                    urlMapping.getShortCode();

            request.setAttribute("shortUrl", shortUrl);
            request.setAttribute("urlMapping", urlMapping);
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Error creating short URL: " + e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

