package com.newoneplus.dresshub;

import lombok.Cleanup;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class ThumbupDao {
    private JdbcTemplate jdbcTemplate;
    public ThumbupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    //상품의 좋아요 개수를 가져오는 기능
    public int getLikes(int productId){
        Object[] params = {productId};
        Integer likes = 0;

        try {
            likes = (Integer) jdbcTemplate.queryForObject(
                    "SELECT * FROM THUMB_UP WHERE PRODUCT = ?", params, (RowMapper) (rs, rowNum) -> {
                        rs.last();
                        return rs.getRow();
                    });
        }catch (EmptyResultDataAccessException e){
            likes = 0;
        }
        return likes;
    }

    //유저가 좋아요를 누른 상품들의 id를 반환하는 기능
    public ArrayList<Integer> getLikeProductIdList(String userId){
        Object[] params = {userId};
        ArrayList<Integer> productIds = null;
        try {
            productIds = (ArrayList<Integer>) jdbcTemplate.queryForObject(
                    "SELECT * FROM THUMB_UP WHERE PRODUCT_id = ?", params, (RowMapper) (rs, rowNum) -> {
                        ArrayList<Integer> arrayList = new ArrayList();
                        while(rs.next()){
                            //TODO 아직 PRODUCT ID가 이름이 없음  , 왜래키 그냥 이대로 가져올 수 있는지도 모름
                            arrayList.add(rs.getInt("PRODUCT"));
                        }
                        return arrayList;
                    });
        }catch (EmptyResultDataAccessException e){
            productIds = null;
        }
        return productIds;
    }

    //좋아요 누르고 좋아요가 생성되어야 하는 경우 호출
    public Integer insert(String userId, int productId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] params = {userId, productId};
        jdbcTemplate.update(con -> {
            @Cleanup
            PreparedStatement preparedStatement = con.prepareStatement(
                    //TODO 아직 PRODUCT ID가 이름이 없음  , 왜래키 그냥 이대로 가져올 수 있는지도 모름
                    "INSERT THUMB_UP(USER, PRODUCT) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;

        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    //좋아요를 철회하는 기능
    public void delete(String userId, int productId){
        Object[] params = {userId, productId};
        jdbcTemplate.update("DELETE FROM THUMB_UP WHERE USER = ? AND PRODUCT = ?", params);
    }


    //업데이트문은 필요 없을듯
}
