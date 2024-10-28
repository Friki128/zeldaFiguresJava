package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileFilter;
import java.io.IOException;

@WebFilter(filterName="/Filter",urlPatterns="/*" )
public class filterController implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
       if (uri.endsWith(".css")) {
             filterChain.doFilter(servletRequest, servletResponse);
         return;
        }
        if((!(uri.equals("/login") || uri.equals("/register"))) && req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("/login");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}


