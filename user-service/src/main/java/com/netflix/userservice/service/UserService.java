package com.netflix.userservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.userservice.dto.UserRequestDTO;
import com.netflix.userservice.dto.UserResponseDTO;
import com.netflix.userservice.dto.UserUpdateDTO;
import com.netflix.userservice.exception.UserNotFoundException;
import com.netflix.userservice.model.User;
import com.netflix.userservice.repository.UserRepository;
import com.netflix.userservice.util.Converter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final Converter converter;

	@Transactional
	public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
		User user = converter.mapToUser(userRequestDTO);
		User saved = userRepository.save(user);
		return converter.mapToResponseDTO(saved);

	}

	public Optional<UserResponseDTO> getUserById(Long id) throws UserNotFoundException {
		Optional<User> UserId = userRepository.findById(id);

		if (UserId.isPresent()) {
			User user = UserId.get();
			return Optional.of(converter.mapToResponseDTO(user));
		} else {
			throw new UserNotFoundException("User not found with given Id");
		}

	}

	public Optional<UserResponseDTO> getUserByEmail(String email) throws UserNotFoundException {
		Optional<User> User = userRepository.findByEmail(email);
		if (User.isPresent()) {
			User user1 = User.get();
			return Optional.of(converter.mapToResponseDTO(user1));
		} else {
			throw new UserNotFoundException("User not found with given EmailId");
		}

	}

	public Optional<UserResponseDTO> updateUser(Long id, UserUpdateDTO userUpdateDTO) throws UserNotFoundException {

		User user = userRepository.findById(id).orElse(null);
		if (user != null) {
			if (userUpdateDTO.getName() != null) {
				user.setName(userUpdateDTO.getName());
			}
			if (userUpdateDTO.getPassword() != null) {
				user.setPassword(userUpdateDTO.getPassword());
			}
			User updateUser = userRepository.save(user);
			return Optional.of(converter.mapToResponseDTO(user));
		}
		throw new UserNotFoundException("User not found with given Id");
	}

	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "user deleted succesfully";

	}
}
