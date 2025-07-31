package com.kiylab.mreview.service;

import com.kiylab.mreview.domain.dto.MovieDTO;
import com.kiylab.mreview.domain.dto.PageRequestDTO;
import com.kiylab.mreview.domain.dto.PageResponseDTO;
import com.kiylab.mreview.domain.entity.Movie;
import com.kiylab.mreview.domain.entity.MovieImage;
import com.kiylab.mreview.repository.MovieImageRepository;
import com.kiylab.mreview.repository.MovieRepository;
import jakarta.persistence.OrderBy;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Data
public non-sealed class MovieServiceImpl implements MovieService {
  private final MovieRepository movieRepository;
  private final MovieImageRepository movieImageRepository;

  @Override
  @Transactional
  public Long register(MovieDTO dto){
    Map<String, Object> map = toEntity(dto);
    Movie movie = (Movie) map.get("movie");
    movieRepository.save(movie);
    List<MovieImage> list = ((List<MovieImage>)map.get("images"));
//    list.forEach(image -> image.setMovie(movie));
    list.forEach(movieImageRepository::save);
    return movie.getMno();
  }

  @Override
  public PageResponseDTO<MovieDTO,Object[]> getList(PageRequestDTO dto) {
    return new PageResponseDTO<>(movieRepository.getListPage(dto.getPageable(Sort.by(Sort.Direction.DESC, "mno"))),
            arr -> toDTO((Movie) arr[0], (List<MovieImage>) (Arrays.asList((MovieImage) arr[1])), (Double) arr[2], (Long)arr[3])) ;

  }
}
