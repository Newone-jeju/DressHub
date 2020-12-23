package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByUid(String uid);
}
