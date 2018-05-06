package com.newoneplus.dresshub.Model;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReviewDao {
    private final JdbcTemplate jdbcTemplate;
    public ReviewDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public ArrayList<Review> getByUser(String userId) {
        String sql= "SELECT * FROM REVIEW WHERE USER = ?";
        Object[] params = {userId};
        try {
            return (ArrayList<Review>) jdbcTemplate.queryForObject(sql, params, (RowMapper) (rs, rowNum) -> {
                ArrayList<Review> reviews = new ArrayList();
                do {
                    Review review = getValidReview(rs);
                    reviews.add(review);
                } while (rs.next());
                return reviews;
            });
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public ArrayList<Review> getByProducr(int productId) {
        String sql = "SELECT * FROM REVIEW WHERE PRODUCT = ?";
        Object[] params = {productId};
        try {
            return (ArrayList<Review>) jdbcTemplate.queryForObject(sql, params, (RowMapper) (rs, rowNum) -> {
                ArrayList<Review> reviews = new ArrayList();
                do {
                    Review review = getValidReview(rs);
                    reviews.add(review);
                } while (rs.next());
                return reviews;
            });
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Review get(int id) {
        String sql= "SELECT * FROM REVIEW WHERE ID = ?";
        Object[] params = {id};
        try {
            return (Review) jdbcTemplate.queryForObject(sql, params, (RowMapper) (rs, rowNum) -> {
                return getValidReview(rs);
            });
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    public int insert(Review review) {
        String sql = "INSERT INTO REVIEW(TITLE, COMMENT, RATE, DATE, USER, PRODUCT, LEASE_START, LEASE_END, IMAGE_URL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {review.getTitle(), review.getComment(), review.getRate(), review.getDate(),
                        review.getUserId(), review.getProductId(), review.getLeaseStart(), review.getLeaseEnd(),
                        review.getImageUrl()};

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0 ; i < params.length ; i++) {
                ps.setObject(i+1, params[i]);
            }
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void update(Review review) {
        String sql = "UPDATE REVIEW SET TITLE = ?, COMMENT = ?, RATE = ?, DATE = ?, USER= ?, PRODUCT = ?," +
                " LEASE_START = ?, LEASE_END = ?, IMAGE_URL = ? WHERE ID =" + review.getId();
        Object[] params = {review.getTitle(), review.getComment(), review.getRate(), review.getDate(),
                review.getUserId(), review.getProductId(), review.getLeaseStart(), review.getLeaseEnd(),
                review.getImageUrl()};
        jdbcTemplate.update(sql, params);
    }

    private Review getValidReview(ResultSet rs) throws SQLException {
        Review review = new Review();
        review.setId(rs.getInt("ID"));
        review.setTitle(rs.getString("TITLE"));
        review.setComment(rs.getString("COMMENT"));
        review.setRate(rs.getFloat("RATE"));
        review.setDate(rs.getString("DATE"));
        review.setUserId(rs.getString("USER"));
        review.setProductId(rs.getInt("PRODUCT"));
        review.setLeaseStart(rs.getString("LEASE_START"));
        review.setLeaseEnd(rs.getString("LEASE_END"));
        review.setImageUrl(rs.getString("IMAGE_URL"));
        return review;
    }

    public void delete(int insertedId) {
        String sql = "DELETE FROM REVIEW WHERE ID = ?";
        Object[] params = {insertedId};
        jdbcTemplate.update(sql, params);
    }
}
