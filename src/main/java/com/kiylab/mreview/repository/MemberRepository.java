package com.kiylab.mreview.repository;

import com.kiylab.mreview.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
