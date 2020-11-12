package com.example.auth.Interceptors;

import com.example.auth.Repositories.ACLrRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class AclInterceptor implements HandlerInterceptor {

    @Autowired
    ACLrRepositoryImp acLrRepositoryImp;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if(request.getRequestURI().equals("/error"))
                return true;
            List<String> redisKeys = new ArrayList<>();
            for(GrantedAuthority authority: authentication.getAuthorities()){
                redisKeys.add(request.getRequestURI() + "-" +authority.getAuthority());
            }

            List<String> acl = acLrRepositoryImp.findAllByKeys(redisKeys);

            if(acl.size() > 0){
                if(acl.get(0) != null) {
                    return true;
                }
            }
        }
        try {
            response.sendRedirect("/error");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
