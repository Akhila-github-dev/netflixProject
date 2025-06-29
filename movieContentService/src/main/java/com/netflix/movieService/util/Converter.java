package com.netflix.movieService.util;

import org.springframework.stereotype.Component;

import com.netflix.movieService.dto.MovieRequestDTO;
import com.netflix.movieService.dto.MovieResponseDTO;
import com.netflix.movieService.model.Movie;

@Component
public class Converter {

	public MovieResponseDTO mapToResponseDTO(Movie movie) {
		return MovieResponseDTO.builder().id(movie.getId()).title(movie.getTitle()).genre(movie.getGenre())
				.language(movie.getLanguage()).description(movie.getDescription()).videoUrl(movie.getVideoUrl())
				.rating(movie.getRating()).thumbnailUrl(movie.getThumbnailUrl()).build();
	}

	public Movie mapToUser(MovieRequestDTO movieRequestDTO) {

		return Movie.builder().title(movieRequestDTO.getTitle()).genre(movieRequestDTO.getGenre())
				.language(movieRequestDTO.getLanguage()).description(movieRequestDTO.getDescription())
				.videoUrl(movieRequestDTO.getVideoUrl()).rating(movieRequestDTO.getRating())
				.thumbnailUrl(movieRequestDTO.getThumbnailUrl()).build();
	}
}
