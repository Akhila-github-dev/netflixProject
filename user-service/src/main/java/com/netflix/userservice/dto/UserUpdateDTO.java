package com.netflix.userservice.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
	private String name;
	private String password;
}