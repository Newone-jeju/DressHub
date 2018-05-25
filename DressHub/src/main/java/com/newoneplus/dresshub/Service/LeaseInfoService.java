package com.newoneplus.dresshub.Service;

import com.newoneplus.dresshub.Model.LeaseInfo;
import com.newoneplus.dresshub.Model.LeaseInfoDao;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class LeaseInfoService {
    @Autowired
    private LeaseInfoDao leaseInfoDao;
    public HashMap getLeaseInfoHashMapById(int id) {
        LeaseInfo leaseInfo = leaseInfoDao.get(id);

        return leaseInfo.toMap();
    }

    public ArrayList getLeaseInfoHashMapsByProduct(Integer product) {
        ArrayList leaseInfos = leaseInfoDao.getInfosByProduct(product);
        for(int i = 0 ; i < leaseInfos.size() ; i++){
            leaseInfos.add(leaseInfos.remove(i));
        }
        return leaseInfos;
    }

    public ArrayList getLeaseInfoHashsMapByUser(String user) {
        ArrayList leaseInfos = leaseInfoDao.getInfosByLeaser(user);
        for(int i = 0 ; i < leaseInfos.size() ; i++){
            leaseInfos.add(leaseInfos.remove(i));  //코드 줄 수를 그냥 줄일려고 하긴 한건데 맞는건가
        }
        return leaseInfos;
    }

    public ArrayList getLeaseInfoHashMapsByUserAndProduct(String user, int product) {
        ArrayList leaseInfos = leaseInfoDao.getInfosByUserAndProduct(user, product);
        for(int i = 0 ; i < leaseInfos.size() ; i++){
            leaseInfos.add(leaseInfos.remove(i));
        }
        return leaseInfos;
    }

    public void update(LeaseInfo leaseInfo) {
        leaseInfoDao.update(leaseInfo);
    }

    public void delete(Integer leaseInfoId) {
        leaseInfoDao.delete(leaseInfoId);
    }

    public boolean askAuthority(Integer leaseInfoId) {
        return leaseInfoDao.get(leaseInfoId).getLeaser().equals(AuthorizationService.getCurrentUser());
    }
}
