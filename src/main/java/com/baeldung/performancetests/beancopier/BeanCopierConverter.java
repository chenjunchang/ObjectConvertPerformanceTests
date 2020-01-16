package com.baeldung.performancetests.beancopier;

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

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenJunChang
 * @date 2020/1/15 9:53 下午
 */
public class BeanCopierConverter implements Converter {
    @Override
    public Order convert(SourceOrder sourceOrder) {
        Order order = BeanCopierUtils.copyBeanProperties(sourceOrder, new Order());

        if (sourceOrder.getStatus() != null) {
            order.setOrderStatus(OrderStatus.valueOf(sourceOrder.getStatus().name()));
        }

        if (sourceOrder.getOrderingUser() != null) {
            User user = BeanCopierUtils.copyBeanProperties(sourceOrder.getOrderingUser(), new User());
            user.setUserAccountStatus(AccountStatus.valueOf(sourceOrder.getOrderingUser().getUserAccountStatus().name()));
            order.setOrderingUser(user);
        }

        if (sourceOrder.getPaymentType() != null) {
            order.setPaymentType(PaymentType.valueOf(sourceOrder.getPaymentType().name()));
        }

        if (sourceOrder.getDiscount() != null) {
            order.setDiscount(BeanCopierUtils.copyBeanProperties(sourceOrder.getDiscount(), new Discount()));
        }

        if (sourceOrder.getDeliveryData() != null) {
            DeliveryData deliveryData = BeanCopierUtils.copyBeanProperties(sourceOrder.getDeliveryData(), new DeliveryData());
            if (sourceOrder.getDeliveryData().getDeliveryAddress() != null) {
                Address address = BeanCopierUtils.copyBeanProperties(sourceOrder.getDeliveryData().getDeliveryAddress(), new Address());
                deliveryData.setDeliveryAddress(address);
            }
            order.setDeliveryData(deliveryData);
        }
        if (sourceOrder.getOfferingShop() != null) {
            Shop shop = BeanCopierUtils.copyBeanProperties(sourceOrder.getOfferingShop(), new Shop());

            if (sourceOrder.getOfferingShop().getShopAddres() != null) {
                Address address = BeanCopierUtils.copyBeanProperties(sourceOrder.getOfferingShop().getShopAddres(), new Address());
                shop.setShopAddres(address);
            }

            List<Review> reviews = BeanCopierUtils.copyListBeanPropertiesToList(sourceOrder.getOfferingShop().getReviews(), new ArrayList<>(), Review.class);
            shop.setReviews(reviews);

            order.setOfferingShop(shop);
        }
        return order;
    }

    @Override
    public DestinationCode convert(SourceCode sourceCode) {
        return BeanCopierUtils.copyBeanProperties(sourceCode, new DestinationCode());
    }
}
