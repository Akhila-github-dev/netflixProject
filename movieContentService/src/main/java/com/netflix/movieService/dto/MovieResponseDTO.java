package com.netflix.movieService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponseDTO {

	private Long id;
	private String title;

	private String genre;

	private String language;

	private String description;

	private String videoUrl;
	private double rating;
	private String thumbnailUrl;
}
