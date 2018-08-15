package com.newoneplus.dresshub.Controller;

import com.newoneplus.dresshub.ImageProcesser;
import com.newoneplus.dresshub.Model.*;
import com.newoneplus.dresshub.Repository.ProductImageRepository;
import com.newoneplus.dresshub.Repository.ProductRepository;
import com.newoneplus.dresshub.Repository.ThumbUpRepository;
import com.newoneplus.dresshub.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/product")
@AllArgsConstructor
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductImageRepository productImageRepository;

    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return productRepository.findById(id).get();
    }

    @GetMapping(value = "/list")
    public List<Product> getProductList() {
        return productRepository.findAllByOrderByIdDesc();
    }

    @GetMapping(value = "/list/search")
    public Page getProductList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String category, @RequestParam(defaultValue = "") String provider) {
        return productService.getProductList(page - 1, category, provider);
    }

    @PostMapping
    public Product productCreate(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping
    public void productUpdate(@RequestBody Product product) {
        productRepository.save(product);
    }

    @DeleteMapping(value = "/{id}")
    public void productDelete(@PathVariable Integer id) {
        productRepository.delete(productRepository.findById(id).get());
    }

    @GetMapping(value = "/list/thumbup")
    public List<Product> getProductListByThumbUp(List<Integer> productIdList) {
        return productRepository.findAllByProductList(productIdList);
    }

    @GetMapping(value = "/list/basket")
    public List<Product> getProductListByBasket(List<Integer> productIdList) {
        return productRepository.findAllByProductList(productIdList);
    }

    @GetMapping(value = "/image/search")
    public List<ProductImage> getProductImageList(@RequestParam(value = "productId", required = false) Long paramId) {
        if (paramId == null) {
            return productImageRepository.findAllByOrderByIdDesc();
        } else {
            return productImageRepository.findAllByProductIdOrderByIdDesc(paramId);
        }
    }

    @PostMapping(value = "/image")
    public void createProductImage(@RequestParam("file") MultipartFile productImage) throws IOException {
        ImageProcesser imageProcesser = new ImageProcesser();
        String filename = productImage.getOriginalFilename();
        String path = System.getProperty("user.dir") + "/out/production/resources/static/product_image/";
        new File(path).mkdirs(); // 디렉토리 생성
        if (productImage == null) {
            return;
        }
        BufferedImage image2 = imageProcesser.getMediumImage(productImage.getInputStream());
        writeImage(filename, path, image2, "medium");

        BufferedImage image3 = imageProcesser.getSmallImage(productImage.getInputStream());
        writeImage(filename, path, image3, "small");

        productImage.transferTo(new File(path + "origin" + filename));
    }

    private void writeImage(String filename, String path, BufferedImage image2, String size) throws IOException {
        String sizeFileName = size + filename;
        ImageIO.write(image2, "jpg", new File(path + sizeFileName));
    }

    @GetMapping("/list/searching")
    public Page<Product> get(@RequestParam(defaultValue = "null") String name,
                             @RequestParam(defaultValue = "null") String provider,
                             @RequestParam(defaultValue = "null") String contents, Pageable pageable) {
        if (name != null && !name.equals("null")) {
            List<Product> map = productRepository.findAllByName(name, pageable);
            List<Integer> ids = new ArrayList<>();
            for (Product p : map) {
                ids.add(p.getId());
            }
            return productRepository.findByIdIn(ids, pageable);
        } else if (provider != null && !provider.equals("null")) {
            Page<Product> lists = productRepository.findAllByProvider(provider, pageable);
            return lists;
        } else if (contents != null && !contents.equals("null")) {
            String[] words = contents.split(" ");

            ArrayList fullResult = new ArrayList();
            List<Product> products = productRepository.findAll();

            for (Product p : products) {
                for (String w : words) {
                    if (p.getName().contains(w)) {
                        p.setSearchPriority(p.getSearchPriority() + 2);
                    } else if (p.getContents().contains(w)) {
                        p.setSearchPriority(p.getSearchPriority() + 1);
                    }
                }
                if (p.getSearchPriority() > 1) {
                    fullResult.add(p);
                }
            }
            fullResult.sort((Comparator) (o, t1) -> {
                if (((Product) o).getSearchPriority() < ((Product) t1).getSearchPriority()) {
                    return 1;
                } else if (((Product) o).getSearchPriority() == ((Product) t1).getSearchPriority()) {
                    return 0;
                } else {
                    return -1;
                }
            });
            ArrayList result = new ArrayList();
            for (int i = pageable.getPageSize() * pageable.getPageNumber(); i < (pageable.getPageSize() + 1) * pageable.getPageNumber(); i++) {
                result.add(fullResult.get(i));
            }
            Page<Product> map = productRepository.findAllByProvider(provider, pageable);
            List<Integer> ids = new ArrayList<>();
            for (Product p : map) {
                ids.add(p.getId());
            }
            return productRepository.findByIdIn(ids, pageable);
//            return productRepository.findAllByProvider(provider, pageable);

        } else {
            return null;
        }
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String checkUser(NullPointerException e) {
        return "403";
    }
}