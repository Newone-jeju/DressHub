package com.newoneplus.dresshub;


import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

import java.sql.*;

public class ProductDao {
    @Value("${db.classname")
    private String classname;
    @Value("${db.url")
    private String url;
    @Value("${db.password")
    private String password;
    @Value("${db.username")
    private String username;
    public Product get(int id) throws ClassNotFoundException, SQLException {
        DataSource dataSource = new SimpleDriverDataSource();
        ((SimpleDriverDataSource) dataSource).setDriverClass((Class<? extends Driver>) Class.forName(classname));
        ((SimpleDriverDataSource) dataSource).setUrl(url);
        ((SimpleDriverDataSource) dataSource).setUsername(username);
        ((SimpleDriverDataSource) dataSource).setPassword(password);
        Object[] params = {id};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Product product = null;
        try {
            product = (Product) jdbcTemplate.queryForObject("SELECT * FROM PRODUCT WHERE ID = ?", new RowMapper() {
                @Override
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Product selectesProduct = new Product();
                    selectesProduct.setCategory(rs.getString("CATEGORY"));
                    selectesProduct.setConsigmentEnd(rs.getDate("CONSIGMENT_END"));
                    selectesProduct.setConsigmentStart(rs.getDate("CONSIGMENT_START"));
                    selectesProduct.setContents(rs.getString("CONTENTS"));
                    selectesProduct.setCostPerDay(rs.getInt("COST_PER_DAY"));
                    selectesProduct.setDeleveryType(rs.getString("DELEVERY_TYPE"));
                    selectesProduct.setDeposit(rs.getInt("DEPOSIT"));
                    selectesProduct.setId(rs.getInt(id));
                    selectesProduct.setImageUrl(rs.getString("IAMGE"));
                    selectesProduct.setName(rs.getString("NAME"));
                    selectesProduct.setProviderId(rs.getString("PROVIDER"));
                    selectesProduct.setSalePrice(rs.getInt("SALE_PRICE"));
                    selectesProduct.setState(rs.getString("STATE"));

                    return selectesProduct;
                }
            });
        }catch (Exception e){
            product = null;
        }




    return product;
    }
}
