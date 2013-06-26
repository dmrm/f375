/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ifmo.pe.oatmeal.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author D
 */
@WebFilter(filterName = "LoggedRequestFilters", urlPatterns = {"/*"})
public class LoggedRequestsFilter implements Filter {
    
    private FilterConfig filterConfig = null;
    
    public LoggedRequestsFilter() {
    }        
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String url = req.getRequestURI();
        
        if((req.getRemoteUser() != null) && 
                (url.contains("login.xhtml") || url.contains("video_feature.xhtml") || 
                url.contains("loginerr.xhtml") || url.contains("anonymous_request.xhtml"))){
            res.sendRedirect(req.getContextPath() + "/p/person.xhtml");
        } else {
            chain.doFilter(request, response);
        }        
    }
    @Override
    public void destroy() {    
        this.filterConfig = null;
    }
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}
