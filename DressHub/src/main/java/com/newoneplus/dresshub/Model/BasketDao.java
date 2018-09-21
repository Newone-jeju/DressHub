//package com.newoneplus.dresshub.Model;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//
//import java.sql.PreparedStatement;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class BasketDao {
//
//    JdbcTemplate jdbcTemplate;
//
//    public BasketDao(JdbcTemplate jdbcTemplate) {
//       this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public Basket get(int id) {
//        Object[] params = {id};
//        Basket basket = null;
//
//        try {
//            basket = (Basket) jdbcTemplate.queryForObject(
//                    "SELECT * FROM BASKET WHERE ID = ?", params, (RowMapper) (rs, rowNum) -> {
//                        Basket basket1 = new Basket();
//                        basket1.setId(rs.getInt("id"));
//                        basket1.setLiker(rs.getString("user_id"));
//                        basket1.setProductId(rs.getInt("product_id"));
//                        return basket1;
//                    });
//        } catch (EmptyResultDataAccessException e) {
//            basket = null;
//        }
//        return basket;
//    }
//
////    user에 따른 장바구니 리스트 가져오기
//    public HashMap<String, Object> getBasketList(String liker) {
//        Object[] params = {liker};
//        HashMap<String, Object> hashMap = null;
//
//        try {
//            hashMap = (HashMap<String, Object>) jdbcTemplate.queryForObject(
//                    "SELECT * FROM BASKET JOIN PRODUCT ON BASKET.product_id = PRODUCT.id WHERE user_id = ?", params, (RowMapper) (rs, rowNum) -> {
//                        HashMap<String, Object> hashMap1 = new HashMap<>();
//                        ArrayList<Basket> basketArrayList = new ArrayList<>();
//                        ArrayList<Product> productArrayList = new ArrayList<>();
//                        do{
//                            Basket basket = new Basket();
//                            basket.setId(rs.getInt("id"));
//                            basket.setLiker(rs.getString("user_id"));
//                            basket.setProductId(rs.getInt("product_id"));
//                            basketArrayList.add(basket);
//                            Product product = new Product();
//                            product.setCategory(rs.getString("CATEGORY"));
//                            product.setConsigmentEnd(String.valueOf(rs.getDate("CONSIGMENT_END")));
//                            product.setConsigmentStart(String.valueOf(rs.getDate("CONSIGMENT_START")));
//                            product.setContents(rs.getString("CONTENTS"));
//                            product.setCostPerDay(rs.getInt("COST_PER_DAY"));
//                            product.setId(rs.getLong("product.id"));
//                            product.setDeleveryType(rs.getString("DELEVERY_TYPE"));
//                            product.setDeposit(rs.getInt("DEPOSIT"));
//                            product.setThumbnailImage(rs.getString("THUMBNAIL_IMAGE"));
//                            product.setName(rs.getString("NAME"));
//                            product.setProvider(rs.getString("PROVIDER"));
//                            product.setSalePrice(rs.getInt("SALE_PRICE"));
//                            product.setState(rs.getString("STATE"));
//                            product.setLikes(rs.getInt("LIKES"));
//                            product.setRegistrationDate(rs.getDate("REGISTRATION_DATE"));
//                            product.setLeastLeaseDay(rs.getInt("LEAST_LEASE_DAY"));
//                            product.setSize(rs.getString("SIZE"));
//                            productArrayList.add(product);
//                        }while(rs.next());
//                        hashMap1.put("basket", basketArrayList);
//                        hashMap1.put("product", productArrayList);
//
//                        return hashMap1;
//                    });
//        } catch (EmptyResultDataAccessException e) {
//            hashMap = null;
//        }
//        return hashMap;
//    }
//    public int insert(Basket basket) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        Object[] params = {basket.getLiker(),basket.getProductId() };
//        jdbcTemplate.update(con -> {
//            PreparedStatement preparedStatement = con.prepareStatement("insert into basket(user_id, product_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
//            for (int i = 0; i < params.length; i++) {
//                preparedStatement.setObject(i + 1, params[i]);
//            }
//            return preparedStatement;
//        }, keyHolder);
//        return keyHolder.getKey().intValue();
//    }
//
//    public void update(Basket basket) {
//        Object[] params = {basket.getLiker(), basket.getProductId(), basket.getId()};
//        jdbcTemplate.update("update basket set user_id = ?, product_id = ? where id = ?", params);
//    }
//
//
//    public void delete(int id) {
//        Object[] params = {id};
//        jdbcTemplate.update("delete from basket where id = ?", params);
//    }
//
//
//
//}
