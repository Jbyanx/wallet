package com.jbyanx.wallet.application.port.in;

import com.jbyanx.wallet.domain.model.Card;

import java.time.YearMonth;

public interface LinkCardUseCase {
    void link(Card card, YearMonth currentDate);
}
