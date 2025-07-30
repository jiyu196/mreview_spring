package com.kiylab.mreview.repository;

import com.kiylab.mreview.domain.entity.Member;
import com.kiylab.mreview.domain.entity.Movie;
import com.kiylab.mreview.domain.entity.Review;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReviewRepositoryTest {

  @Autowired
  private ReviewRepository reviewRepository;

  @Test
  public void testExist() {
    Assertions.assertNotNull(reviewRepository);
  }

  @Test
  @Transactional
  public void insertReviews() {
    IntStream.rangeClosed(1, 200).forEach(i -> {
      Long mno = (long)(Math.random()*100) + 1;
      Long mid = ((long)(Math.random()*100) + 1);
      Member member = Member.builder().mid(mid).build();
      Movie movie = Movie.builder().mno(mno).build();

      Review movieReview = Review.builder()
              .member(member)
              .movie(movie)
              .grade((int)(Math.random()*5) + 1)
              .text("이 영화에 대한 느낌 ..." + i)
              .build();
      reviewRepository.save(movieReview);
    });
  }

  @Test
//  @Transactional(readOnly = true)
  public void testFindByMovieMno() {
    reviewRepository.findByMovie_mno(100L).forEach(r -> {
      log.info(r);
      log.info(r.getMember().getEmail());
//      log.info(r.getMovie().getTitle());
    });
  }



}
