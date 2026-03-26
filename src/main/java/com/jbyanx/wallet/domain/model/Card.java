package com.jbyanx.wallet.domain.model;

import java.time.YearMonth;
import java.util.UUID;

public record Card(
        UUID id,
        String number,
        String cardHolderName,
        YearMonth expirationDate
) {

    public boolean isExpired(YearMonth currentDate) {
        // ¿La fecha de expiración es anterior a la fecha actual? -> true (está vencida).
        return this.expirationDate.isBefore(currentDate);
    }
}
