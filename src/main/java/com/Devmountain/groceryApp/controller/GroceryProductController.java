package com.Devmountain.groceryApp.controller;


import com.Devmountain.groceryApp.common.ApiResponse;
import com.Devmountain.groceryApp.dto.GroceryProductDto;
import com.Devmountain.groceryApp.model.Category;
import com.Devmountain.groceryApp.repository.CategoryRepo;
import com.Devmountain.groceryApp.service.GroceryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GroceryProduct")
public class GroceryProductController {
    @Autowired
    GroceryProductService groceryProductService;

    @Autowired
    CategoryRepo categoryRepo;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody GroceryProductDto groceryProductDto) {
        Optional<Category> optionalCategory = categoryRepo.findById(groceryProductDto.getCategoryId());
        if(!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
        }
        groceryProductService.createProduct(groceryProductDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<GroceryProductDto>> getProducts() {
        List<GroceryProductDto> products = groceryProductService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping("/update/{groceryProductId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("groceryProductId") Integer groceryProductId, @RequestBody GroceryProductDto groceryProductDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepo.findById(groceryProductDto.getCategoryId());
        if(!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
        }
        groceryProductService.updateProduct(groceryProductDto, groceryProductId);
        return new ResponseEntity<>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }

}
