package com.newoneplus.dresshub.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class BasketDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Basket get(int id) {
        Object[] params = {id};
        Basket basket = null;

        try {
            basket = (Basket) jdbcTemplate.queryForObject(
                    "SELECT * FROM BASKET WHERE ID = ?", params, (RowMapper) (rs, rowNum) -> {
                        Basket basket1 = new Basket();
                        basket1.setId(rs.getInt("id"));
                        basket1.setUserId(rs.getString("user_id"));
                        basket1.setProductId(rs.getInt("product_id"));
                        return basket1;
                    });
        } catch (EmptyResultDataAccessException e) {
            basket = null;
        }
        return basket;
    }

//    user에 따른 장바구니 리스트 가져오기
    public ArrayList<Basket> getBasketList(String userId) {
        Object[] params = {userId};
        ArrayList<Basket> basketArrayList= null;
        try {
            basketArrayList = (ArrayList<Basket>) jdbcTemplate.queryForObject(
                    "SELECT * FROM BASKET WHERE user_id = ?", params, (RowMapper) (rs, rowNum) -> {
                        ArrayList<Basket> basketArrayList1 = new ArrayList<>();
                        do{
                            Basket basket1 = new Basket();
                            basket1.setId(rs.getInt("id"));
                            basket1.setUserId(rs.getString("user_id"));
                            basket1.setProductId(rs.getInt("product_id"));
                            basketArrayList1.add(basket1);

                        }while(rs.next());
                        return basketArrayList1;
                    });
        } catch (EmptyResultDataAccessException e) {
            basketArrayList = null;
        }
        return basketArrayList;
    }
    public int insert(Basket basket) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Object[] params = {basket.getUserId(),basket.getProductId() };
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("insert into basket(user_id, product_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void update(Basket basket) {
        Object[] params = {basket.getUserId(), basket.getProductId(), basket.getId()};
        jdbcTemplate.update("update basket set user_id = ?, product_id = ? where id = ?", params);
    }


    public void delete(int id) {
        Object[] params = {id};
        jdbcTemplate.update("delete from basket where id = ?", params);
    }



}
