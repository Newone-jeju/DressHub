package com.newoneplus.dresshub.Model;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LeaseInfoDao {
    private final JdbcTemplate jdbcTemplate;
    public LeaseInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public LeaseInfo get(int id){
        String sql = "SELECT * FROM lease_info where id = ?";
        Object[] params = {id};
        LeaseInfo leaseInfo = jdbcTemplate.queryForObject(sql, params, ((rs, rowNum) -> {
            return getValidLeaseInfo(rs);
        }));
        return leaseInfo;

    }


    public ArrayList<LeaseInfo> getInfosByLeaser(String userId) {
        String sql = "SELECT * FROM lease_info WHERE leaser = ?";
        Object[] params = {userId};
        ArrayList<LeaseInfo> leaseInfos = getLeaseInfos(sql, params);
        return leaseInfos;
    }

    public ArrayList<LeaseInfo> getInfosByProduct(int productId) {
        String sql = "SELECT * FROM lease_info WHERE product = ?";
        Object[] params = {productId};

        ArrayList<LeaseInfo> leaseInfos = getLeaseInfos(sql, params);
        return leaseInfos;
    }


    public int insert(LeaseInfo leaseInfo) {
        String sql = "INSERT INTO lease_info(lease_day, return_day, " +
                "total_price, lease_site, return_site , leaser, product, status, order_time, order_wait_start," +
                "order_waite_end, release_time, lease_deliver_start, lease_deliver_end, lease_start, lease_end" +
                "return_start, return_end, laundry_start, laundry_end, ready" +
                "VALUES (?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {leaseInfo.getLeaseDay(), leaseInfo.getReturnDay(), leaseInfo.getTotalPrice(),
                leaseInfo.getLeaseSite(), leaseInfo.getReturnSite(),
                leaseInfo.getLeaser(), leaseInfo.getProduct()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0 ; i < params.length ; i ++){
                ps.setObject(i+1, params[i]);
            }
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public ArrayList<LeaseInfo> getInfosByUserAndProduct(String userId, int productId) {
        String sql = "SELECT * FROM lease_info WHERE leaser = ? and product = ?";
        Object[] params = {userId, productId};

        ArrayList<LeaseInfo> leaseInfos = getLeaseInfos(sql, params);
        return leaseInfos;
    }

    private LeaseInfo getValidLeaseInfo(ResultSet rs) throws SQLException {
        LeaseInfo leaseInfo = new LeaseInfo();
        leaseInfo.setId(rs.getInt("id"));
        leaseInfo.setLeaseDay(rs.getString("lease_day"));
        if (rs.getString("return_day") != null) {
            leaseInfo.setReturnDay(rs.getString("return_day"));
        }else{
            leaseInfo.setReturnDay("null");
        }
        leaseInfo.setTotalPrice(rs.getInt("total_price"));
        leaseInfo.setLeaseSite(rs.getString("lease_site"));
        if (rs.getString("return_day") != null) {
            leaseInfo.setReturnSite(rs.getString("return_site"));
        }else{
            leaseInfo.setReturnSite("null");
        }
        leaseInfo.setLeaser(rs.getString("leaser"));
        leaseInfo.setProduct(rs.getInt("product"));
        return leaseInfo;
    }

    private ArrayList<LeaseInfo> getLeaseInfos(String sql, Object[] params) {
        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
            ArrayList<LeaseInfo> tempLeaseInfos = new ArrayList<>();
            do{
                tempLeaseInfos.add(getValidLeaseInfo(rs));
            }while(rs.next());
            return tempLeaseInfos;
        });
    }

    public void update(LeaseInfo leaseInfo) {
        String sql = "UPDATE lease_info SET lease_day = ?, return_day = ?, " +
                "total_price = ?, lease_site = ?, return_site = ? , leaser = ?, product = ?," +
                "status = ? , order_time = ? , order_wait_start = ? ," +
                "order_waite_end = ?, release_time = ?, lease_deliver_start = ?, lease_deliver_end = ?, " +
                "lease_start = ?, lease_end = ?" +
                "return_start = ?, return_end = ?, laundry_start = ?, laundry_end = ?, ready = ? WHERE id = ? ";
        Object[] params = {leaseInfo.getLeaseDay(), leaseInfo.getReturnDay(), leaseInfo.getTotalPrice(),
                leaseInfo.getLeaseSite(), leaseInfo.getReturnSite(), leaseInfo.getLeaser(), leaseInfo.getProduct(),
                leaseInfo.getId()};
        jdbcTemplate.update(sql,params);

    }
}
