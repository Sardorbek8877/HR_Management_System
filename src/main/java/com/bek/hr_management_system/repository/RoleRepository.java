package com.bek.hr_management_system.repository;

import com.bek.hr_management_system.entity.Role;
import com.bek.hr_management_system.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}
