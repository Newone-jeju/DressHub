package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ThumbUp;
import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/thumbUp")
@Slf4j
@AllArgsConstructor
public class ThumbUpController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Product thumbUpProcess(@RequestBody ThumbUp thumbUp)  {
        if(AuthorizationService.getCurrentUser()==null){
            new NullPointerException();
        }
        //TODO 나중에 user에 대해서도 rest로 바뀌면 이렇게 넣을필요없다. 프론트에서 넣어서 가져오니까
        thumbUp.setLiker(AuthorizationService.getCurrentUser().getUid());
        return productService.clickThumup(thumbUp);
    }
    //TODO 나중에 user에 대해서도 rest로 바뀌면 이렇게 넣을필요없다.front에서 넘겨줄거다 Path로 가져오든지 하자
    @GetMapping(value = "/search")
    public Page<ThumbUp> getThumbUpProductList(@RequestParam(value = "page", defaultValue = "1") int page) {
        return productService.getThumbUpProductList(page-1, AuthorizationService.getCurrentUser());
    }
}
