package com.Devmountain.groceryApp.repository;

import com.Devmountain.groceryApp.model.GroceryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryProductRepository extends JpaRepository<GroceryProduct, Integer> {
}
