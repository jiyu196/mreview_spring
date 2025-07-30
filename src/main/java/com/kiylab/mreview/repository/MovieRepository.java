package com.kiylab.mreview.repository;

import com.kiylab.mreview.domain.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
  @Query("select m, mi, round(avg(coalesce( r.grade, 0)), 1), count(distinct r) from Movie m " +
         "left join MovieImage mi on mi.movie = m " +
          "left join Review r on r.movie = m group by m")
  Page<Object[]> getListPage(Pageable pageable);

  @Query("select m, mi, round(avg(coalesce(r.grade, 0)), 1), count(distinct r) from Movie m " +
          "join MovieImage mi on mi.movie = m" +
          " left join Review r on r.movie = m where m.mno = :mno group by mi")  // group by를 빼먹으면 행이 다 합쳐짐.
  List<Object[]> getMovieWithAll(Long mno);
}
