package com.example.auth.Repositories;

import com.example.auth.Entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RolesEntity, String> {


}
