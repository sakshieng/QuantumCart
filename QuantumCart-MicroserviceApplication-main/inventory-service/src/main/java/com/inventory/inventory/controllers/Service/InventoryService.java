package com.inventory.inventory.controllers.Service;

import com.inventory.inventory.dto.InventoryResponse;
import com.inventory.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
       return inventoryRepository.findByskucodeIn(skuCode).stream().map(inventory -> InventoryResponse.builder().skucode(inventory.getSkucode()).isInStock(inventory.getQuantity()>0).build()).toList();
    }
}
