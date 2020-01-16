package com.jc.performancetests.model.destination;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JGlobalMap
public class Shop {

    private String shopName;
    private Address shopAddres;
    private String shopUrl;
    private List<Review> reviews;
}
