package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(String uid);
}
