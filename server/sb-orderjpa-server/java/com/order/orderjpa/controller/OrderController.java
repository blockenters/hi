package com.order.orderjpa.controller;

import com.order.orderjpa.dto.ErrorResponse;
import com.order.orderjpa.dto.OrderRequest;
import com.order.orderjpa.dto.OrderResponse;
import com.order.orderjpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<Object> createOrder(@RequestBody OrderRequest orderRequest){

        orderService.createOrder(orderRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id){
       try {
           OrderResponse orderResponse = orderService.getOrder(id);
           return ResponseEntity.status(200).body(orderResponse);
       } catch (Exception e) {
           return ResponseEntity.status(404).build();
       }

    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable long id,
                                       @RequestBody OrderRequest orderRequest){
        try {
            orderService.updateOrder(id, orderRequest);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<ErrorResponse> deleteOrder(@PathVariable long id){
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ErrorResponse("주문을 찾을 수 없습니다. ID: "+id));
        }
    }

}
