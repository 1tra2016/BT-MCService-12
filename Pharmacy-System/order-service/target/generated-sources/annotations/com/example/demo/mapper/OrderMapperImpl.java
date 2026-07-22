package com.example.demo.mapper;

import com.example.demo.dto.order.OrderRequestDTO;
import com.example.demo.dto.order.OrderResponseDTO;
import com.example.demo.entity.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-22T16:07:44+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toEntity(OrderRequestDTO request) {
        if ( request == null ) {
            return null;
        }

        Order order = new Order();

        order.setProductId( request.getProductId() );
        order.setQuantity( request.getQuantity() );
        order.setCustomerEmail( request.getCustomerEmail() );

        return order;
    }

    @Override
    public OrderResponseDTO toResponseDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseDTO.OrderResponseDTOBuilder orderResponseDTO = OrderResponseDTO.builder();

        if ( order.getId() != null ) {
            orderResponseDTO.id( order.getId().longValue() );
        }
        if ( order.getProductId() != null ) {
            orderResponseDTO.productId( order.getProductId().longValue() );
        }
        orderResponseDTO.quantity( order.getQuantity() );
        orderResponseDTO.customerEmail( order.getCustomerEmail() );

        return orderResponseDTO.build();
    }
}
