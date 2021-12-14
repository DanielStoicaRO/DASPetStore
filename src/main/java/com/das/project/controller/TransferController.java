package com.das.project.controller;

import com.das.project.dto.TransferDto;
import com.das.project.dto.TransferInfo;
import com.das.project.model.User;
import com.das.project.service.TransferService;
import com.das.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class TransferController {

    private final TransferService transferService;
    private final UserService userService;

    @Autowired
    public TransferController(TransferService transferService, UserService userService) {
        this.transferService = transferService;
        this.userService = userService;
    }

    @GetMapping("/transfers")
    public String getTransfersPage(Model model) {
        model.addAttribute("transfersDto", transferService.findAll());
        return "transfer/transfers";
    }

    // FIXME: implement later
    @GetMapping("/transfers/add")
    public String getTransferForm(Model model) {
        model.addAttribute("transferDto", new TransferDto());
        return "transfer/transfer-add";
    }

    @PostMapping("/transfers/add")
    public String addDonationForm(@ModelAttribute TransferDto transferDto) {
        transferService.save(transferDto);
        return "redirect:/home";
    }

    @GetMapping("/my-transfers")
    public String getMyTransfersPage(Model model) {
        User user = userService.findLoggedUser();
        Set<TransferInfo> transfers = transferService.findTransfersByUser(user);
        model.addAttribute("transfersInfo", transfers);
        return "transfer/my-transfers";
    }
}
