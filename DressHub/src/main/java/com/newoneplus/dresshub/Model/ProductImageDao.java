package com.newoneplus.dresshub.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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





}
