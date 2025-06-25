package com.netflix.userservice.util;

import org.springframework.stereotype.Component;

import com.netflix.userservice.dto.UserRequestDTO;
import com.netflix.userservice.dto.UserResponseDTO;
import com.netflix.userservice.model.User;

@Component
public class Converter {

	public UserResponseDTO mapToResponseDTO(User user){
		 return UserResponseDTO.builder()
				 .id(user.getId())
				  .name(user.getName())
				  .email(user.getEmail())
				  .role(user.getRole())
				  .build();
	}

	public User mapToUser(UserRequestDTO userRequestDTO) {

		return User.builder()
				.name(userRequestDTO.getName())
				.email(userRequestDTO.getEmail())
				.password(userRequestDTO.getPassword())
				.role("ROLE_USER")
				.build();
	}
}
