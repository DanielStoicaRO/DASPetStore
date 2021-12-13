package com.das.project.service;


import com.das.project.dto.TransferDto;
import com.das.project.dto.TransferInfo;
import com.das.project.mapper.TransferMapper;
import com.das.project.model.Transfer;
import com.das.project.model.User;
import com.das.project.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;

    @Autowired
    public TransferService(TransferRepository transferRepository,
                           TransferMapper transferMapper) {
        this.transferRepository = transferRepository;
        this.transferMapper = transferMapper;
    }

    public Transfer save(TransferDto transferDto) {
        return transferRepository.save(transferMapper.map(transferDto));
    }

    public List<TransferDto> findAll() {
        return transferRepository.findAll().stream()
                .map(transfer -> transferMapper.map(transfer))
                .collect(Collectors.toList());
    }

    public Set<TransferInfo> findTransferByUser(User user){
        return transferRepository.findAll().stream()
                .filter(transfer -> transfer.getUser().equals(user))
                .map(transfer -> transferMapper.mapFromTransferToTransferInfo(transfer))
                .collect(Collectors.toSet());
    }

}
