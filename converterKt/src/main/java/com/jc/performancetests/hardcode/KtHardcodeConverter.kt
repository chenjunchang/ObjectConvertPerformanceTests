package com.jc.performancetests.hardcode

import com.jc.performancetests.Converter
import com.jc.performancetests.model.destination.*
import com.jc.performancetests.model.source.AccountStatus
import com.jc.performancetests.model.source.OrderStatus
import com.jc.performancetests.model.source.SourceCode
import com.jc.performancetests.model.source.SourceOrder
import java.util.*

/**
 * @author ChenJunChang
 */
class KtHardcodeConverter : Converter {
    override fun convert(sourceOrder: SourceOrder?): Order? {
        return if (sourceOrder == null) {
            null
        } else {
            val order = Order()
            order.orderStatus = orderStatusToOrderStatus(sourceOrder.status)
            order.orderingUser = userToUser(sourceOrder.orderingUser)
            order.orderedProducts = productListToProductList(sourceOrder.orderedProducts)
            order.orderDate = sourceOrder.orderDate
            order.orderFinishDate = sourceOrder.orderFinishDate
            order.paymentType = paymentTypeToPaymentType(sourceOrder.paymentType)
            order.discount = discountToDiscount(sourceOrder.discount)
            order.orderId = sourceOrder.orderId
            order.deliveryData = deliveryDataToDeliveryData(sourceOrder.deliveryData)
            order.offeringShop = shopToShop(sourceOrder.offeringShop)
            order
        }
    }

    override fun convert(sourceCode: SourceCode?): DestinationCode? {
        return if (sourceCode == null) {
            null
        } else {
            val destinationCode = DestinationCode()
            destinationCode.code = sourceCode.code
            destinationCode
        }
    }

    companion object {
        private fun orderStatusToOrderStatus(orderStatus: OrderStatus?): com.jc.performancetests.model.destination.OrderStatus? {
            return if (orderStatus == null) {
                null
            } else {
                when (orderStatus) {
                    OrderStatus.CREATED -> com.jc.performancetests.model.destination.OrderStatus.CREATED
                    OrderStatus.CONFIRMED -> com.jc.performancetests.model.destination.OrderStatus.CONFIRMED
                    OrderStatus.COLLECTING -> com.jc.performancetests.model.destination.OrderStatus.COLLECTING
                    OrderStatus.IN_TRANSPORT -> com.jc.performancetests.model.destination.OrderStatus.IN_TRANSPORT
                    OrderStatus.FINISHED -> com.jc.performancetests.model.destination.OrderStatus.FINISHED
                }
            }
        }

        private fun accountStatusToAccountStatus(accountStatus: AccountStatus?): com.jc.performancetests.model.destination.AccountStatus? {
            return if (accountStatus == null) {
                null
            } else {
                when (accountStatus) {
                    AccountStatus.ACTIVE -> com.jc.performancetests.model.destination.AccountStatus.ACTIVE
                    AccountStatus.NOT_ACTIVE -> com.jc.performancetests.model.destination.AccountStatus.NOT_ACTIVE
                    AccountStatus.BANNED -> com.jc.performancetests.model.destination.AccountStatus.BANNED
                }
            }
        }

        private fun userToUser(user: com.jc.performancetests.model.source.User?): User? {
            return if (user == null) {
                null
            } else {
                val user1 = User()
                user1.username = user.username
                user1.email = user.email
                user1.userAccountStatus = accountStatusToAccountStatus(user.userAccountStatus)
                user1
            }
        }

        private fun refundPolicyToRefundPolicy(refundPolicy: com.jc.performancetests.model.source.RefundPolicy?): RefundPolicy? {
            return if (refundPolicy == null) {
                null
            } else {
                val refundPolicy1 = RefundPolicy()
                refundPolicy1.isRefundable = refundPolicy.isRefundable
                refundPolicy1.refundTimeInDays = refundPolicy.refundTimeInDays
                refundPolicy1.notes = refundPolicy.notes
                refundPolicy1
            }
        }

        private fun productToProduct(product: com.jc.performancetests.model.source.Product?): Product? {
            return if (product == null) {
                null
            } else {
                val product1 = Product()
                product1.description = product.description
                product1.isAvailable = product.isAvailable
                product1.price = product.price
                product1.quantity = product.quantity
                product1.name = product.name
                product1.refundPolicy = refundPolicyToRefundPolicy(product.refundPolicy)
                product1
            }
        }

        private fun productListToProductList(list: List<com.jc.performancetests.model.source.Product>?): List<Product?>? {
            return if (list == null) {
                null
            } else {
                val list1: MutableList<Product?> = ArrayList(list.size)
                for (product in list) {
                    list1.add(productToProduct(product))
                }
                list1
            }
        }

        private fun paymentTypeToPaymentType(paymentType: com.jc.performancetests.model.source.PaymentType?): PaymentType? {
            return if (paymentType == null) {
                null
            } else {
                when (paymentType) {
                    com.jc.performancetests.model.source.PaymentType.CASH -> PaymentType.CASH
                    com.jc.performancetests.model.source.PaymentType.CARD -> PaymentType.CARD
                    com.jc.performancetests.model.source.PaymentType.TRANSFER -> PaymentType.TRANSFER
                }
            }
        }

        private fun discountToDiscount(discount: com.jc.performancetests.model.source.Discount?): Discount? {
            return if (discount == null) {
                null
            } else {
                val discount1 = Discount()
                discount1.startTime = discount.startTime
                discount1.endTime = discount.endTime
                discount1.discountPrice = discount.discountPrice
                discount1
            }
        }

        private fun addressToAddress(address: com.jc.performancetests.model.source.Address?): Address? {
            return if (address == null) {
                null
            } else {
                val address1 = Address()
                address1.street = address.street
                address1.city = address.city
                address1.postalCode = address.postalCode
                address1.country = address.country
                address1
            }
        }

        private fun deliveryDataToDeliveryData(deliveryData: com.jc.performancetests.model.source.DeliveryData?): DeliveryData? {
            return if (deliveryData == null) {
                null
            } else {
                val deliveryData1 = DeliveryData()
                deliveryData1.deliveryAddress = addressToAddress(deliveryData.deliveryAddress)
                deliveryData1.isPrePaid = deliveryData.isPrePaid
                deliveryData1.trackingCode = deliveryData.trackingCode
                deliveryData1.expectedDeliveryTimeInDays = deliveryData.expectedDeliveryTimeInDays
                deliveryData1
            }
        }

        private fun reviewToReview(review: com.jc.performancetests.model.source.Review?): Review? {
            return if (review == null) {
                null
            } else {
                val review1 = Review()
                review1.shippingGrade = review.shippingGrade
                review1.pricingGrade = review.pricingGrade
                review1.serviceGrade = review.serviceGrade
                review1.reviewingUser = review.reviewingUser
                review1.note = review.note
                review1
            }
        }

        private fun reviewListToReviewList(list: List<com.jc.performancetests.model.source.Review>?): List<Review?>? {
            return if (list == null) {
                null
            } else {
                val list1: MutableList<Review?> = ArrayList(list.size)
                for (review in list) {
                    list1.add(reviewToReview(review))
                }
                list1
            }
        }

        private fun shopToShop(shop: com.jc.performancetests.model.source.Shop?): Shop? {
            return if (shop == null) {
                null
            } else {
                val shop1 = Shop()
                shop1.shopName = shop.shopName
                shop1.shopAddres = addressToAddress(shop.shopAddres)
                shop1.shopUrl = shop.shopUrl
                shop1.reviews = reviewListToReviewList(shop.reviews)
                shop1
            }
        }
    }
}