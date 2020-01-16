package com.baeldung.performancetests.model.destination;

import com.googlecode.jmapper.annotations.JGlobalMap;
import com.googlecode.jmapper.annotations.JMapConversion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JGlobalMap
public class User {
    private String username;
    private String email;
    private AccountStatus userAccountStatus;

    @JMapConversion(from = "userAccountStatus", to = "userAccountStatus")
    public AccountStatus conversion(com.baeldung.performancetests.model.source.AccountStatus status) {
        AccountStatus accountStatus = null;
        switch (status) {
            case ACTIVE:
                accountStatus = AccountStatus.ACTIVE;
                break;
            case NOT_ACTIVE:
                accountStatus = AccountStatus.NOT_ACTIVE;
                break;

            case BANNED:
                accountStatus = AccountStatus.BANNED;
                break;
        }
        return accountStatus;
    }
}
