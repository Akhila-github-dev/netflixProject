package com.netflix.movieService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.movieService.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	 List<Movie> findByGenre(String genre);
	 List<Movie> findByLanguage(String language);
	 List<Movie> findByTitleContainingIgnoreCase(String keyword);
}
