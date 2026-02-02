package quocph.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    @Mock
    CalculatorRepository repository;

    @InjectMocks
    CalculatorService service;

    @Test
    void testAdd() {
        // GIVEN
        when(repository.getBaseValue()).thenReturn(10);

        // WHEN
        int result = service.add(5);

        // THEN
        assertEquals(15, result);

        verify(repository).getBaseValue();
    }
}
