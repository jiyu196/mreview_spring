package com.kiylab.mreview.service;

import com.kiylab.mreview.domain.dto.MovieDTO;
import com.kiylab.mreview.domain.dto.MovieImageDTO;
import com.kiylab.mreview.domain.entity.Movie;
import com.kiylab.mreview.domain.entity.MovieImage;

public interface MovieImageService {
  static MovieImage toEntity(MovieImageDTO dto) {
    return MovieImage.builder()
            .movie(Movie.builder().mno(dto.getMno()).build())
            .uuid(dto.getUuid())
            .path(dto.getPath())
            .imgName(dto.getOrigin())
            .build();

  }
}
