package com.newoneplus.dresshub.Model;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class ProductImageDao {

    @Value("${db.classname}")
    private String classname;
    @Value("${db.url}")
    private String url;
    @Value("${db.password}")
    private String password;
    @Value("${db.username}")
    private String username;
    JdbcTemplate jdbcTemplate = null;

    public ProductImageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ProductImage get(int id) {
        Object[] params = {id};
        ProductImage productImage = null;

        try {
            productImage = (ProductImage) jdbcTemplate.queryForObject(
                    "SELECT * FROM PRODUCT_IMAGE WHERE ID = ?", params, (RowMapper) (rs, rowNum) -> {
                        ProductImage getProductImage = new ProductImage();
                        getProductImage.setId(rs.getInt("id"));
                        getProductImage.setProductId(rs.getInt("product_id"));
                        getProductImage.setImage(rs.getString("image"));
                        getProductImage.setImageSize(rs.getString("image_size"));
                        return getProductImage;
                    });
        }catch (EmptyResultDataAccessException e){
            productImage = null;
        }
        return productImage;
    }


    public int insert(ProductImage productImage) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] params = {productImage.getProductId(), productImage.getImage(), productImage.getImageSize()};
        jdbcTemplate.update(con -> {
            @Cleanup
            PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO PRODUCT_IMAGE(PRODUCT_ID, IMAGE, IMAGE_SIZE)" +
                            "VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            for(int i = 0 ; i < params.length ; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void update(ProductImage productImage) {
        Object[] params = {productImage.getProductId(), productImage.getImage(), productImage.getImageSize(), productImage.getId()};
        jdbcTemplate.update("UPDATE PRODUCT_IMAGE SET PRODUCT_ID = ?, IMAGE = ?, IMAGE_SIZE = ?  WHERE ID = ?", params);
    }
}
