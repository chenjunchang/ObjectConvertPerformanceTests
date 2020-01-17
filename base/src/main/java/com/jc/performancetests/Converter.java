package com.jc.performancetests;

import com.jc.performancetests.model.destination.AccountStatus;
import com.jc.performancetests.model.destination.Address;
import com.jc.performancetests.model.destination.DeliveryData;
import com.jc.performancetests.model.destination.DestinationCode;
import com.jc.performancetests.model.destination.Discount;
import com.jc.performancetests.model.destination.Order;
import com.jc.performancetests.model.destination.OrderStatus;
import com.jc.performancetests.model.destination.PaymentType;
import com.jc.performancetests.model.destination.Review;
import com.jc.performancetests.model.destination.Shop;
import com.jc.performancetests.model.destination.User;
import com.jc.performancetests.model.source.SourceCode;
import com.jc.performancetests.model.source.SourceOrder;

import java.util.List;

/**
 * @author ChenJunChang
 */
public interface Converter {
    /**
     * convert {@link SourceOrder} to {@link Order}
     *
     * @param sourceOrder {@link SourceOrder}
     * @return {@link Order}
     */
    Order convert(SourceOrder sourceOrder);

    /**
     * convert {@link SourceCode} to {@link DestinationCode}
     *
     * @param sourceCode {@link SourceCode}
     * @return {@link DestinationCode}
     */
    DestinationCode convert(SourceCode sourceCode);

    /**
     * default copy order method
     *
     * @param copier      {@link Copier}
     * @param sourceOrder {@link SourceOrder}
     * @return {@link Order}
     */
    default Order convert(Copier copier, SourceOrder sourceOrder) {
        Order order = copier.copy(sourceOrder, Order.class);

        if (sourceOrder.getStatus() != null) {
            order.setOrderStatus(OrderStatus.valueOf(sourceOrder.getStatus().name()));
        }

        if (sourceOrder.getOrderingUser() != null) {
            User user = copier.copy(sourceOrder.getOrderingUser(), User.class);
            user.setUserAccountStatus(AccountStatus.valueOf(sourceOrder.getOrderingUser().getUserAccountStatus().name()));
            order.setOrderingUser(user);
        }

        if (sourceOrder.getPaymentType() != null) {
            order.setPaymentType(PaymentType.valueOf(sourceOrder.getPaymentType().name()));
        }

        if (sourceOrder.getDiscount() != null) {
            order.setDiscount(copier.copy(sourceOrder.getDiscount(), Discount.class));
        }

        if (sourceOrder.getDeliveryData() != null) {
            DeliveryData deliveryData = copier.copy(sourceOrder.getDeliveryData(), DeliveryData.class);
            if (sourceOrder.getDeliveryData().getDeliveryAddress() != null) {
                Address address = copier.copy(sourceOrder.getDeliveryData().getDeliveryAddress(), Address.class);
                deliveryData.setDeliveryAddress(address);
            }
            order.setDeliveryData(deliveryData);
        }
        if (sourceOrder.getOfferingShop() != null) {
            Shop shop = copier.copy(sourceOrder.getOfferingShop(), Shop.class);

            if (sourceOrder.getOfferingShop().getShopAddres() != null) {
                Address address = copier.copy(sourceOrder.getOfferingShop().getShopAddres(), Address.class);
                shop.setShopAddres(address);
            }

            List<Review> reviews = copier.copyList(sourceOrder.getOfferingShop().getReviews(), Review.class);
            shop.setReviews(reviews);

            order.setOfferingShop(shop);
        }
        return order;
    }
}
