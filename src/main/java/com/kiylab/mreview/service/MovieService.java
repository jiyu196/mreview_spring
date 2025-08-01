package com.kiylab.mreview.service;

import com.kiylab.mreview.domain.dto.MovieDTO;
import com.kiylab.mreview.domain.dto.MovieImageDTO;
import com.kiylab.mreview.domain.dto.PageRequestDTO;
import com.kiylab.mreview.domain.dto.PageResponseDTO;
import com.kiylab.mreview.domain.entity.Movie;
import com.kiylab.mreview.domain.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public sealed interface MovieService permits MovieServiceImpl{
  Long register(MovieDTO dto);
  PageResponseDTO<MovieDTO, Object[]> getList(PageRequestDTO dto);
  MovieDTO get(Long mno);

  default Map<String, Object> toEntity(MovieDTO dto) {
    Map<String, Object> map = new HashMap<>();
    Movie movie = Movie.builder().title(dto.getTitle()).mno(dto.getMno()).build();
    map.put("movie",Movie.builder().title(dto.getTitle()).mno(dto.getMno()).build());
    map.put("images", dto.getList().stream().map( m ->
            MovieImage.builder()
                    .movie(movie)
                    .uuid(m.getUuid())
                    .path(m.getPath())
                    .imgName(m.getOrigin())
                    .build()
    ).toList()); // 이렇게 타입을 바꾸는거.
    return map;
  }

  default MovieDTO toDTO(Movie movie, List<MovieImage> images, double avg, long reviewCnt) {
    return MovieDTO.builder()
            .mno(movie.getMno())
            .title(movie.getTitle())
            .regDate(movie.getRegDate())
            .modDate(movie.getModDate())
            .list(images.stream().map(i -> i == null? null : MovieImageDTO.builder()
                            .origin(i.getImgName())
                            .uuid(i.getUuid())
                            .path(i.getPath())
                            .build())
                    .toList())
            .avg(avg)
            .reviewCnt(reviewCnt)
            .build();

  }
}
