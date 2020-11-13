package com.example.auth.Services;

import com.example.auth.Entities.AclEntity;
import com.example.auth.Entities.RolesEntity;
import com.example.auth.Repositories.ACLrRepositoryImp;
import com.example.auth.Repositories.AclRepository;
import com.example.auth.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class AclService {

    @Autowired
    AclRepository aclRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ACLrRepositoryImp acLrRepositoryImp;

    public Object saveAcl(HttpServletRequest request) {
        String url = urlGenerator(request.getParameter("url"));
        String role = request.getParameter("role");
        RolesEntity rolesEntity = roleRepository.findById(role).get();
        Integer isPattern = 0;
        if (url != null) {
            if (!url.equals(request.getParameter("url"))) {
                isPattern = 1;
            }
        }

        List<AclEntity> aclEntityList = new ArrayList<>();
        AclEntity acl = new AclEntity();
        acl.setRole(role);
        acl.setUrl(url.trim());
        acl.setIsPattern(isPattern);
        acl.setRolesByRole(rolesEntity);
        acl = aclRepository.saveAndFlush(acl);
        aclEntityList.add(acl);

        char[] urlArray = acl.getUrl().toCharArray();
        if (urlArray[urlArray.length - 1] == '/') {

            urlArray[urlArray.length - 1] = ' ';
            url = new String(urlArray).trim();

            AclEntity acl1 = new AclEntity();
            acl1.setUrl(url);
            acl1.setIsPattern(isPattern);
            acl1.setRole(role);
            acl1.setRolesByRole(rolesEntity);
            acl1 = aclRepository.save(acl1);

            aclEntityList.add(acl1);
        }

        return aclEntityList;

    }

    public String urlGenerator(String url) {
        if (url != null) {
            url = url.replaceAll("\\?", "[^/]?");
            url = url.replaceAll("\\{[^/]*}", "[^/]*");
        }

        return url;
    }

    public Object syncRedis() {
        try {
            List<RolesEntity> rolesEntities = roleRepository.findAll();
            for (RolesEntity rolesEntity : rolesEntities) {
                for (AclEntity aclEntity : rolesEntity.getAclsByRole()) {
                    String entityName = rolesEntity.getRole();
                    if (aclEntity.getIsPattern() == 1) {
                        entityName += "_r";
                    }
                    acLrRepositoryImp.save(entityName, aclEntity.getUrl(), aclEntity.getId() + "");
                }
            }

            return 200;
        }
        catch (Exception e){
            e.printStackTrace();
            return 500;
        }
    }

}
