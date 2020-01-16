package com.baeldung.performancetests.model.source;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    private String shopName;
    private Address shopAddres;
    private String shopUrl;
    private List<Review> reviews;

}
