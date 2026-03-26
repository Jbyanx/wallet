package com.jbyanx.wallet.application.port.out;

import com.jbyanx.wallet.domain.model.Card;

public interface SaveCardPort {
    void save(Card card);
}
