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
        int totalClicks = 0;
        double avgClicksPerUrl = 0;
        UrlMapping mostClicked = null;
        
        for (UrlMapping url : urls) {
            int clicks = url.getVisitsCount() != null ? url.getVisitsCount() : 0;
            totalClicks += clicks;
            
            if (mostClicked == null || 
                (url.getVisitsCount() != null && 
                 mostClicked.getVisitsCount() != null && 
                 url.getVisitsCount() > mostClicked.getVisitsCount())) {
                mostClicked = url;
            }
        }
        
        if (totalUrls > 0) {
            avgClicksPerUrl = (double) totalClicks / totalUrls;
        }
        
        request.setAttribute("totalUrls", totalUrls);
        request.setAttribute("totalClicks", totalClicks);
        request.setAttribute("avgClicksPerUrl", avgClicksPerUrl);
        request.setAttribute("mostClicked", mostClicked);

        request.getRequestDispatcher("/WEB-INF/urlList.jsp").forward(request, response);
    }
}
