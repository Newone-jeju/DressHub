package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.Model.Product;
import com.newoneplus.dresshub.Model.ThumbUp;
import com.newoneplus.dresshub.Model.User;
import com.newoneplus.dresshub.Repository.ProductRepository;
import com.newoneplus.dresshub.Repository.ThumbUpRepository;
import com.newoneplus.dresshub.Service.AuthorizationService;
import com.newoneplus.dresshub.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/thumbup")
@Slf4j
@AllArgsConstructor
public class ThumbUpController {
    @Autowired
    ProductService productService;
    @Autowired
    ThumbUpRepository thumbUpRepository;

    @PostMapping
    public ThumbUp thumbUpProcess(@RequestBody ThumbUp thumbUp)  {
        if(AuthorizationService.getCurrentUser()==null){
            new NullPointerException();
        }
        //TODO 나중에 user에 대해서도 rest로 바뀌면 이렇게 넣을필요없다. 프론트에서 넣어서 가져오니까
        thumbUp.setLiker(AuthorizationService.getCurrentUser().getUid());
        return productService.clickThumup(thumbUp);
    }
    //TODO 나중에 user에 대해서도 rest로 바뀌면 이렇게 넣을필요없다.front에서 넘겨줄거다 Path로 가져오든지 하자
    @GetMapping(value = "/list/search")
    public Page<ThumbUp> getThumbUpProductList(@RequestParam(value = "page", defaultValue = "1") int page) {
        PageRequest pageRequest = PageRequest.of(page, 25, Sort.Direction.DESC, "id");
        return thumbUpRepository.findAllByLiker(AuthorizationService.getCurrentUser().getUid(), pageRequest);
    }
    @GetMapping(value = "/list/product")
    public List<ThumbUp> getThumbUpListbyProduct(@RequestBody List<Integer> productIdList) {
        return thumbUpRepository.findAllByLikerAndProductList(AuthorizationService.getCurrentUser().getUid(), productIdList);
    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String checkUser(NullPointerException e){
        return "403";
    }

}
