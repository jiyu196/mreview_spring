package com.kiylab.mreview.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "movie")
public class MovieImage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long inum;
  private String uuid;
  private String imgName;
  private String path;
  @ManyToOne(fetch = FetchType.LAZY)
//  @Setter
  private Movie movie;
//  private boolean present;
}
