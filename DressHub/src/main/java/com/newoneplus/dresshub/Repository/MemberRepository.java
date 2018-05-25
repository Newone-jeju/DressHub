package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUid(String uid);
    void deleteById(String uid);
}
