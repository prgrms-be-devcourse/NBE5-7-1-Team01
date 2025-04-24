package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.domain.OrdersItems;
import io.binary.coffeenotfound_404.domain.Orders;
import io.binary.coffeenotfound_404.dto.CreateOrdersRequestDTO;
import io.binary.coffeenotfound_404.exceptions.OrdersException;
import io.binary.coffeenotfound_404.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

    // 주문 생성 기능
    //주문 객체 생성
    public Orders save(CreateOrdersRequestDTO dto) {
        // 1. 이메일 검사
        if (!isValidEmail(dto.getEmail())) {
            throw new OrdersException.InvalidEmailException("이메일 형식이 올바르지 않습니다.");
        }

        // 2. 주소 검사
        if (!isValidAddress(dto.getAddress())) {
            throw new OrdersException.InvalidAddressException("주소는 필수 입력 값입니다.");
        }

        // 3. 우편번호 검사
        if (!isValidPostcode(dto.getPostcode())) {
            throw new OrdersException.InvalidPostcodeException("우편번호는 필수 입력 값입니다.");
        }

        // 4. 주문 아이템 검사
        if (!isValidOrderItems(dto.getOrdersItems())) {
            throw new OrdersException.InvalidOrderItemsException("주문 아이템이 1개 이상이어야 합니다.");
        }


        // 유효성 검사가 모두 통과되면 주문 생성
        Orders orders = Orders.builder()
                .email(dto.getEmail())
                .address(dto.getAddress())
                .postcode(dto.getPostcode())
                .ordersItems(dto.getOrdersItems())
                .build();

        // 각 주문 아이템에 주문 객체 연결 (setOrderReference는 ordersItems에서 생성)
        for (OrdersItems item : dto.getOrdersItems()) {
            item.setOrderReference(orders);
        }

        // DB 저장
        return ordersRepository.save(orders);
    }

    // 이메일 유효성 검사
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(emailRegex);
    }

    // 주소 유효성 검사
    private boolean isValidAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }

    // 우편번호 유효성 검사
    private boolean isValidPostcode(String postcode) {
        return postcode != null && !postcode.trim().isEmpty();
    }

    // 주문 아이템 유효성 검사
    private boolean isValidOrderItems(List<OrdersItems> ordersItems) {
        return ordersItems != null && !ordersItems.isEmpty();
    }
}


// 주문 생성
//1. 이메일, 주소, 우편번호, 아이템들 입력
//2. 결제하기 버튼 클릭
//3. 이메일형식인지, 주소 및 우편번호가 유효한지, 아이템이 1개 이상 담겼는지 체크. (만약 하나라도 어긋나면 오류메세지)
//4. 모두 올바르면 1개의 주문 생성됨