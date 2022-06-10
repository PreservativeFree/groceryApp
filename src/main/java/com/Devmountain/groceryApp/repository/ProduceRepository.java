package com.Devmountain.groceryApp.repository;

import com.Devmountain.groceryApp.model.Produce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduceRepository extends JpaRepository<Produce, Integer> {
}