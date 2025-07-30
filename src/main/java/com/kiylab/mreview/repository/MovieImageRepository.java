package com.kiylab.mreview.repository;

import com.kiylab.mreview.domain.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
