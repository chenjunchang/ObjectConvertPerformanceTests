package com.baeldung.performancetests.model.destination;

import com.baeldung.performancetests.model.source.User;
import com.googlecode.jmapper.annotations.JGlobalMap;
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
