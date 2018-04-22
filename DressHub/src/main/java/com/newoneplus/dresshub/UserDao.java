package com.newoneplus.dresshub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.SQLException;

public class UserDao {
    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    private JdbcTemplate jdbcTemplate;




    public User get(String id) throws ClassNotFoundException, SQLException {
        DataSource dataSource = getDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select * from dresshub where id = ?";
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
        DataSource dataSource = getDataSource();
        String sql = "insert into dresshub(id, password, name, email, address, " +
                "phone_number, nickname, introduce, open_private_info," +
                " certification, resister_date) " +
                "values(?, ?, ?, ?, ?," +
                " ?, ?, ?, ?, ?, ? )";
        Object[] params = new Object[]{user.getId(), user.getPassword(), user.getName(), user.getEmail(), user.getAddress(),
        user.getPhoneNumber(), user.getNickname(), user.getIntroduce(), user.isOpenPrivateInfo(), user.isCertification(),
                user.getResisterDate()};
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, params);
    }

    private DataSource getDataSource() throws ClassNotFoundException {
        DataSource dataSource = new SimpleDriverDataSource();
        ((SimpleDriverDataSource) dataSource).setDriverClass((Class<? extends Driver>)Class.forName("com.mysql.jdbc.Driver"));
        ((SimpleDriverDataSource) dataSource).setUrl("jdbc:mysql://localhost/");
        ((SimpleDriverDataSource) dataSource).setUsername("");
        ((SimpleDriverDataSource) dataSource).setPassword("");
        return dataSource;
    }

    public void deleteAll() throws ClassNotFoundException {
        DataSource dataSource = getDataSource();
        String sql = "delete from dresshub";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql);
    }
}
