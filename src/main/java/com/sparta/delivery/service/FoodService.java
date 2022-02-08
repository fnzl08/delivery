package com.sparta.delivery.service;


import com.sparta.delivery.domain.Food;
import com.sparta.delivery.domain.Restaurant;
import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//서비스 명시. 푸드 서비스는 음식 등록과 메뉴판 조회가 들어갈 예정.
@Service
@RequiredArgsConstructor
public class FoodService {

    //db와 통신에 필요한 레포지 주입
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    //하나라도 에러면 등록 스탑
    //반환할게 없으니 보이드. 등록이니까.
    //컨트롤러의 addrestauranatfood 드디어 실행. 아이디랑 dto 리스트 받아왔다.
    @Transactional
    public void addRestaurantFood(Long restaurantId, List<FoodRequestDto> requestDtoList) {

        //레스토랑 빵틀에 레스토랑 아이디로 레포지에서 찾아서 레스토랑 만들자. 없으면 null로 반환.
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElse(null);

        //만약 널이면 에러.
        if (restaurant == null) {
            throw new IllegalArgumentException("해당 음식점이 없습니다.");
        }

        //널 아니면 푸드로 이 포문 돌리자. 리스트 안에 있는 dto 모두 이거 검사하자. - 리스트니까 포문.
        for (FoodRequestDto requestDto : requestDtoList) {

            //dto에서 프라이스 가져와서
            int price = requestDto.getPrice();

            //100원 미만이면 에러
            if (price < 100) {
                throw new IllegalArgumentException("음식 가격이 100원 미만입니다.");
            }

            //1000000원 초과면 에러

            if (price > 1000000) {
                throw new IllegalArgumentException("음식 가격이 1,000,000원을 초과했습니다.");
            }

            //가격은 100원 단위 아니면 에러
            if (price % 100 != 0) {
                throw new IllegalArgumentException("음식 가격이 100원 단위로 입력해주세요.");
            }

            //이 조건들 충족하면 푸드가 중복 음식인지 확인
            //널이 와도 NPE 안생기게 optional, 푸드레포지에서 해당 레스토랑에 해당하는 음식들 이름 가져와서 찾은거 found로
            Optional<Food> found = foodRepository.findFoodByRestaurantAndName(restaurant, requestDto.getName());

            //지금 등록하려는 음식읭 레스토랑 아이디와 음식이름으로 레포지에서 찾아진게 있으면 이미 디비에 있다는 것이니 중복
            if (found.isPresent()) {
                throw new IllegalArgumentException("이미 있는 음식 이름입니다.");
            }

            //아무것도 해당되는거 없으면 저장하자 이제. food entity에 맞게
            //해당 레스토랑 아이디에 중복 없고 어쩌고 조건맞는 음식이 레포지에 food로 저장됐음.
            Food food = new Food(requestDto, restaurant);
            foodRepository.save(food);

//           public User registerUser(SignupRequestDto requestDto) {
//                String username = requestDto.getUsername();
//                String password = requestDto.getPassword();
//                회원 ID 중복 확인
//                Optional<User> found = userRepository.findByUsername(username);
//                if (found.isPresent()) {
//                throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
//                }

        }
    }


    //메뉴판 조회 서비스

    //컨트롤러에서 호출한 findallrestaurantfoods 서비스 실행. 레스토랑 아이디로 레스토랑레포지에서 찾은 레스토랑 검사 후
    //푸드 레포지에 음식 리스트 그 레스토랑 아이디로 찾아서 다 리턴


    @Transactional
    public List<Food> findAllRestaurantFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(
                        () -> new NullPointerException("해당 레스토랑이 없습니다.")
                );
        return foodRepository.findFoodsByRestaurant(restaurant);
    }
}
