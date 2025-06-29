package com.netflix.movieService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRequestDTO {

	private String title;

	private String genre;

	private String language;

	private String description;

	private String videoUrl;
	private double rating;
	private String thumbnailUrl;

}
