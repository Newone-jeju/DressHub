package com.newoneplus.dresshub;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
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
        Object[] params = getFullParams(product);
        jdbcTemplate.update(con -> {
            @Cleanup
            PreparedStatement preparedStatement = con.prepareStatement(
                  "INSERT INTO PRODUCT(NAME, THUMBNAIL_IMAGE, CONTENTS, COST_PER_DAY, DEPOSIT, SALE_PRICE," +
                          "CATEGORY, CONSIGMENT_START, CONSIGMENT_END, STATE, DELEVERY_TYPE, PROVIDER," +
                          "LIKES, REGISTRATION_DATE, LEAST_LEASE_DAY, SIZE)" +
                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)",
                  Statement.RETURN_GENERATED_KEYS);
          for(int i = 0 ; i < params.length ; i++){
              preparedStatement.setObject(i+1, params[i]);
          }
          return preparedStatement;

        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void update(Product product) {
        Object[] params = getFullParams(product);
        jdbcTemplate.update("UPDATE PRODUCT SET NAME = ?, THUMBNAIL_IMAGE = ?, CONTENTS = ?, COST_PER_DAY = ?, DEPOSIT = ?," +
                " SALE_PRICE = ?,CATEGORY = ?, CONSIGMENT_START = ?, CONSIGMENT_END = ?, STATE = ?, DELEVERY_TYPE = ?" +
                ", PROVIDER = ?, LIKES = ?, REGISTRATION_DATE = ?, LEASET_LEASE_DAY = ?, SIZE = ?", params);

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
        Product product = new Product();
        product.setCategory(rs.getString("CATEGORY"));
        product.setConsigmentEnd(rs.getDate("CONSIGMENT_END"));
        product.setConsigmentStart(rs.getDate("CONSIGMENT_START"));
        product.setContents(rs.getString("CONTENTS"));
        product.setCostPerDay(rs.getInt("COST_PER_DAY"));
        product.setDeleveryType(rs.getString("DELEVERY_TYPE"));
        product.setDeposit(rs.getInt("DEPOSIT"));
        product.setId(rs.getInt("ID"));

        product.setThumbnailImage(rs.getString("THUMBNAIL_IMAGE"));
        product.setName(rs.getString("NAME"));
        product.setProviderId(rs.getString("PROVIDER"));
        product.setSalePrice(rs.getInt("SALE_PRICE"));
        product.setState(rs.getString("STATE"));
        product.setLikes(rs.getInt("LIKES"));
        product.setRegDate(rs.getDate("REGISTRATION_DATE"));
        product.setLeastLeaseDay(rs.getInt("LEAST_LEASE_DAY"));
        product.setSize(rs.getString("SIZE"));
        return product;
    }

    private Object[] getFullParams(Product product) {
        return new Object[]{product.getName(), product.getThumbnailImage(), product.getContents(), product.getCostPerDay(),
                product.getDeposit(), product.getSalePrice(), product.getCategory(),
                product.getConsigmentStart(), product.getConsigmentEnd(), product.getState(),
                product.getDeleveryType(), product.getProviderId(), product.getLikes(),
                product.getRegDate(), product.getLeastLeaseDay(), product.getSize()};
    }
}
