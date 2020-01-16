package com.baeldung.performancetests.spring;

import com.baeldung.performancetests.Converter;
import com.baeldung.performancetests.model.destination.AccountStatus;
import com.baeldung.performancetests.model.destination.Address;
import com.baeldung.performancetests.model.destination.DeliveryData;
import com.baeldung.performancetests.model.destination.DestinationCode;
import com.baeldung.performancetests.model.destination.Discount;
import com.baeldung.performancetests.model.destination.Order;
import com.baeldung.performancetests.model.destination.OrderStatus;
import com.baeldung.performancetests.model.destination.PaymentType;
import com.baeldung.performancetests.model.destination.Review;
import com.baeldung.performancetests.model.destination.Shop;
import com.baeldung.performancetests.model.destination.User;
import com.baeldung.performancetests.model.source.SourceCode;
import com.baeldung.performancetests.model.source.SourceOrder;

import java.util.List;

/**
 * @author ChenJunChang
 * @date 2020/1/15 6:56 下午
 */
public class BeanUtilsConverter implements Converter {
    @Override
    public Order convert(SourceOrder sourceOrder) {
        Order order = SpringBeanUtils.copy(sourceOrder, Order.class);

        if (sourceOrder.getStatus() != null) {
            order.setOrderStatus(OrderStatus.valueOf(sourceOrder.getStatus().name()));
        }

        if (sourceOrder.getOrderingUser() != null) {
            User user = SpringBeanUtils.copy(sourceOrder.getOrderingUser(), User.class);
            user.setUserAccountStatus(AccountStatus.valueOf(sourceOrder.getOrderingUser().getUserAccountStatus().name()));
            order.setOrderingUser(user);
        }

        if (sourceOrder.getPaymentType() != null) {
            order.setPaymentType(PaymentType.valueOf(sourceOrder.getPaymentType().name()));
        }

        if (sourceOrder.getDiscount() != null) {
            order.setDiscount(SpringBeanUtils.copy(sourceOrder.getDiscount(), Discount.class));
        }

        if (sourceOrder.getDeliveryData() != null) {
            DeliveryData deliveryData = SpringBeanUtils.copy(sourceOrder.getDeliveryData(), DeliveryData.class);
            if (sourceOrder.getDeliveryData().getDeliveryAddress() != null) {
                Address address = SpringBeanUtils.copy(sourceOrder.getDeliveryData().getDeliveryAddress(), Address.class);
                deliveryData.setDeliveryAddress(address);
            }
            order.setDeliveryData(deliveryData);
        }
        if (sourceOrder.getOfferingShop() != null) {
            Shop shop = SpringBeanUtils.copy(sourceOrder.getOfferingShop(), Shop.class);

            if (sourceOrder.getOfferingShop().getShopAddres() != null) {
                Address address = SpringBeanUtils.copy(sourceOrder.getOfferingShop().getShopAddres(), Address.class);
                shop.setShopAddres(address);
            }

            List<Review> reviews = SpringBeanUtils.copyList(sourceOrder.getOfferingShop().getReviews(), Review.class);
            shop.setReviews(reviews);

            order.setOfferingShop(shop);
        }
        return order;
    }

    @Override
    public DestinationCode convert(SourceCode sourceCode) {
        return SpringBeanUtils.copy(sourceCode, DestinationCode.class);
    }
}
