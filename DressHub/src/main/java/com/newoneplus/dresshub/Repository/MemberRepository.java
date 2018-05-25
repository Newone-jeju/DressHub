package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

}
