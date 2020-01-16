package com.baeldung.performancetests.model.destination;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JGlobalMap
public class Address {
    private String street;
    private String city;
    private String postalCode;
    private String country;
}
