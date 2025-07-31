package com.kiylab.mreview.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Movie extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mno;

  private String title;

//  private List<MovieImage> images;
// onetomany. 이것만 있으면 다 해결이 되는데 이건 안여기서는 안씀
  //movie타입이 되는순간., movie타입이 됨.

}
