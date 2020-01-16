package com.jc.performancetests.model.destination;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.jc.performancetests.model.source.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JGlobalMap
public class Review {

    int shippingGrade;
    int pricingGrade;
    int serviceGrade;
    User reviewingUser;
    String note;
}
