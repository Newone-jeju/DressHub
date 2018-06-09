package com.newoneplus.dresshub.Repository;

import com.newoneplus.dresshub.Model.LeaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseInfoRepository extends JpaRepository<LeaseInfo, Long> {
    List<LeaseInfo> findById(int id);
    List<LeaseInfo> findAllByLeaser(String leaser);
    List<LeaseInfo> findAllByProduct(int product);
    List<LeaseInfo> findAllByLeaserAndProduct(String leaser, int product);

    void deleteById(int Id);
    LeaseInfo save(LeaseInfo leaseInfo);
}
