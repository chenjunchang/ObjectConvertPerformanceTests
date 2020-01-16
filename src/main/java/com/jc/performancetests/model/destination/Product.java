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
public class Product {
    String description;
    boolean available;
    private BigDecimal price;
    private int quantity;
    private String name;
    private RefundPolicy refundPolicy;
}
