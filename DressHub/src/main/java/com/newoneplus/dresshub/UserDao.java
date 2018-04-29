package com.newoneplus.dresshub;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public User get(String id) throws ClassNotFoundException, SQLException {
        String sql = "select * from user where id = ?";
        Object[] params = new Object[]{id};
        try {
            return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setAddress(rs.getString("address"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setNickname(rs.getString("nickname"));
                    user.setIntroduce(rs.getString("introduce"));
                    user.setOpenPrivateInfo(rs.getBoolean("open_private_info"));
                    user.setCertification(rs.getBoolean("certification"));
                    user.setResisterDate(rs.getDate("resister_date"));
                    return user;
                });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void insert(User user) throws ClassNotFoundException {
        String sql = "insert into user(id, password, name, email, address, " +
                "phone_number, nickname, introduce, open_private_info," +
                " certification, resister_date) " +
                "values(?, ?, ?, ?, ?," +
                " ?, ?, ?, ?, ?, ? )";
        Object[] params = new Object[]{user.getId(), user.getPassword(), user.getName(), user.getEmail(), user.getAddress(),
        user.getPhoneNumber(), user.getNickname(), user.getIntroduce(), user.isOpenPrivateInfo(), user.isCertification(),
                user.getResisterDate()};
        jdbcTemplate.update(sql, params);
    }


    public void deleteAll() throws ClassNotFoundException {
        String sql = "delete from user";
        jdbcTemplate.update(sql);
    }

    public boolean loginCheck(String id, String password) {
        String sql = "select * from user where id = ? and password =?";
        Object[] params = new Object[]{id, password};
        try {
                return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setPassword(rs.getString("password"));
                return true;
            });
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
//        return true;
    }

    public User viewUser(User user) {
        String sql = "select * from user where id = ? and password = ?";
        Object[] params = new Object[]{user.getId(), user.getPassword()};
        try {
            return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setNickname(rs.getString("nickname"));
                user.setIntroduce(rs.getString("introduce"));
                user.setOpenPrivateInfo(rs.getBoolean("open_private_info"));
                user.setCertification(rs.getBoolean("certification"));
                user.setResisterDate(rs.getDate("resister_date"));
                return user;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
