package com.block.orderjpa2.service;

import com.block.orderjpa2.dto.*;
import com.block.orderjpa2.entity.Order;
import com.block.orderjpa2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;


    public OrderListResponse getAllOrders(int page, int size, String order){
        // order 변수는 :  "orderDate,desc"
        String[] orderArray = order.split(",");
        // orderArray[0] => "orderDate"
        // orderArray[1] => "desc"
        Sort.Direction direction =
                Sort.Direction.fromString(orderArray[1]);

        Sort sort = Sort.by( direction , orderArray[0] );
        PageRequest pageRequest =
                PageRequest.of(page-1, size, sort );

        Page<Order> orderPage = orderRepository.findAll(pageRequest);
        ArrayList<OrderItemResponse> orderList =
                new ArrayList<>();

        for( Order savedOrder  : orderPage){
            OrderItemResponse orderItemResponse =
                    new OrderItemResponse();
            orderItemResponse.id = savedOrder.id;
            orderItemResponse.productName = savedOrder.productName;
            orderItemResponse.quantity = savedOrder.quantity;
            orderItemResponse.totalPrice = savedOrder.totalPrice;
            orderItemResponse.orderDate = savedOrder.orderDate.toString();

            // 리스트에 하니씩 넣어준다.
            orderList.add(orderItemResponse);
        }

        // 페이징 정보를 담을 DTO가 필요하다.
        PageInfo pageInfo = new PageInfo();
        pageInfo.page = page;
        pageInfo.size = size;
        pageInfo.totalElements = orderPage.getTotalElements();
        pageInfo.totalPages = orderPage.getTotalPages();

        OrderListResponse orderListResponse =
                new OrderListResponse(orderList, pageInfo);

        return orderListResponse;

    }


    public void deleteOrder(long id){
        if( orderRepository.existsById(id) == false){
            throw new RuntimeException();
        }

        orderRepository.deleteById(id);
    }


    public void updateOrder(long id, OrderRequest orderRequest){
        // 1. 디비에서 id 에 해당되는 행을 가져온다.
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent() == false){
            throw new RuntimeException();
        }
        // 2. 클라이언트에서 변경된 데이터로 셋팅한후
        Order savedOrder = order.get();
        savedOrder.productName = orderRequest.productName;
        savedOrder.quantity = orderRequest.quantity;
        savedOrder.totalPrice = orderRequest.totalPrice;

        // 3. DB에 다시 저장한다(업데이트한다).
        orderRepository.save(savedOrder);
    }


    public void createOrder(OrderRequest orderRequest){

        // orderRequest DTO 를 Order Entity 로 만든다.
        Order order = new Order();
        order.productName = orderRequest.productName;
        order.quantity = orderRequest.quantity;
        order.totalPrice = orderRequest.totalPrice;

        orderRepository.save(order);

    }

    public OrderResponse getOrder(long id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            // Entity 를 DTO로 만든다.
            OrderResponse orderResponse = new OrderResponse( order.get().id,
                    order.get().productName, order.get().quantity,
                    order.get().totalPrice, order.get().orderDate.toString() );
            return orderResponse;

        }else{
            throw new RuntimeException();
        }
    }



}
