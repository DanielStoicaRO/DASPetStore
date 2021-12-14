package com.das.project.dto;

import com.das.project.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationInfo {

    // logged user id
    private UserInfoDto user;
    private Product product;
    private String details;
}
