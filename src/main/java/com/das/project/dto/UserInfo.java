package com.das.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfo {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}