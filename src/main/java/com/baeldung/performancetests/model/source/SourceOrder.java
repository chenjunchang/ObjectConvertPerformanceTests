package com.baeldung.performancetests.model.source;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceOrder {
    private String orderFinishDate;
    private PaymentType paymentType;
    private Discount discount;
    private DeliveryData deliveryData;
    private User orderingUser;
    private List<Product> orderedProducts;
    private Shop offeringShop;
    private int orderId;
    private OrderStatus status;
    private String orderDate;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
