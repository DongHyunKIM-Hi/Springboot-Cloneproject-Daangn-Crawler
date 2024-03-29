package com.webproject.clone_project_daangn.controller;

import com.webproject.clone_project_daangn.model.dto.ProductRequestDto;
import com.webproject.clone_project_daangn.model.entity.Product;
import com.webproject.clone_project_daangn.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.NullType;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/api/hot_articles")
    public List<Product> getPopularProducts() {
        List<Product> popluarlist =  productService.findAllProduct();
        Collections.shuffle(popluarlist);
        return popluarlist;
    }

    @GetMapping("/api/hot_articles/{id}")
    public Product getProductDetail(@PathVariable String id) {
        return productService.findProductDetail(id);
    }

    @GetMapping(value = {"/api/region/{val2}/{val3}", "/api/region/{val2}"})
    public List<Product> find_hot( @PathVariable String val2, @PathVariable(required = false) String val3){
        if (val3 == null){
            return productService.find_hot(val2);
        }
        String want = val2 +" "+val3;
        return productService.find_hot(want);
    }

    @PostMapping("/api/upload")
    public void PostProduct(@RequestBody ProductRequestDto requestDto){
        productService.createProduct(requestDto);
    }

    @GetMapping("api/search/{query}")
    public List<Product> getSearchResults(@PathVariable String query) {
        return productService.searchProduct(query);
    }
}