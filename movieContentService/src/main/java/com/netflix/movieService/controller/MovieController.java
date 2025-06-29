package com.netflix.movieService.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.movieService.MovieServiceApplication;
import com.netflix.movieService.dto.MovieRequestDTO;
import com.netflix.movieService.dto.MovieResponseDTO;
import com.netflix.movieService.dto.UserUpdateDTO;
import com.netflix.movieService.exception.MovieNotFoundException;
import com.netflix.movieService.model.Movie;
import com.netflix.movieService.service.MovieService;

@RestController
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PostMapping("/add")
	public ResponseEntity<MovieResponseDTO> addMovie(@RequestBody MovieRequestDTO movieRequestDTO) {

		return ResponseEntity.ok(movieService.addMovie(movieRequestDTO));

	}

	@GetMapping
	public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {

		return ResponseEntity.ok(movieService.getAllMovies());

	}

	@GetMapping("/{id}")
	public ResponseEntity<MovieResponseDTO> getMovie(@PathVariable Long id) throws MovieNotFoundException {

		return ResponseEntity.ok(movieService.getMovieById(id));

	}

	@GetMapping("/search")
	public ResponseEntity<List<MovieResponseDTO>> search(@RequestParam String title) {
		return ResponseEntity.ok(movieService.searchByTitle(title));
	}

	
	@GetMapping("/genre/{genre}")
	public ResponseEntity<List<MovieResponseDTO>> byGnere(@PathVariable String genre) {
		return ResponseEntity.ok(movieService.filterByGenre(genre));
	}
}
