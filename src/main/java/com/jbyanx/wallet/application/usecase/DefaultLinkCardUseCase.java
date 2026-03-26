package com.jbyanx.wallet.application.usecase;

import com.jbyanx.wallet.application.port.in.LinkCardUseCase;
import com.jbyanx.wallet.application.port.out.SaveCardPort;
import com.jbyanx.wallet.domain.exception.CardExpiredException;
import com.jbyanx.wallet.domain.model.Card;

import java.time.YearMonth;

public class DefaultLinkCardUseCase implements LinkCardUseCase {
    private final SaveCardPort saveCardPort;

    public DefaultLinkCardUseCase(SaveCardPort saveCardPort) {
        this.saveCardPort = saveCardPort;
    }

    @Override
    public void link(Card card, YearMonth currentDate) {
        if(card.isExpired(currentDate))
            throw new CardExpiredException("Error al agregar la tarjeta, tarjeta expirada");
        this.saveCardPort.save(card);
    }
}
