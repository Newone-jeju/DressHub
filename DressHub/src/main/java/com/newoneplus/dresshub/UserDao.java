package com.newoneplus.dresshub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

public class UserDao {
    @Value("${db.classname")
    private String classname;
    @Value("${db.url")
    private String url;
    @Value("${db.username")
    private String username;
    @Value("${db.password")
    private String password;




    public User get(String id) throws ClassNotFoundException {

        DataSource dataSource = new SimpleDriverDataSource();
        ((SimpleDriverDataSource) dataSource).setDriverClass((Class<? extends Driver>) Class.forName(classname));
        ((SimpleDriverDataSource) dataSource).setUrl(url);
        ((SimpleDriverDataSource) dataSource).setUsername(username);
        ((SimpleDriverDataSource) dataSource).setPassword(password);

        return null;
    }
}
