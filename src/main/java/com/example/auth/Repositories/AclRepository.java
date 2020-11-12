package com.example.auth.Repositories;

import com.example.auth.Entities.AclEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface AclRepository extends JpaRepository<AclEntity, Integer> {
}
