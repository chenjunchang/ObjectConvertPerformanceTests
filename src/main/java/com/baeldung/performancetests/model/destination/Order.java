package com.baeldung.performancetests.model.destination;

import com.googlecode.jmapper.annotations.JMap;
import com.googlecode.jmapper.annotations.JMapConversion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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

    @JMapConversion(from = "status", to = "orderStatus")
    public OrderStatus conversion(com.baeldung.performancetests.model.source.OrderStatus status) {
        OrderStatus orderStatus = null;
        switch (status) {
            case CREATED:
                orderStatus = OrderStatus.CREATED;
                break;
            case FINISHED:
                orderStatus = OrderStatus.FINISHED;
                break;

            case CONFIRMED:
                orderStatus = OrderStatus.CONFIRMED;
                break;

            case COLLECTING:
                orderStatus = OrderStatus.COLLECTING;
                break;

            case IN_TRANSPORT:
                orderStatus = OrderStatus.IN_TRANSPORT;
                break;
        }
        return orderStatus;
    }

    @JMapConversion(from = "paymentType", to = "paymentType")
    public PaymentType conversion(com.baeldung.performancetests.model.source.PaymentType type) {
        PaymentType paymentType = null;
        switch (type) {
            case CARD:
                paymentType = PaymentType.CARD;
                break;

            case CASH:
                paymentType = PaymentType.CASH;
                break;

            case TRANSFER:
                paymentType = PaymentType.TRANSFER;
                break;
        }
        return paymentType;
    }

}
