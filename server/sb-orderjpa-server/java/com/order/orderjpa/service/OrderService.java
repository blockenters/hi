package com.order.orderjpa.service;

import com.order.orderjpa.dto.OrderItemResponse;
import com.order.orderjpa.dto.OrderRequest;
import com.order.orderjpa.dto.OrderResponse;
import com.order.orderjpa.entity.Order;
import com.order.orderjpa.exception.OrderNotFoundException;
import com.order.orderjpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void createOrder(OrderRequest orderRequest){

        // DTO 를 Entity 로 바꾼후에 레파지토리 이용하는것!
        Order order = new Order();
        order.productName = orderRequest.productName;
        order.quantity = orderRequest.quantity;
        order.totalPrice = orderRequest.totalPrice;

        orderRepository.save(order);

    }

    public OrderResponse getOrder(long id) {

        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            OrderResponse orderResponse = new OrderResponse(order.get());
            return orderResponse;
        } else {
            throw new RuntimeException();
        }
    }

     public void updateOrder(long id, OrderRequest orderRequest){

        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            Order savedOrder = order.get();
            savedOrder.productName = orderRequest.productName;
            savedOrder.quantity = orderRequest.quantity;
            savedOrder.totalPrice = orderRequest.totalPrice;
            orderRepository.save(savedOrder);
        }else{
            throw new RuntimeException();
        }
    }

    public void deleteOrder(long id){
        if( orderRepository.existsById(id) ){
            orderRepository.deleteById(id);
        }else{
            throw new RuntimeException();
        }
    }

    public PageImpl<OrderItemResponse>  getAllOrders(Pageable pageable){

        // 데이터를 JSON 으로 보내기위해, Entity 를 DTO 로 만들기.
        Page<Order> orderPage  = orderRepository.findAll(pageable);

        ArrayList<OrderItemResponse> orderList = new ArrayList<>();

        for( Order order  :  orderPage){
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.id = order.id;
            orderItemResponse.productName = order.productName;
            orderItemResponse.quantity = order.quantity;
            orderItemResponse.totalPrice = order.totalPrice;
            orderItemResponse.orderDate = order.orderDate.toString();

            orderList.add(orderItemResponse);
        }
        return new PageImpl<>(orderList, pageable, orderPage.getTotalElements());
    }

}





