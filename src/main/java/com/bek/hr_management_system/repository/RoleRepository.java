package com.bek.hr_management_system.repository;

import com.bek.hr_management_system.entity.Role;
import com.bek.hr_management_system.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Set<Role> findAllByName(RoleName name);
}
