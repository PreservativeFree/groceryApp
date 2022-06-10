package com.Devmountain.groceryApp.service;


import com.Devmountain.groceryApp.dto.GroceryProductDto;
import com.Devmountain.groceryApp.model.Category;
import com.Devmountain.groceryApp.model.GroceryProduct;
import com.Devmountain.groceryApp.repository.GroceryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroceryProductService {
    @Autowired
    GroceryProductRepository groceryProductRepository;

    public void createProduct(GroceryProductDto groceryProductDto, Category category) {
        GroceryProduct groceryProduct = new GroceryProduct();
        groceryProduct.setDescription(groceryProductDto.getDescription());
        groceryProduct.setImageUrl(groceryProductDto.getImageUrl());
        groceryProduct.setName(groceryProductDto.getName());
        groceryProduct.setCategory(category);
        groceryProduct.setPrice(groceryProductDto.getPrice());
        groceryProductRepository.save(groceryProduct);
    }

    public GroceryProductDto getProductDto(GroceryProduct groceryProduct){
        GroceryProductDto groceryProductDto = new GroceryProductDto();
        groceryProductDto.setDescription(groceryProduct.getDescription());
        groceryProductDto.setImageUrl(groceryProduct.getImageUrl());
        groceryProductDto.setName(groceryProduct.getName());
        groceryProductDto.setCategoryId(groceryProduct.getId());
        groceryProductDto.setPrice(groceryProduct.getPrice());
        groceryProductDto.setId(groceryProduct.getId());
        return groceryProductDto;
    }

    public List<GroceryProductDto> getAllProducts() {
        List<GroceryProduct> allProducts = groceryProductRepository.findAll();
        List<GroceryProductDto> groceryProductDtos = new ArrayList<>();
        for(GroceryProduct product: allProducts) {
            groceryProductDtos.add(getProductDto(product));
        }
        return groceryProductDtos;
    }

    public void updateProduct(GroceryProductDto groceryProductDto, Integer groceryProductId) throws Exception {
        Optional<GroceryProduct> optionalProduct = groceryProductRepository.findById(groceryProductId);
        if (!optionalProduct.isPresent()) {
            throw new Exception("product not present");
        }
        GroceryProduct groceryProduct = optionalProduct.get();
        groceryProduct.setDescription(groceryProductDto.getDescription());
        groceryProduct.setImageUrl(groceryProductDto.getImageUrl());
        groceryProduct.setName(groceryProductDto.getName());
        groceryProduct.setPrice(groceryProductDto.getPrice());
        groceryProductRepository.save(groceryProduct);
    }
}