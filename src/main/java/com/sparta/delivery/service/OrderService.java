//package com.sparta.delivery.service;
//
//import com.sparta.delivery.domain.Food;
//import com.sparta.delivery.domain.OrderDetail;
//import com.sparta.delivery.domain.Orders;
//import com.sparta.delivery.domain.Restaurant;
//import com.sparta.delivery.dto.FoodsRequestDto;
//import com.sparta.delivery.dto.FoodsResponseDto;
//import com.sparta.delivery.dto.OrdersRequestDto;
//import com.sparta.delivery.dto.OrdersResponseDto;
//import com.sparta.delivery.repository.FoodRepository;
//import com.sparta.delivery.repository.OrderDetailRepository;
//import com.sparta.delivery.repository.OrderRepository;
//import com.sparta.delivery.repository.RestaurantRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//    @Service
//    @RequiredArgsConstructor
//    public class OrderService {
//        private final FoodRepository foodRepository;
//        private final RestaurantRepository restaurantRepository;
//        private final OrderRepository orderRepository;
//        private final OrderDetailRepository orderDetailRepository;
//
//        @Transactional
//        public OrdersResponseDto putOrder(OrdersRequestDto orderRequestDto){
//            Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
//                    .orElseThrow(
//                            ()-> new NullPointerException("해당 음식점이 없습니다.")
//                    );
//            Long totalPrice = 0L;
//            List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();
//            List<OrderDetail> orderDetails = orderRequestDto.getFoods();
//            List<OrderDetail> orderDetailList = new ArrayList<>();
//            for (OrderDetail tempOrderDetail : orderDetails){
//
//                Long quantity = tempOrderDetail.getQuantity();
//                if(quantity < 1 || quantity >100){
//                    throw new IllegalArgumentException("음식 수량은 1 이상 100이하로 설정해주세요.");
//                }
//
//                Food food = foodRepository.findById(tempOrderDetail.getId()
//                        .orElseThrow(
//                                () -> new NullPointerException("해당 음식이 없습니다.")
//                        );
//
//                OrderDetail orderDetail = OrderDetail
//                        .builder()
//                        .quantity(tempOrderDetail.getQuantity())
//                        .name(food.getName())
//                        .price(food.getPrice()*quantity)
//                        .food(food)
//                        .build();
//                orderDetailRepository.save(orderDetail);
//                FoodsResponseDto foodsResponseDto = new FoodsResponseDto(orderDetail);
//                foodsResponseDtoList.add(foodsResponseDto);
//                totalPrice += food.getPrice() * quantity;
//                orderDetailList.add(orderDetail);
//            }
//
//            if(totalPrice < restaurant.getMinOrderPrice()){
//                throw new IllegalArgumentException("최소 주문 가격 이상으로 주문해주세요.");
//            }
//
//            Long deliveryFee = restaurant.getDeliveryFee();
//            totalPrice += deliveryFee;
//            Orders orders = new Orders(restaurant.getName(), totalPrice, orderDetailList);
//            orderRepository.save(orders);
//            OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders, deliveryFee, foodsResponseDtoList);
//            return ordersResponseDto;
//
//            @Transactional
//            public List<OrdersResponseDto> findAllOrder(){
//                List<OrdersRequestDto> ordersResponseDtoList = new ArrayList<>();
//
//            }
//
//
//
//
//
//
//            }
//
//        }
