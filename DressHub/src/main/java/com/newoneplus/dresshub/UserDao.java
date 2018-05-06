package com.newoneplus.dresshub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class UserDao {
    @Value("${db.classname}")
    private String classname;
    @Value("${db.url}")
    private String url;
    @Value("${db.password}")
    private String password;
    @Value("${db.username}")
    private String username;

    JdbcTemplate jdbcTemplate = null;

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
   // 로그인 처리
    public boolean isValidUser(String id, String password) {
        boolean retVal;
        try {
            String SQL = "select count(*) from user where id = ? and password = ?";
            int count =  jdbcTemplate.queryForObject(SQL, new Object[]{id, password}, Integer.class);
                if (count == 1) {
                    retVal = true;
                } else {
                    retVal = false;
                }
            } catch (Exception ex) {
                retVal = false;
            }
            return retVal;
        }

}
