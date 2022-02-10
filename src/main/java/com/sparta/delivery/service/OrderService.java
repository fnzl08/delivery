package com.sparta.delivery.service;

import com.sparta.delivery.domain.Food;
import com.sparta.delivery.domain.OrderDetail;
import com.sparta.delivery.domain.Orders;
import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.FoodsResponseDto;
import com.sparta.delivery.dto.OrdersRequestDto;
import com.sparta.delivery.dto.OrdersResponseDto;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.OrderDetailRepository;
import com.sparta.delivery.repository.OrderRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    //레포지 주입
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;


    //주문넣기. 포스트 - 음식이름, 양으로 계산한 가격
    //orderrequest에 식당 id랑 foods 받아와서 response로 내야지. post지만 api명세서 보면 리스트로 반환중
    @Transactional
    public OrdersResponseDto orderFood(OrdersRequestDto ordersRequestDto) {

        Restaurant restaurant = restaurantRepository.findById(ordersRequestDto.getRestaurantId())
                .orElseThrow(() -> new NullPointerException("등록되지 않은 음식점입니다."));

        //자 이제 예외검사하자. : 음식수량, 최소가격 인데 그럴려면 orderDetail 불러와야한다.
        //orderdetails의 food 안에 수량있다.
        List<OrderDetail> orderDetails = ordersRequestDto.getFoods();
        //주문상세 저장해줄 리스트
        List<OrderDetail> orderDetailList = new ArrayList<>();

        List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();
        //총가격
        int totalPrice = 0;


        for (OrderDetail orderDetailFor : orderDetails) {

            int quantity = orderDetailFor.getQuantity();
            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("주문 수량은 1이상 100이하로 설정해주세요.");
            }
            //주문수량 맞으면 response 저장해야지
            //근데 food 불러와야 밑에서 food 이름 찾아 저장할 수 있음... 위에서 검사한 친구의 id로 가져와서 food로 저장

            //food 불러올 때 그냥 Food food로 했더니 오류나서 optional로 했더니 optional 하면 밑에 getname안된다.
//                Optional<Food> food = foodRepository.findById(orderDetail.getId());
            //null 들어오면 안되니까 예외처리는 항상 불러올 때 해주기
//
//                OrderDetail orderDetail = OrderDetail.builder()
//                        .name(food.getName()) /단

            Food food = foodRepository.findById(orderDetailFor.getId())
                    .orElseThrow(() -> new NullPointerException("등록되지 않은 음식입니다."));


            OrderDetail orderDetail = OrderDetail.builder()
                    .name(food.getName())
                    .quantity(orderDetailFor.getQuantity())
                    .price(food.getPrice() * quantity)
                    .food(food)
                    .build();
            orderDetailRepository.save(orderDetail);
            //주문에서 foods 받고 있기 때문에 foodresponsedto에 orderdetail넣어줘야한다.
            //orders에 foodresponse들어가고 여기서 detail받아오기때문에
            FoodsResponseDto foodsResponseDto = new FoodsResponseDto(orderDetail);
            //주문 상세 음식 여러개니까 리스트. 이거 위로 올려줬음.
//                List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();
            //리스트에 각 주문상세추가
            foodsResponseDtoList.add(foodsResponseDto);
            totalPrice += food.getPrice() * quantity;
            orderDetailList.add(orderDetail);
        }
        //이까지 orderDetail 포문 돌려 저장한 것
        //자 이제 총 주문 에러 띄우기
//            totalPrice +=food.getPrice() * quantity; //오...이거 위에 포문 바깥에 두니까 food를 못찾네. 여긴 선언 안되어있어서

        //레스토랑 안가져왔더니 에러뜨네 레스토랑 가져오자 -> 위로 올려줌
    // ㅇ;Restaurant restaurant = restaurantRepository.findById(ordersRequestDto.getRestaurantId())
    // .orElseThrow(() -> new NullPointerException("등록되지 않은 음식점입니다."));

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소 주문 가격 이상으로 주문해주세요.");
        }

        //이제 배달비 더해주기
        int deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;

        //저장해주기
        //orders 저장할때 entity랑 순서맞추는거 중요
        Orders orders = new Orders(restaurant.getName(), orderDetailList, totalPrice);
        orderRepository.save(orders);

        //responsedto 반환
        OrdersResponseDto ordersResponseDto = new OrdersResponseDto(foodsResponseDtoList, orders, deliveryFee);
        return ordersResponseDto;
    }

    //주문조회 -테스트코드보면 주문조회에는 orders(식당 이름, detail의 foods(음식이름, 수량, 가격), fee, totalprice)
    @Transactional //우리 파인드올 할거임.
    public List<OrdersResponseDto> findOrder() {

        //orderresponse에다가 넣을거임.
        List<OrdersResponseDto> ordersResponseDtoList = new ArrayList<>();

        //orders에 다 넣어놨으니까 레포지에서 리스트 받아오자. 여기에 배달비만 더해주면 반환완성
        List<Orders> ordersList = orderRepository.findAll();

        //1. 배달료는 Orders에 있는 레스토랑 이름으로 레스토랑레포지에서 배달료 찾아서 선언
        for (Orders orders : ordersList) {
            int deliveryFee = restaurantRepository.findByName(orders.getRestaurantName()).getDeliveryFee();

            //그리고 foods를 받아와야하는데 해당 foods는 orders안의 orderdetail에
            //그리고 orderdetail에 foodresponsedto들어가니까 갖고오기
            List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();
            //2. 해당 주문 상세 가져오기. 레포지에서, 찾는 함수 ()에 해당 orders 넣어서
            List<OrderDetail> orderDetailList = orderDetailRepository.findOrderDetailsByOrders(orders);
            //주문상세마다 있는 food 빼서 response 그릇에 잠시 담아두기(음식이름, 양, 가격)
            for (OrderDetail orderDetail : orderDetailList) {
                FoodsResponseDto foodsResponseDto = new FoodsResponseDto(orderDetail);
                foodsResponseDtoList.add(foodsResponseDto); //포문으로 뽑아낸 foods를 차례차례 담아두기
            }

            //자 이제 각 그릇에 담아뒀던거 합쳐서 하나하나 리스트에 추가하자. dto 지정해준 차례에 맞게
            OrdersResponseDto ordersResponseDto = new OrdersResponseDto(foodsResponseDtoList, orders, deliveryFee);
            ordersResponseDtoList.add(ordersResponseDto);

        }

        //반환
        return ordersResponseDtoList;

    }
}