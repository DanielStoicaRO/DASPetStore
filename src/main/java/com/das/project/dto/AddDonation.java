package com.das.project.dto;

import com.das.project.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddDonation {

    private Long userId;
    private Product product;
    private String details;

}
