package com.jbyanx.wallet.domain.model;

import java.time.YearMonth;
import java.util.UUID;

public record Card(
        UUID id,
        String number,
        String cardHolderName,
        YearMonth expirationDate
) {
    public boolean isExpired(YearMonth currentDate){
        //esta expirada si su fecha de expiracion es anterior a la enviada,
        // es decir, si ya pasó esa fecha de expiracion
        return this.expirationDate.isBefore(currentDate);
    }
}
