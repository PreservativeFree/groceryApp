package com.Devmountain.groceryApp.controller;


import com.Devmountain.groceryApp.common.ApiResponse;
import com.Devmountain.groceryApp.dto.ProduceDto;
import com.Devmountain.groceryApp.model.Category;
import com.Devmountain.groceryApp.repository.CategoryRepo;
import com.Devmountain.groceryApp.service.ProduceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produce")
public class ProduceController {
    @Autowired
    ProduceServiceImpl produceServiceImpl;

    @Autowired
    CategoryRepo categoryRepo;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduce(@RequestBody ProduceDto produceDto) {
        Optional<Category> optionalCategory = categoryRepo.findById(produceDto.getCategoryId());
        if(!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
        }
        produceServiceImpl.createProduce(produceDto);
        return new ResponseEntity<>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProduceDto>> getProduce() {
        List<ProduceDto> produce = produceServiceImpl.getAllProduce();
        return new ResponseEntity<>(produce, HttpStatus.OK);
    }
//    @GetMapping("/{produceId}")
//    public ResponseEntity<ProduceDto> getProduceById() {
//        ProduceDto produceDto = produceServiceImpl.getAllProduce();
//        return new ResponseEntity<>(produceDto, HttpStatus.OK);
//   }

    @PostMapping("/update/{produceId}")
    public ResponseEntity<ApiResponse> updateProduce(@PathVariable("produceId") Integer produceId, @RequestBody ProduceDto produceDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepo.findById(produceDto.getCategoryId());
        if(!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
        }
        produceServiceImpl.updateProduct(produceId, produceDto);
        return new ResponseEntity<>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }

}
