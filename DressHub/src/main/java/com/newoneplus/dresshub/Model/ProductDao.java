package com.newoneplus.dresshub.Model;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    @Value("${db.classname}")
    private String classname;
    @Value("${db.url}")
    private String url;
    @Value("${db.password}")
    private String password;
    @Value("${db.username}")
    private String username;
    JdbcTemplate jdbcTemplate = null;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Product get(int id) {
        Object[] params = {id};
        Product product = null;

        try {
            product = (Product) jdbcTemplate.queryForObject(
                    "SELECT * FROM PRODUCT WHERE ID = ?", params, (RowMapper) (rs, rowNum) -> {
                Product selectesProduct = makeValidProduct(rs);

                return selectesProduct;
            });
        }catch (EmptyResultDataAccessException e){
            product = null;
        }
    return product;
    }


    public Integer insert(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] params = {product.getName(), product.getThumbnailImage(), product.getContents(), product.getCostPerDay(),
                        product.getDeposit(), product.getSalePrice(), product.getCategory(),
                        product.getConsigmentStart(), product.getConsigmentEnd(), product.getState(),
                        product.getDeleveryType(), product.getProviderId()};
        jdbcTemplate.update(con -> {
            @Cleanup
            PreparedStatement preparedStatement = con.prepareStatement(
                  "INSERT INTO PRODUCT(NAME, IMAGE, CONTENTS, COST_PER_DAY, DEPOSIT, SALE_PRICE," +
                          "CATEGORY, CONSIGMENT_START, CONSIGMENT_END, STATE, DELEVERY_TYPE, PROVIDER)" +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                  Statement.RETURN_GENERATED_KEYS);
          for(int i = 0 ; i < params.length ; i++){
              preparedStatement.setObject(i+1, params[i]);
          }
          return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void update(Product product) {
        Object[] params = {product.getName(), product.getThumbnailImage(), product.getContents(), product.getCostPerDay(),
                    product.getDeposit(), product.getSalePrice(), product.getCategory(), product.getConsigmentStart(),
                    product.getConsigmentEnd(), product.getState(), product.getDeleveryType(), product.getProviderId()};
        jdbcTemplate.update("UPDATE PRODUCT SET NAME = ?, IMAGE = ?, CONTENTS = ?, COST_PER_DAY = ?, DEPOSIT = ?," +
                " SALE_PRICE = ?,CATEGORY = ?, CONSIGMENT_START = ?, CONSIGMENT_END = ?, STATE = ?, DELEVERY_TYPE = ?" +
                ", PROVIDER = ?", params);

    }

    public void delete(int id) {
        Object[] params = {id};
        jdbcTemplate.update("DELETE FROM PRODUCT WHERE ID = ?", params);
    }

    public ArrayList<Product> getList(String arrangeQuery) {
        ArrayList<Product> productList = null;
        try {
            productList = (ArrayList<Product>) jdbcTemplate.queryForObject
                    ("SELECT * FROM PRODUCT ORDER BY " + arrangeQuery, (RowMapper<Object>) (rs, rowNum) -> {
                List<Product> products = new ArrayList<>();
                while (rs.next()) {
                    Product selectesProduct = makeValidProduct(rs);
                    products.add(selectesProduct);
                }
                return products;
            });
        }catch (EmptyResultDataAccessException e){
            productList = null;
        }
        return productList;
    }


    private Product makeValidProduct(ResultSet rs) throws SQLException {
        Product selectesProduct = new Product();
        selectesProduct.setCategory(rs.getString("CATEGORY"));
        selectesProduct.setConsigmentEnd(rs.getDate("CONSIGMENT_END"));
        selectesProduct.setConsigmentStart(rs.getDate("CONSIGMENT_START"));
        selectesProduct.setContents(rs.getString("CONTENTS"));
        selectesProduct.setCostPerDay(rs.getInt("COST_PER_DAY"));
        selectesProduct.setDeleveryType(rs.getString("DELEVERY_TYPE"));
        selectesProduct.setDeposit(rs.getInt("DEPOSIT"));
        selectesProduct.setId(rs.getInt("ID"));

        selectesProduct.setThumbnailImage(rs.getString("IMAGE"));
        selectesProduct.setName(rs.getString("NAME"));
        selectesProduct.setProviderId(rs.getString("PROVIDER"));
        selectesProduct.setSalePrice(rs.getInt("SALE_PRICE"));
        selectesProduct.setState(rs.getString("STATE"));
        return selectesProduct;
    }
}
