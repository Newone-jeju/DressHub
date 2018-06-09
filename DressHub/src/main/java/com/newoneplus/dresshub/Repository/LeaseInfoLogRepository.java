package com.newoneplus.dresshub.Repository;


import com.newoneplus.dresshub.Model.LeaseInfoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseInfoLogRepository extends JpaRepository<LeaseInfoLog, Integer> {
}
