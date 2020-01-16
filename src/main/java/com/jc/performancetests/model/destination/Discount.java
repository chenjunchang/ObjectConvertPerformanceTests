package com.jc.performancetests.model.destination;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JGlobalMap
public class Discount {
    private String startTime;
    private String endTime;
    private BigDecimal discountPrice;
}
