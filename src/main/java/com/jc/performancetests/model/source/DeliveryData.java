package com.jc.performancetests.model.source;

import com.googlecode.jmapper.annotations.JMapAccessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryData {
    private Address deliveryAddress;
    @JMapAccessor(get = "isPrePaid", set = "setPrePaid")
    private boolean isPrePaid;
    private String trackingCode;
    private int expectedDeliveryTimeInDays;

}
