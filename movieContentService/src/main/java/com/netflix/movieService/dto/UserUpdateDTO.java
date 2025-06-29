package com.netflix.movieService.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
	private String name;
	private String password;
}