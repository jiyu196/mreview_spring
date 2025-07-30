package com.kiylab.mreview.repository;

import com.kiylab.mreview.domain.entity.Movie;
import com.kiylab.mreview.domain.entity.MovieImage;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MovieRepositoryTest {
  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private MovieImageRepository movieImageRepository;

  @Commit
  @Transactional
  @Test
  public void insertMovies() {
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Movie movie = Movie.builder().title("Movie..." + i).build();
      movieRepository.save(movie);
      int count = (int)(Math.random()*5) + 1;

      for(int j = 0; j < count; j++) {
        MovieImage movieImage = MovieImage.builder()
                .uuid(UUID.randomUUID().toString())
                .movie(movie)
                .imgName("test"+j+".jpg").build();
        movieImageRepository.save(movieImage);
      }
    });
  }

  @Test
  public void testGetListPage() {
//    PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
//    movieRepository.getListPage(PageRequest.of(0, 10, Sort.Direction.DESC)).forEach(m -> log.info(Arrays.toString(m)));
    movieRepository.getListPage(PageRequest.of(0,10,Sort.Direction.DESC,"mno")).forEach(m ->log.info(Arrays.toString(m)));

//    Page<Object[]> result = movieRepository.getListPage(pageRequest);
//    for(Object[] obj : result.getContent()) {
//      log.info(Arrays.toString(obj));
//    }
  }

  @Test
  public void testGetMovieWithAll() {
    movieRepository.getMovieWithAll(100L).forEach(m -> log.info(Arrays.toString(m)));
  }
}
