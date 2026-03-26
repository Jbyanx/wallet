package com.jbyanx.wallet.domain.model;

import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    // Tarjeta inmutable compartida para los tests
    Card testCard = new Card(
            UUID.randomUUID(),
            "1234567890123456",
            "Jabes Doe",
            YearMonth.of(2030, 11)
            );

    @Test
    void shouldReturnFalseWhenCardIsNotExpired() {
        // Arrange (Preparar): Usamos una fecha anterior a la expiración
        YearMonth currentDate = YearMonth.of(2026, 3);

        // Act (Actuar)
        boolean isExpired = testCard.isExpired(currentDate);

        // Assert (Afirmar)
        assertFalse(isExpired, "La tarjeta no debería estar expirada en 2026");
    }

    @Test
    void shouldReturnTrueWhenCardIsExpired(){
        //Arrange fecha despues de la expiracion
        YearMonth date = YearMonth.of(2040, 12);

        //Act
        boolean isExpired = testCard.isExpired(date);

        //Assert
        assertTrue(isExpired, "La tarjeta debe estar expirada en 2040");
    }
}