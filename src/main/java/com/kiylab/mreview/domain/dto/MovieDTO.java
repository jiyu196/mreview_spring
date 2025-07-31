package com.kiylab.mreview.domain.dto;

import com.kiylab.mreview.domain.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
  private Long mno;
  private String title;

  @Builder.Default
  private List<MovieImageDTO> list = new ArrayList<>();

  private Double avg;
  private Long reviewCnt;
  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
