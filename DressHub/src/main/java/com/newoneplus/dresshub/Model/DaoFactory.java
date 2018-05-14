package com.newoneplus.dresshub.Model;

import com.newoneplus.dresshub.Service.MainService;
import com.newoneplus.dresshub.Service.ProductService;
<<<<<<< HEAD
import com.newoneplus.dresshub.ThumbupDao;
import com.newoneplus.dresshub.UserController;
import com.newoneplus.dresshub.UserDao;
=======
>>>>>>> 4a7c96a17909b73d2eecfcaeccb22958db6b0fc1
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {
    @Value("${db.classname}")
    private String className;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;


    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(datasource());
    }

    @Bean
    public DataSource datasource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        } catch (ClassNotFoundException e){
            new RuntimeException(e);
        }
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public UserDao userDao()
    {
        return new UserDao(jdbcTemplate());
    }

    @Bean
    public ProductDao productDao() {return new ProductDao(jdbcTemplate());}


    @Bean
    public MainService mainService(){return new MainService();}

    @Bean

    public ProductService productService(){return new ProductService();}
    @Bean
    public ProductImageDao productImageDao(){return new ProductImageDao(jdbcTemplate());}

//    @Bean
//    public UserController userController()
//    {
//        return new UserController();
//    }

    @Bean
    public ReviewDao reviewDao(){return  new ReviewDao(jdbcTemplate());}

    @Bean
    public LeaseInfoDao leaseInfoDao(){return new LeaseInfoDao(jdbcTemplate());}

    @Bean

    public BasketDao basketDao(){return new BasketDao(jdbcTemplate());}

    @Bean
    public ThumbupDao thumbupDao(){return new ThumbupDao(jdbcTemplate());}
}



