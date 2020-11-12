package com.example.auth.Controllers;

import com.example.auth.Entities.ACLrEntity;
import com.example.auth.Entities.AclEntity;
import com.example.auth.Repositories.ACLrRepository;
import com.example.auth.Repositories.ACLrRepositoryImp;
import com.example.auth.Repositories.AclRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "/acl")
@Controller
public class AclController {

    @Autowired
    AclRepository aclRepository;
    @Autowired
    ACLrRepositoryImp acLrRepositoryImp;

    @RequestMapping(value = "/sync/redis")
    @ResponseBody
    public Object syncRedis() {
        List<AclEntity> aclEntities = aclRepository.findAll();
        try {
            for (AclEntity aclEntity : aclEntities) {
                acLrRepositoryImp.save(aclEntity.getUrl() + "-" + aclEntity.getRole(), aclEntity.getId() + "");
            }
            return 200;
        } catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
    }

    @RequestMapping(value = "/list/redis")
    @ResponseBody
    public Object aclRList() {
        acLrRepositoryImp.delete("1");
        acLrRepositoryImp.delete("2");
        return acLrRepositoryImp.findAll();
    }
}
