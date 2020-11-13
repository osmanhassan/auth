package com.example.auth.Controllers;

import com.example.auth.Entities.ACLrEntity;
import com.example.auth.Entities.AclEntity;
import com.example.auth.Entities.RolesEntity;
import com.example.auth.Repositories.ACLrRepository;
import com.example.auth.Repositories.ACLrRepositoryImp;
import com.example.auth.Repositories.AclRepository;
import com.example.auth.Repositories.RoleRepository;
import com.example.auth.Services.AclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping(value = "/acl")
@Controller
public class AclController {

    @Autowired
    AclRepository aclRepository;
    @Autowired
    ACLrRepositoryImp acLrRepositoryImp;
    @Autowired
    AclService aclService;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(HttpServletRequest request){
        return aclService.saveAcl(request);
    }

    @RequestMapping(value = "/sync/redis")
    @ResponseBody
    public Object syncRedis() {
        return aclService.syncRedis();
    }

    @RequestMapping(value = "/list/redis/{entityName}")
    @ResponseBody
    public Object aclRList(@PathVariable("entityName") String entityName) {
        return acLrRepositoryImp.findAll(entityName);
    }
}
