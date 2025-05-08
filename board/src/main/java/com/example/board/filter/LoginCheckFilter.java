package com.example.board.filter;

import java.io.IOException;

import com.example.board.entity.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckFilter  implements Filter{

    @Override
    public void doFilter(
        ServletRequest request, 
        ServletResponse response,
        FilterChain chain) 
        throws IOException, ServletException{
            log.debug("filter begin");
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            // String ip = request.getRemoteAddr();
            // log.debug("ip : " + ip);
            // chain.doFilter(req, response);
            // log.debug("filter end");
            System.out.println(req.toString());
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            String url = req.getRequestURI();
            System.out.println(url);
            if(user == null && url.equals("/board/write")){
                res.sendRedirect("/signin");
                return ;
            }
            if(user != null && url.equals("/signin")){
                res.sendRedirect("/board/list");
                return ;
            }
            System.out.println("user" + user);
            chain.doFilter(request, response);
            

    }

   
    
}
