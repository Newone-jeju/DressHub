package com.newoneplus.dresshub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.SQLException;

public class UserDao {
    @Value("${db.classname}")
    private String className = "com.mysql.jdbc.Driver";
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    private JdbcTemplate jdbcTemplate;




    public User get(String id) throws ClassNotFoundException, SQLException {
        DataSource dataSource = new SimpleDriverDataSource();
        ((SimpleDriverDataSource) dataSource).setDriverClass((Class<? extends Driver>)Class.forName(className));
        ((SimpleDriverDataSource) dataSource).setUrl(url);
        ((SimpleDriverDataSource) dataSource).setUsername(username);
        ((SimpleDriverDataSource) dataSource).setPassword(password);
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select * from users where id = ?";
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
}
