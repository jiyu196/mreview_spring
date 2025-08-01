package com.kiylab.mreview.service;

import com.kiylab.mreview.domain.dto.ReviewDTO;
import com.kiylab.mreview.domain.entity.Member;
import com.kiylab.mreview.domain.entity.Movie;
import com.kiylab.mreview.domain.entity.Review;

import java.util.List;

public sealed interface ReviewService  permits ReviewServiceImpl{
  List<ReviewDTO> getListWithMovie(Long mno);
  Long register(ReviewDTO dto);
  void modify(ReviewDTO dto);
  void remove(Long reviewnum);

  default Review toEntity(ReviewDTO dto){
    return Review.builder()
            .text(dto.getText())
            .grade(dto.getGrade())
            .reviewnum(dto.getReviewnum())
            .member(Member.builder().mid(dto.getMid()).build())
            .movie(Movie.builder().mno(dto.getMno()).build())
            .build();
  }
  default ReviewDTO toDTO(Review review){
    return ReviewDTO.builder()
            .reviewnum(review.getReviewnum())
            .text(review.getText())
            .grade(review.getGrade())
            .email(review.getMember().getEmail())
            .mid(review.getMember().getMid())
            .nickname(review.getMember().getNickname())
            .mno(review.getMovie().getMno())
            .regDate(review.getRegDate())
            .modDate(review.getModDate())
            .build();
  }
}