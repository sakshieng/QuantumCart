package com.inventory.inventory.repository;

import com.inventory.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
//    Optional<Inventory> findByskucode(String skucode);
    List<Inventory> findByskucodeIn(List<String> skucode);
}
