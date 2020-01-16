package com.jc.performancetests.hardcode;

import com.jc.performancetests.Converter;
import com.jc.performancetests.model.destination.AccountStatus;
import com.jc.performancetests.model.destination.Address;
import com.jc.performancetests.model.destination.DeliveryData;
import com.jc.performancetests.model.destination.DestinationCode;
import com.jc.performancetests.model.destination.Discount;
import com.jc.performancetests.model.destination.Order;
import com.jc.performancetests.model.destination.OrderStatus;
import com.jc.performancetests.model.destination.PaymentType;
import com.jc.performancetests.model.destination.Product;
import com.jc.performancetests.model.destination.RefundPolicy;
import com.jc.performancetests.model.destination.Review;
import com.jc.performancetests.model.destination.Shop;
import com.jc.performancetests.model.destination.User;
import com.jc.performancetests.model.source.SourceCode;
import com.jc.performancetests.model.source.SourceOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenJunChang
 */
public class HardcodeConverter implements Converter {

    private static OrderStatus orderStatusToOrderStatus(com.jc.performancetests.model.source.OrderStatus orderStatus) {
        if (orderStatus == null) {
            return null;
        } else {
            switch (orderStatus) {
                case CREATED:
                    return OrderStatus.CREATED;
                case CONFIRMED:
                    return OrderStatus.CONFIRMED;
                case COLLECTING:
                    return OrderStatus.COLLECTING;
                case IN_TRANSPORT:
                    return OrderStatus.IN_TRANSPORT;
                case FINISHED:
                    return OrderStatus.FINISHED;
                default:
                    return null;
            }
        }
    }

    private static AccountStatus accountStatusToAccountStatus(com.jc.performancetests.model.source.AccountStatus accountStatus) {
        if (accountStatus == null) {
            return null;
        } else {
            switch (accountStatus) {
                case ACTIVE:
                    return AccountStatus.ACTIVE;
                case NOT_ACTIVE:
                    return AccountStatus.NOT_ACTIVE;
                case BANNED:
                    return AccountStatus.BANNED;
                default:
                    return null;
            }
        }
    }

    private static User userToUser(com.jc.performancetests.model.source.User user) {
        if (user == null) {
            return null;
        } else {
            User user1 = new User();
            user1.setUsername(user.getUsername());
            user1.setEmail(user.getEmail());
            user1.setUserAccountStatus(accountStatusToAccountStatus(user.getUserAccountStatus()));
            return user1;
        }
    }

    private static RefundPolicy refundPolicyToRefundPolicy(com.jc.performancetests.model.source.RefundPolicy refundPolicy) {
        if (refundPolicy == null) {
            return null;
        } else {
            RefundPolicy refundPolicy1 = new RefundPolicy();
            refundPolicy1.setRefundable(refundPolicy.isRefundable());
            refundPolicy1.setRefundTimeInDays(refundPolicy.getRefundTimeInDays());
            refundPolicy1.setNotes(refundPolicy.getNotes());
            return refundPolicy1;
        }
    }

    private static Product productToProduct(com.jc.performancetests.model.source.Product product) {
        if (product == null) {
            return null;
        } else {
            Product product1 = new Product();
            product1.setDescription(product.getDescription());
            product1.setAvailable(product.isAvailable());
            product1.setPrice(product.getPrice());
            product1.setQuantity(product.getQuantity());
            product1.setName(product.getName());
            product1.setRefundPolicy(refundPolicyToRefundPolicy(product.getRefundPolicy()));
            return product1;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static List<Product> productListToProductList(List<com.jc.performancetests.model.source.Product> list) {
        if (list == null) {
            return null;
        } else {
            List<Product> list1 = new ArrayList(list.size());

            for (com.jc.performancetests.model.source.Product product : list) {
                list1.add(productToProduct(product));
            }

            return list1;
        }
    }

    private static PaymentType paymentTypeToPaymentType(com.jc.performancetests.model.source.PaymentType paymentType) {
        if (paymentType == null) {
            return null;
        } else {
            switch (paymentType) {
                case CASH:
                    return PaymentType.CASH;
                case CARD:
                    return PaymentType.CARD;
                case TRANSFER:
                    return PaymentType.TRANSFER;
                default:
                    return null;
            }
        }
    }

    private static Discount discountToDiscount(com.jc.performancetests.model.source.Discount discount) {
        if (discount == null) {
            return null;
        } else {
            Discount discount1 = new Discount();
            discount1.setStartTime(discount.getStartTime());
            discount1.setEndTime(discount.getEndTime());
            discount1.setDiscountPrice(discount.getDiscountPrice());
            return discount1;
        }
    }

    private static Address addressToAddress(com.jc.performancetests.model.source.Address address) {
        if (address == null) {
            return null;
        } else {
            Address address1 = new Address();
            address1.setStreet(address.getStreet());
            address1.setCity(address.getCity());
            address1.setPostalCode(address.getPostalCode());
            address1.setCountry(address.getCountry());
            return address1;
        }
    }

    private static DeliveryData deliveryDataToDeliveryData(com.jc.performancetests.model.source.DeliveryData deliveryData) {
        if (deliveryData == null) {
            return null;
        } else {
            DeliveryData deliveryData1 = new DeliveryData();
            deliveryData1.setDeliveryAddress(addressToAddress(deliveryData.getDeliveryAddress()));
            deliveryData1.setPrePaid(deliveryData.isPrePaid());
            deliveryData1.setTrackingCode(deliveryData.getTrackingCode());
            deliveryData1.setExpectedDeliveryTimeInDays(deliveryData.getExpectedDeliveryTimeInDays());
            return deliveryData1;
        }
    }

    private static Review reviewToReview(com.jc.performancetests.model.source.Review review) {
        if (review == null) {
            return null;
        } else {
            Review review1 = new Review();
            review1.setShippingGrade(review.getShippingGrade());
            review1.setPricingGrade(review.getPricingGrade());
            review1.setServiceGrade(review.getServiceGrade());
            review1.setReviewingUser(review.getReviewingUser());
            review1.setNote(review.getNote());
            return review1;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static List<Review> reviewListToReviewList(List<com.jc.performancetests.model.source.Review> list) {
        if (list == null) {
            return null;
        } else {
            List<Review> list1 = new ArrayList(list.size());
            for (com.jc.performancetests.model.source.Review review : list) {
                list1.add(reviewToReview(review));
            }
            return list1;
        }
    }

    private static Shop shopToShop(com.jc.performancetests.model.source.Shop shop) {
        if (shop == null) {
            return null;
        } else {
            Shop shop1 = new Shop();
            shop1.setShopName(shop.getShopName());
            shop1.setShopAddres(addressToAddress(shop.getShopAddres()));
            shop1.setShopUrl(shop.getShopUrl());
            shop1.setReviews(reviewListToReviewList(shop.getReviews()));
            return shop1;
        }
    }

    @Override
    public Order convert(SourceOrder sourceOrder) {
        if (sourceOrder == null) {
            return null;
        } else {
            Order order = new Order();
            order.setOrderStatus(orderStatusToOrderStatus(sourceOrder.getStatus()));
            order.setOrderingUser(userToUser(sourceOrder.getOrderingUser()));
            order.setOrderedProducts(productListToProductList(sourceOrder.getOrderedProducts()));
            order.setOrderDate(sourceOrder.getOrderDate());
            order.setOrderFinishDate(sourceOrder.getOrderFinishDate());
            order.setPaymentType(paymentTypeToPaymentType(sourceOrder.getPaymentType()));
            order.setDiscount(discountToDiscount(sourceOrder.getDiscount()));
            order.setOrderId(sourceOrder.getOrderId());
            order.setDeliveryData(deliveryDataToDeliveryData(sourceOrder.getDeliveryData()));
            order.setOfferingShop(shopToShop(sourceOrder.getOfferingShop()));
            return order;
        }
    }

    @Override
    public DestinationCode convert(SourceCode sourceCode) {
        if (sourceCode == null) {
            return null;
        } else {
            DestinationCode destinationCode = new DestinationCode();
            destinationCode.setCode(sourceCode.getCode());
            return destinationCode;
        }
    }
}
