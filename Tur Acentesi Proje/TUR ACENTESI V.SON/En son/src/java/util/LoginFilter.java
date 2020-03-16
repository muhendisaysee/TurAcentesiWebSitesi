/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Iletisim;
import entity.Personel;
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

@WebFilter("/*")
public class LoginFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();
        Personel u = (Personel) req.getSession().getAttribute("valid_personel");
        Iletisim u1 = (Iletisim) req.getSession().getAttribute("valid_iletisim");        
        
        if (u == null && u1 == null){
            if (url.contains("index")) {
                res.sendRedirect(req.getContextPath() + "/Frontend/login.xhtml");
            }else if (url.contains("profil")) {
                res.sendRedirect(req.getContextPath() + "/Frontend/login.xhtml");
            }else if (url.contains("module")) {
                res.sendRedirect(req.getContextPath() + "/Frontend/login.xhtml");
            }  else {
                chain.doFilter(request, response);
            }            
        }else {
            if (url.contains("logOut")) {
                req.getSession().invalidate();
                chain.doFilter(request, response);
            }else if(url.contains("login")) {
                req.getSession().invalidate();
                chain.doFilter(request, response);
            }
            else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }

}
