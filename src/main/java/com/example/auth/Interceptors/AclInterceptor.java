package com.example.auth.Interceptors;

import com.example.auth.Configs.AuthBeans;
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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AclInterceptor implements HandlerInterceptor {

    @Autowired
    ACLrRepositoryImp acLrRepositoryImp;
    @Autowired
    AuthBeans authBeans;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> permitAll = Arrays.asList(authBeans.permitAll());
        if(permitAll.contains(request.getRequestURI())){
            return true;
        }
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (request.getRequestURI().equals("/error"))
                return true;
            if (acLrRepositoryImp.findById("all_url", request.getRequestURI()) != null) {
                for (GrantedAuthority authority : authentication.getAuthorities()) {
                    String entityName = authority.getAuthority();
                    if (acLrRepositoryImp.findById(entityName, request.getRequestURI()) != null) {
                        return true;
                    }
                }
            } else {
                for (GrantedAuthority authority : authentication.getAuthorities()) {
                    String entityName = authority.getAuthority() + "_r";

                    Map<String, String> acl = acLrRepositoryImp.findAll(entityName);

                    for (Map.Entry<String, String> aclEntity : acl.entrySet()) {
                        Pattern p = Pattern.compile(aclEntity.getKey());
                        Matcher matcher = p.matcher(request.getRequestURI());
                        if(matcher.matches()){
                            return true;
                        }

                    }

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
