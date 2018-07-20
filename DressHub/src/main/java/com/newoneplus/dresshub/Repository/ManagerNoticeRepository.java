package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.ManagerNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerNoticeRepository extends JpaRepository<ManagerNotice, Integer> {
}
