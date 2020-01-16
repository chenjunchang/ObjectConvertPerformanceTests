package com.baeldung.performancetests.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    int shippingGrade;
    int pricingGrade;
    int serviceGrade;
    User reviewingUser;
    String note;
}
