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
            request.setAttribute("error", "URL не може бути порожнім");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
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
            request.setAttribute("error", "Помилка при створенні короткого URL: " + e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}