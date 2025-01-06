package com.order.orderjpa.controller;

import com.order.orderjpa.dto.*;
import com.order.orderjpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

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

    @GetMapping("/orders")
    public ResponseEntity<OrderListResponse> getAllOrders(@RequestParam int page,
                                                   @RequestParam int size,
                                                   @RequestParam String order){
        // page : 시작은 1이지만, 시스템에서는 0부터다!
        // 정렬은 Sort 클래스를 만든다.
        String[] orderArray = order.split(",");
        Sort.Direction direction =
                Sort.Direction.fromString( orderArray[1] );
        Sort sort = Sort.by(direction, orderArray[0]);

        PageRequest pageRequest =
                PageRequest.of(page-1, size, sort);

        Page<OrderItemResponse> orderPage =
                orderService.getAllOrders(pageRequest);

        OrderListResponse orderListResponse =
                new OrderListResponse();
        // 보낼 리스트 셋팅
        orderListResponse.orderList = orderPage.getContent();
        // PageInfo 셋팅
        PageInfo pageInfo = new PageInfo();
        pageInfo.page = page;
        pageInfo.size = size;
        pageInfo.totalElements = orderPage.getTotalElements();
        pageInfo.totalPages = orderPage.getTotalPages();

        orderListResponse.pageInfo = pageInfo;
        return ResponseEntity.status(200).body(orderListResponse);
    }

}
