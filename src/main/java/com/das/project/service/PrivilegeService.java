package com.das.project.service;

import com.das.project.controller.exception.ResourceAlreadyExistsException;
import com.das.project.model.Privilege;
import com.das.project.model.PrivilegeType;
import com.das.project.repository.PrivilegeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrivilegeService {

    private static final Logger log = LoggerFactory.getLogger(PrivilegeService.class);

    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Transactional
    public Privilege save(PrivilegeType type) {
        log.info("save privilege {}", type);

        return (Privilege) privilegeRepository.findByType(type)
                .map((existingPrivilege) -> {
                    throw new ResourceAlreadyExistsException("privilege already exist");
                })
                .orElseGet(() -> privilegeRepository.save(new Privilege(type)));
    }
}
