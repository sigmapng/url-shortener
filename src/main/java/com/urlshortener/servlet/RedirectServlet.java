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

@WebServlet("/*")
public class RedirectServlet extends HttpServlet {

    @Inject
    private UrlShortenerService urlShortenerService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            // Якщо шлях порожній, перенаправляємо на головну сторінку
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        // Видаляємо слеш на початку
        String shortCode = pathInfo.substring(1);

        UrlMapping urlMapping = urlShortenerService.getOriginalUrl(shortCode);

        if (urlMapping != null) {
            // Перенаправляємо на оригінальний URL
            response.sendRedirect(urlMapping.getOriginalUrl());
        } else {
            // Якщо короткий код не знайдено
            request.setAttribute("error", "Короткий URL не знайдено");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}