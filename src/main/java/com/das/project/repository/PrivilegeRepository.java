package com.das.project.repository;

import com.das.project.model.Privilege;
import com.das.project.model.PrivilegeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Optional<Privilege> findByType(PrivilegeType type);
}
