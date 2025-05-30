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
import java.util.List;

@WebServlet("/list")
public class UrlListServlet extends HttpServlet {

    @Inject
    private UrlShortenerService urlShortenerService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UrlMapping> urls = urlShortenerService.getAllUrls();
        request.setAttribute("urls", urls);
        
        // Calculate stats for displaying
        int totalUrls = urls.size();
        request.setAttribute("totalUrls", totalUrls);

        request.getRequestDispatcher("/WEB-INF/urlList.jsp").forward(request, response);
    }
}
