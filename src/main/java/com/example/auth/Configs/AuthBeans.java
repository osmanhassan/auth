package com.example.auth.Configs;

import com.example.auth.Repositories.ACLrRepositoryImp;
import com.example.auth.Services.AclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthBeans implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ACLrRepositoryImp acLrRepositoryImp;
    @Autowired
    AclService aclService;

    public String[] permitAll(){
        List<String> permitAll = new ArrayList<>();
        permitAll.add("/acl/save");

        String[] permitAllArray = new String[permitAll.size()];
        return permitAll.toArray(permitAllArray);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        int[] count = {1};
        ApplicationContext applicationContext = event.getApplicationContext();
        applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods()
                .forEach((key, value)->{
                    String url = key.toString().replaceAll("\\{ \\[", "");
                    url = url.replaceAll("]}", "");
                    url = url.replaceAll("]", "");
                    url = url.replaceAll(",", "");
                    url = url.split(" ")[0];
                    System.out.println(url);
                    acLrRepositoryImp.save("all_url", url, "" + count[0]++);

                });
        System.out.println("**********************");
        System.out.println("Redis has been synced with all urls.");
        System.out.println("**********************");
    }

    @Bean
    public void syncRedis(){
        aclService.syncRedis();
        System.out.println("**********************");
        System.out.println("Redis has been synced with acl.");
        System.out.println("**********************");
    }
}
