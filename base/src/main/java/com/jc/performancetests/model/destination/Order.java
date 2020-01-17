package com.jc.performancetests.model.destination;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.googlecode.jmapper.annotations.JMap;
import com.googlecode.jmapper.annotations.JMapConversion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @JMap
    private User orderingUser;
    @JMap
    private List<Product> orderedProducts;
    @JsonProperty(value = "status")
    @JMap("status")
    private OrderStatus orderStatus;
    @JMap
    private String orderDate;
    @JMap
    private String orderFinishDate;
    @JMap
    private PaymentType paymentType;
    @JMap
    private Discount discount;
    @JMap
    private int orderId;
    @JMap
    private DeliveryData deliveryData;
    @JMap
    private Shop offeringShop;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @SuppressWarnings("unused")
    @JMapConversion(from = "status", to = "orderStatus")
    public OrderStatus conversion(com.jc.performancetests.model.source.OrderStatus status) {
        switch (status) {
            case CREATED:
                return OrderStatus.CREATED;
            case FINISHED:
                return OrderStatus.FINISHED;
            case CONFIRMED:
                return OrderStatus.CONFIRMED;
            case COLLECTING:
                return OrderStatus.COLLECTING;
            case IN_TRANSPORT:
                return OrderStatus.IN_TRANSPORT;
            default:
                return null;
        }
    }

    @SuppressWarnings("unused")
    @JMapConversion(from = "paymentType", to = "paymentType")
    public PaymentType conversion(com.jc.performancetests.model.source.PaymentType type) {
        switch (type) {
            case CARD:
                return PaymentType.CARD;
            case CASH:
                return PaymentType.CASH;
            case TRANSFER:
                return PaymentType.TRANSFER;
            default:
                return null;
        }
    }

}
