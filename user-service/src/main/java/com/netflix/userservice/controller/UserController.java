package com.netflix.userservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.userservice.UserServiceApplication;
import com.netflix.userservice.dto.UserRequestDTO;
import com.netflix.userservice.dto.UserResponseDTO;
import com.netflix.userservice.dto.UserUpdateDTO;
import com.netflix.userservice.exception.UserNotFoundException;
import com.netflix.userservice.model.User;
import com.netflix.userservice.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO) {
		UserResponseDTO response = userService.registerUser(userRequestDTO);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) throws UserNotFoundException {
		Optional<UserResponseDTO> user = userService.getUserById(id);

		return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	@GetMapping("/email/{email}")
	public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) throws UserNotFoundException {
		Optional<UserResponseDTO> user = userService.getUserByEmail(email);

		return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	@PutMapping("{id}")
	public ResponseEntity<UserResponseDTO> UpdateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO)
			throws UserNotFoundException {
		Optional<UserResponseDTO> updateUser = userService.updateUser(id, userUpdateDTO);

		return updateUser.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	@DeleteMapping("{id}")
	public String deleteUser(@PathVariable Long id) {

		return userService.deleteUser(id);

	}
}
