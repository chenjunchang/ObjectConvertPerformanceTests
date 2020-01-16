package com.baeldung.performancetests.model.source;

import com.googlecode.jmapper.annotations.JMapAccessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundPolicy {
    @JMapAccessor(get = "isRefundable", set = "setRefundable")
    private boolean isRefundable;
    private int refundTimeInDays;
    private List<String> notes;

}
