package com.orderservice.orderservice.service;

import com.orderservice.orderservice.dto.InventoryResponse;
import com.orderservice.orderservice.dto.OrderLineItemsDTO;
import com.orderservice.orderservice.dto.OrderRequest;
import com.orderservice.orderservice.models.Order;

import com.orderservice.orderservice.models.OrderLineItems;
import com.orderservice.orderservice.repository.OrderRepositorty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepositorty orderRepositorty;
    private final WebClient.Builder webClientBuilder;
    public void placeOrder(OrderRequest orderRequest){
        Order order= new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList=orderRequest.getOrderLineItemsDTOS().stream().map(this::mapToDto).toList();
        order.setOrderLineItemsList(orderLineItemsList);
        List<String> skucodes=order.getOrderLineItemsList().stream().map(OrderLineItems::getSkucode).toList();
        //Call Inventory and check if item is in inventory then only order
        InventoryResponse[] inventoryResponsesArray=webClientBuilder.build().get().uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skucode", skucodes).build()).retrieve().bodyToMono(InventoryResponse[].class).block();
        boolean allProductsinStock= Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::getIsInStock);
        if(allProductsinStock){
            orderRepositorty.save(order);
        }
        else {
            throw new IllegalArgumentException("Product is not in stock");
        }


    }

    private OrderLineItems mapToDto(OrderLineItemsDTO orderLineItem) {
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setPrice(orderLineItem.getPrice());
        orderLineItems.setQuantity(orderLineItems.getQuantity());
        orderLineItems.setSkucode(orderLineItem.getSkucode());
        return orderLineItems;
    }


}
