package com.Devmountain.groceryApp.repository;


import com.Devmountain.groceryApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

