package com.newoneplus.dresshub.Dao;

import com.newoneplus.dresshub.Data.User;
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
}
