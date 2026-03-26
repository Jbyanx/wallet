package com.jbyanx.wallet.application.usecase;

import com.jbyanx.wallet.application.port.out.SaveCardPort;
import com.jbyanx.wallet.domain.exception.CardExpiredException;
import com.jbyanx.wallet.domain.model.Card;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.YearMonth;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

class DefaultLinkCardUseCaseTest {

    // 1. EL MOCK: Creamos un "mesero falso". Mockito genera una implementación
    // automática en memoria de la interfaz SaveCardPort.
    private final SaveCardPort saveCardPortMock = Mockito.mock(SaveCardPort.class);

    // 2. LA INYECCIÓN: Le pasamos nuestro mock al caso de uso por constructor.
    // (Aún no has creado esta clase en 'main', por lo que aquí se pondrá en rojo. ¡Es normal en TDD!)
    private final DefaultLinkCardUseCase useCase = new DefaultLinkCardUseCase(saveCardPortMock);

    @Test
    void shouldThrowExceptionWhenCardIsExpired() {
        // Arrange: Preparamos una tarjeta vencida en 2020
        Card expiredCard = new Card(UUID.randomUUID(), "1234", "Jabes", YearMonth.of(2020, 1));
        YearMonth currentDate = YearMonth.of(2026, 3); // Fecha actual simulada

        // Act & Assert: Verificamos que al intentar vincularla, explote con nuestra excepción
        assertThrows(CardExpiredException.class, () -> {
            // Nota: He modificado el mét odo link para recibir también la fecha actual y poder testearlo
            useCase.link(expiredCard, currentDate);
        });

        // Assert: Verificamos que el UseCase NUNCA llamó al puerto de guardado
        // (Porque explotó antes, lo cual es correcto)
        verify(saveCardPortMock, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldSaveCardWhenCardIsValid() {
        // Arrange: Preparamos una tarjeta válida para 2030
        Card validCard = new Card(UUID.randomUUID(), "1234", "Jabes", YearMonth.of(2030, 1));
        YearMonth currentDate = YearMonth.of(2026, 3);

        // Act: Intentamos vincularla
        useCase.link(validCard, currentDate);

        // Assert: Le preguntamos a Mockito... "¿El UseCase te pasó esta tarjeta exacta para guardarla?"
        verify(saveCardPortMock, Mockito.times(1)).save(validCard);
    }
}