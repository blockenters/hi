package com.block.orderjpa2.controller;

import com.block.orderjpa2.dto.OrderListResponse;
import com.block.orderjpa2.dto.OrderRequest;
import com.block.orderjpa2.dto.OrderResponse;
import com.block.orderjpa2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable long id){
        try {
            OrderResponse orderResponse = orderService.getOrder(id);
            return ResponseEntity.status(200).body(orderResponse);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable long id, @RequestBody OrderRequest orderRequest){

        try {
            orderService.updateOrder(id, orderRequest);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }

    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id){
        try {
            orderService.deleteOrder(id);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/orders")
    public ResponseEntity<OrderListResponse> getAllOrders(@RequestParam int page,
                                                   @RequestParam int size,
                                                   @RequestParam String order){

       OrderListResponse orderListResponse =
               orderService.getAllOrders(page, size, order);

       return ResponseEntity.status(200).body(orderListResponse);
    }

}



