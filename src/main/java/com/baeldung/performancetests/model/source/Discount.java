package com.baeldung.performancetests.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    private String startTime;
    private String endTime;
    private BigDecimal discountPrice;

}
