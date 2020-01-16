package com.jc.performancetests.model.destination;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.googlecode.jmapper.annotations.JMapAccessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JGlobalMap
public class DeliveryData {
    private Address deliveryAddress;
    @JMapAccessor(get = "isPrePaid", set = "setPrePaid")
    private boolean isPrePaid;
    private String trackingCode;
    private int expectedDeliveryTimeInDays;
}
