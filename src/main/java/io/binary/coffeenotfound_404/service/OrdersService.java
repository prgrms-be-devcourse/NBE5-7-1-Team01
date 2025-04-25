package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.dao.ItemRepository;
import io.binary.coffeenotfound_404.dao.OrdersRepository;
import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.domain.OrderItems;
import io.binary.coffeenotfound_404.domain.Orders;
import io.binary.coffeenotfound_404.dto.*;
import io.binary.coffeenotfound_404.exceptions.OrdersException;
import io.binary.coffeenotfound_404.validation.OrdersValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ItemRepository itemRepository;
    private final OrdersValidator ordersValidator;

    // 주문 생성 기능
    // 1. 이메일, 주소, 우편번호, 아이템들 입력
    // 2. 결제하기 버튼 클릭
    // 3. 이메일형식인지, 주소 및 우편번호가 유효한지, 아이템이 1개 이상 담겼는지 체크. (만약 하나라도 어긋나면 오류메세지)
    // 4. 모두 올바르면 1개의 주문 생성됨
    // 주문 객체 생성
    public Orders save(CreateOrdersRequestDto dto) {

        // 유효성 검사
        ordersValidator.emailValidate(dto.getEmail());
        ordersValidator.addressValidate(dto.getAddress());
        ordersValidator.postCodeValidate(dto.getPostCode());
        ordersValidator.orderItemsListValidate(dto.getOrderItemsList());

        // 유효성 검사가 모두 통과되면 주문 생성
        // OrderItems 생성을 위해 OrderItems List 빼고 Orders Build
        Orders orders = Orders.builder()
                .email(dto.getEmail())
                .address(dto.getAddress())
                .postCode(dto.getPostCode())
                .build();

        // OrdersForm -> OrderItemsDTO 리스트를 통해 Items의 id와 quantity를 얻음
        // Orders, Items로 OrderItems 생성 -> OrderItems List 생성
        List<OrderItems> orderItemsList = new ArrayList<>();
        List<OrderItemsDto> orderItemsFormList = dto.getOrderItemsList();
        for (OrderItemsDto itemsForm : orderItemsFormList) {

            Items findItems = itemRepository.findById(itemsForm.getItemId())
                    .orElseThrow(OrdersException.ItemsNotFoundException::new);

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

    // 주문 조회 기능
    public List<Orders> findOrdersByEmail(String email) {
        return ordersRepository.findByEmail(email);
    }


    // 주문 삭제 기능
    public void removeOrdersByEmail(String email, Long orderId) {
        // email 유효성 검사
        ordersValidator.emailValidate(email);

        for (Orders targetOrder : findOrdersByEmail(email)) {
            if (targetOrder.getId().equals(orderId)) {
                ordersRepository.delete(targetOrder);
                return;
            }
        }

        // 일치하는 id의 주문이 없는 경우 예외 발생
        throw new OrdersException.ItemsNotFoundException();
    }

    // 주문 수정 기능
    public void updateOrder(String email, Long orderId, UpdateOrderRequestDto dto) {

        /* 1) 기본 검증 */
        ordersValidator.emailValidate(email);

        Orders order = ordersRepository.findByEmail(email).stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .orElseThrow(OrdersException.InvalidOrderItemsException::new);

        /* 2) 주소·우편번호 부분 수정 */
        if (dto.getAddress() != null)   order.setAddress(dto.getAddress());
        if (dto.getPostCode() != null)  order.setPostCode(dto.getPostCode());

        /* 3) 주문‑상품 전체 교체  */
        if (dto.getOrderItemsList() != null && !dto.getOrderItemsList().isEmpty()) {

            List<OrderItems> current = order.getOrderItemsList();
            current.clear();

            for (OrderItemsDto line : dto.getOrderItemsList()) {

                Items item = itemRepository.findById(line.getItemId())
                        .orElseThrow(OrdersException.ItemsNotFoundException::new);

                OrderItems oi = OrderItems.builder()
                        .orders(order)
                        .items(item)
                        .quantity(line.getQuantity())
                        .build();

                current.add(oi);
            }
        }
    }

}