package com.newoneplus.dresshub.Model;

import com.newoneplus.dresshub.Model.ThumbUp;
import lombok.Cleanup;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public ArrayList<ThumbUp> getLikeProductIdList(String userId){
        Object[] params = {userId};
        ArrayList<ThumbUp> productIds = null;
        try {
            productIds = (ArrayList<ThumbUp>) jdbcTemplate.queryForObject(
                    "SELECT * FROM THUMB_UP WHERE USER= ?", params, (RowMapper) (rs, rowNum) -> {
                        ArrayList<ThumbUp> arrayList = new ArrayList();
                        do{
                            ThumbUp thumbUp = new ThumbUp();
                            thumbUp.setId(rs.getInt("id"));
                            thumbUp.setUser(rs.getString("user"));
                            thumbUp.setProduct(rs.getInt("product"));
                            //TODO 아직 PRODUCT ID가 이름이 없음  , 왜래키 그냥 이대로 가져올 수 있는지도 모름
                            arrayList.add(thumbUp);
                        }while(rs.next());
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

    //   페이징 처리를 위해서 db count하는 코드
    public int getCount(String user) {
        String sql = "SELECT COUNT(*) FROM thumb_up WHERE USER ='"+user+"'";
        return  jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // user가 thumb_up한 product 가져오는 코드
    public ArrayList<Product> getThumbUpProductList(int page, User user) {
        ArrayList<Product> productList = null;
        try {
            productList = (ArrayList<Product>) jdbcTemplate.queryForObject
                    ("SELECT PRODUCT, NAME, THUMBNAIL_IMAGE, DEPOSIT, COST_PER_DAY, CONTENTS, CATEGORY, STATE, SIZE " +
                            "FROM THUMB_UP JOIN PRODUCT ON THUMB_UP.PRODUCT= PRODUCT.ID " +
                            "WHERE (@ROWNUM :=" + (page * 25 - 25) + ") =" + (page * 25 - 25) +  " LIMIT " + (page * 25 - 25) + ", 25;", (RowMapper<Object>) (rs, rowNum) -> {
                        List<Product> products = new ArrayList<>();
                        do  {
                            Product product = new Product();
                            product.setId(rs.getInt("product"));
                            product.setName(rs.getString("name"));
                            product.setThumbnailImage(rs.getString("thumbnail_image"));
                            product.setContents(rs.getString("contents"));
                            product.setCategory(rs.getString("category"));
                            product.setState(rs.getString("state"));
                            product.setSize(rs.getString("size"));
                            product.setDeposit(rs.getInt("deposit"));
                            product.setCostPerDay(rs.getInt("cost_per_day"));
                            products.add(product);
                        }while((rs.next()));
                        return products;
                    });
        }catch (EmptyResultDataAccessException e){
            productList = null;
        }
        return productList;
    }

    //업데이트문은 필요 없을듯
}
