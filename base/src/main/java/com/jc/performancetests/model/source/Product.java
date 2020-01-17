package com.jc.performancetests.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    String description;
    boolean available;
    private BigDecimal price;
    private int quantity;
    private String name;
    private RefundPolicy refundPolicy;

}
