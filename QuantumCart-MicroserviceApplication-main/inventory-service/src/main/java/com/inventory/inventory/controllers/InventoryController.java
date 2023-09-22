package com.inventory.inventory.controllers;

import com.inventory.inventory.controllers.Service.InventoryService;
import com.inventory.inventory.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    // /skucode=jdxfn&skucode=fddjsn (request param)
//    @GetMapping("/{sku-code}")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean isInStock(@PathVariable("sku-code") String skuCode ){
//        return inventoryService.isInStock(skuCode);
//
//    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skucode ){
        return inventoryService.isInStock(skucode);

    }
}
