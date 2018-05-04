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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    private String sql;
    private String sql1;

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


    public void delete(int id) {
        Object[] params = {id};
        jdbcTemplate.update("DELETE FROM PRODUCT_IMAGE WHERE ID = ?", params);
    }

    public ArrayList<ProductImage> getProductImageList(int product_id, String arrangeQuery) {
        String sql = "select * from product_image where product_id = ? order by ?";
        Object[] params = {product_id, arrangeQuery};
        return getProductImages(sql, params);
    }



    public ArrayList<ProductImage> getProductImageList(String arrangeQuery) {
        String sql = "select * from product_image  order by ?";
        Object[] params = { arrangeQuery};
        return getProductImages(sql, params);
    }


    private ArrayList<ProductImage> getProductImages(String sql, Object[] params) {
        ArrayList<ProductImage> productImageList = null;
        try{
            productImageList = (ArrayList<ProductImage>) jdbcTemplate.queryForObject(sql,params, (RowMapper<Object>) (rs, rowNum) -> {
                List<ProductImage> productImages = new ArrayList<>();
                do  {
                    ProductImage productImage = new ProductImage();
                    productImage.setId(rs.getInt("id"));
                    productImage.setProductId(rs.getInt("product_id"));
                    productImage.setImage(rs.getString("image"));
                    productImage.setImageSize(rs.getString("image_size"));
                    productImages.add(productImage);
                }while((rs.next()));
                return productImages;
            });
        }catch (EmptyResultDataAccessException e){
            productImageList = null;
        }
        return productImageList;
    }
}
