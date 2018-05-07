package com.newoneplus.dresshub.Model;

import lombok.Data;

//일단은 이정도의 데이터가 필요할 것같음
@Data
public class Basket {
    private Integer id;
    private String userId;
    private Integer productId;
}
