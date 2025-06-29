package com.netflix.movieService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.movieService.dto.MovieRequestDTO;
import com.netflix.movieService.dto.MovieResponseDTO;
import com.netflix.movieService.dto.UserUpdateDTO;
import com.netflix.movieService.exception.MovieNotFoundException;
import com.netflix.movieService.model.Movie;
import com.netflix.movieService.repository.MovieRepository;
import com.netflix.movieService.util.Converter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {

	private final MovieRepository movieRepository;

	private final Converter converter;

	
	public MovieResponseDTO addMovie(MovieRequestDTO movieRequestDTO) {
		Movie movie = converter.mapToUser(movieRequestDTO);
	 System.out.println("Mapped movie before saving: " + movie);
		Movie saved = movieRepository.save(movie);
		return converter.mapToResponseDTO(saved);

	}

	public List<MovieResponseDTO> getAllMovies() {

		List<Movie> allmovies = movieRepository.findAll();
		List<MovieResponseDTO> responseList = new ArrayList<>();
		for (Movie movie : allmovies) {
			MovieResponseDTO mapToResponseDTO = converter.mapToResponseDTO(movie);
			responseList.add(mapToResponseDTO);
		}
		return responseList;

	}

	public MovieResponseDTO getMovieById(Long id) throws MovieNotFoundException {
		Optional<Movie> movie = movieRepository.findById(id);
		if (movie.isPresent()) {
			Movie movie1 = movie.get();
			return converter.mapToResponseDTO(movie1);
		} else {
			throw new MovieNotFoundException("movie not found with given Id");
		}

	}

	public List<MovieResponseDTO> searchByTitle(String title) {
		/*
		 * using loops
		 * 
		 * List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
		 * List<MovieResponseDTO> responseList = new ArrayList<>(); for (Movie movie :
		 * movies) { MovieResponseDTO mapToResponseDTO =
		 * converter.mapToResponseDTO(movie); responseList.add(mapToResponseDTO); }
		 * return responseList;
		 */

		// using streams
		return movieRepository.findByTitleContainingIgnoreCase(title).stream().map(converter::mapToResponseDTO)
				.collect(Collectors.toList());
	}

	public List<MovieResponseDTO> filterByGenre(String genre) {
	
		return movieRepository.findByGenre(genre).stream().map(converter::mapToResponseDTO).collect(Collectors.toList());
	}

}
