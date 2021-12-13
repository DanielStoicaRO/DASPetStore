package com.das.project.repository;

import com.das.project.model.Role;
import com.das.project.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByType(RoleType type);

    @Query("FROM Role role JOIN role.users user WHERE user.id =:userId")
    Set<Role> getRoles(Long userId);

}
