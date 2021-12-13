package com.das.project.dto;

import com.das.project.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetInfo {

    private String name;
    private Category category;
    private String description;
    private boolean isAvailable;

    private String photo;
}
