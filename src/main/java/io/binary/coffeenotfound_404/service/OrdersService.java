package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.domain.OrderItems;
import io.binary.coffeenotfound_404.domain.Orders;
import io.binary.coffeenotfound_404.dto.CreateOrdersRequestDto;
import io.binary.coffeenotfound_404.dto.OrderItemsDto;
import io.binary.coffeenotfound_404.exceptions.OrdersException;
import io.binary.coffeenotfound_404.repository.ItemsRepository;
import io.binary.coffeenotfound_404.repository.OrdersRepository;
import io.binary.coffeenotfound_404.validation.OrdersValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// 주문 생성 기능
//1. 이메일, 주소, 우편번호, 아이템들 입력
//2. 결제하기 버튼 클릭
//3. 이메일형식인지, 주소 및 우편번호가 유효한지, 아이템이 1개 이상 담겼는지 체크. (만약 하나라도 어긋나면 오류메세지)
//4. 모두 올바르면 1개의 주문 생성됨
//주문 객체 생성
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ItemsRepository itemsRepository;
    private final
    OrdersValidator ordersValidator;

    // 주문 생성 기능
    // 주문 객체 생성
    public Orders save(CreateOrdersRequestDto dto) {

        // 유효성 검사
        ordersValidator.ordersValidate(dto);

        // 유효성 검사가 모두 통과되면 주문 생성
        // OrderItems 생성을 위해 OrderItems List 빼고 Orders Build
        Orders orders = Orders.builder()
                .email(dto.getEmail())
                .address(dto.getAddress())
                .postcode(dto.getPostcode())
                .build();

        // OrdersForm -> OrderItemsDTO 리스트를 통해 Items의 id와 quantity를 얻음
        // Orders, Items로 OrderItems 생성 -> OrderItems List 생성
        List<OrderItems> orderItemsList = new ArrayList<>();
        List<OrderItemsDto> orderItemsFormList = dto.getOrderItemsList();
        for (OrderItemsDto itemsForm : orderItemsFormList) {

            Items findItems = itemsRepository.findById(itemsForm.getItemsId())
                    .orElseThrow(() -> new OrdersException.ItemsNotFoundException());

            OrderItems orderItems = OrderItems.builder()
                    .orders(orders)
                    .items(findItems)
                    .quantity(itemsForm.getQuantity())
                    .build();

            orderItemsList.add(orderItems);
        }

        // Orders에 OrderItems List 추가
        orders.setOrderItemsList(orderItemsList);

        // OrderItems List까지 있는 완전한 Orders 객체 생성 후 저장
        for (OrderItems orderItems : orderItemsList) {
            orderItems.setOrders(orders);
        }

        log.info("orders.getOrderItemsList() = {}", orders.getOrderItemsList());

        // DB 저장
        return ordersRepository.save(orders);
    }

    public List<Orders> findOrdersByEmail(String email) {
        return ordersRepository.findByEmail(email);
    }

}