package com.newoneplus.dresshub.Model;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public String sql;


    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//    public Product get(long id) {
//        Object[] params = {id};
//        Product product = null;
//
//        try {
//            product = (Product) jdbcTemplate.queryForObject(
//                    "SELECT * FROM PRODUCT WHERE ID = ?", params, (RowMapper) (rs, rowNum) -> {
//                Product selectesProduct = makeValidProduct(rs);
//                return selectesProduct;
//            });
//        }catch (EmptyResultDataAccessException e){
//            product = null;
//        }
//    return product;
//    }
//

//    public Integer insert(Product product) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        Object[] params = getFullParams(product);
//        jdbcTemplate.update(con -> {
//            @Cleanup
//            PreparedStatement preparedStatement = con.prepareStatement(
//                  "INSERT INTO PRODUCT(NAME, THUMBNAIL_IMAGE, CONTENTS, COST_PER_DAY, DEPOSIT, SALE_PRICE," +
//                          "CATEGORY, CONSIGMENT_START, CONSIGMENT_END, STATE, DELEVERY_TYPE, PROVIDER," +
//                          "LIKES, REGISTRATION_DATE, LEAST_LEASE_DAY, SIZE)" +
//                          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)",
//                  Statement.RETURN_GENERATED_KEYS);
//          for(int i = 0 ; i < params.length ; i++){
//              preparedStatement.setObject(i+1, params[i]);
//          }
//          return preparedStatement;
//
//        }, keyHolder);
//        return keyHolder.getKey().intValue();
//    }

//    public void update(Product product) {
//        Object[] params = getFullParams(product);
//        jdbcTemplate.update("UPDATE PRODUCT SET NAME = ?, THUMBNAIL_IMAGE = ?, CONTENTS = ?, COST_PER_DAY = ?, DEPOSIT = ?," +
//                " SALE_PRICE = ?,CATEGORY = ?, CONSIGMENT_START = ?, CONSIGMENT_END = ?, STATE = ?, DELEVERY_TYPE = ?" +
//                ", PROVIDER = ?, LIKES = ?, REGISTRATION_DATE = ?, LEAST_LEASE_DAY = ?, SIZE = ? WHERE id ="+ product.getId(), params);
//
//    }

//    public void delete(int id) {
//        Object[] params = {id};
//        jdbcTemplate.update("DELETE FROM PRODUCT WHERE ID = ?", params);
//    }
//
//    public ArrayList<Product> getList(String arrangeQuery) {
//        return getProducts("SELECT * FROM PRODUCT ORDER BY " + arrangeQuery);
//    }

//page에 따라 데이터 가져오기 위한 코드
//    @RowNUM은 없어도 되는데 나중에 어떻게 될 지몰라서 일단 들여놓음 가져오는 게시물에 순서대로 id 매기는 코드
    public ArrayList<Product> getList(int page, String category, String arrangeQuery) {

        if(!category.equals("null")){
            category = " AND CATEGORY LIKE'" + category + "%'";
        }else{
            category="";
        }
        String sql = "SELECT * FROM PRODUCT WHERE (@ROWNUM :=" + (page * 25 - 25) + ") =" + (page * 25 - 25) + category +" ORDER BY " + arrangeQuery + " LIMIT " + (page * 25 - 25) + ", 25;";
        return getProducts(sql);
    }


//    Product가져오는 부분이 중복되서 리팩토링 기존의 코드는 변경없음
    private ArrayList<Product> getProducts(String sql) {
        ArrayList<Product> productList = null;
        try {
            productList = (ArrayList<Product>) jdbcTemplate.queryForObject
                    (sql, (RowMapper<Object>) (rs, rowNum) -> {
                        List<Product> products = new ArrayList<>();
                        do  {
                            Product selectesProduct = makeValidProduct(rs);
                            products.add(selectesProduct);
                        }while((rs.next()));
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
        product.setConsigmentEnd(String.valueOf(rs.getDate("CONSIGMENT_END")));
        product.setConsigmentStart(String.valueOf(rs.getDate("CONSIGMENT_START")));
        product.setContents(rs.getString("CONTENTS"));
        product.setCostPerDay(rs.getInt("COST_PER_DAY"));
        product.setDeleveryType(rs.getString("DELEVERY_TYPE"));
        product.setDeposit(rs.getInt("DEPOSIT"));
        product.setId(rs.getLong("ID"));
        product.setThumbnailImage(rs.getString("THUMBNAIL_IMAGE"));
        product.setName(rs.getString("NAME"));
        product.setProvider(rs.getString("PROVIDER"));
        product.setSalePrice(rs.getInt("SALE_PRICE"));
        product.setState(rs.getString("STATE"));
        product.setLikes(rs.getInt("LIKES"));
        product.setRegistrationDate(rs.getDate("REGISTRATION_DATE"));
        product.setLeastLeaseDay(rs.getInt("LEAST_LEASE_DAY"));
        product.setSize(rs.getString("SIZE"));
        return product;
    }


//    private Object[] getFullParams(Product product)  {
//        java.util.Date getConsigmentStart = null;
//        java.util.Date getConsigmentEnd=null;
//        if(!product.getConsigmentStart().equals("")&&!product.getConsigmentEnd().equals("")){
//            try {
//                getConsigmentStart  = format.parse(product.getConsigmentStart());
//                getConsigmentEnd = format.parse(product.getConsigmentEnd());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return new Object[]{product.getName(), product.getThumbnailImage(), product.getContents(), product.getCostPerDay(),
//                product.getDeposit(), product.getSalePrice(), product.getCategory(),getConsigmentStart,getConsigmentEnd, product.getState(),
//                product.getDeleveryType(), product.getProvider(), product.getLikes(),
//                product.getRegistrationDate(), product.getLeastLeaseDay(), product.getSize()};
//
//    }

//   페이징 처리를 위해서 db count하는 코드
    public int getCount(String category) {
        String sql = "SELECT COUNT(*) FROM PRODUCT";
        if( !category.equals("null") ){
            category = " where category LIKE '"+ category+"%'";
            sql = sql + category;
        }
         return  jdbcTemplate.queryForObject(sql, Integer.class);
    }
}