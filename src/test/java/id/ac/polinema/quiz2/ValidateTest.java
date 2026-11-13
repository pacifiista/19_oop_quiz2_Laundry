package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidateTest {

    @Test
    @DisplayName("validate melempar InvalidWeightException untuk berat 0 atau negatif")
    void throwsForZeroOrNegative() {
        assertThrows(InvalidWeightException.class, () -> OrderCalculator.validate(new TestSupport.TestOrder("L1", 0)),
                "berat 0 tidak valid: lempar InvalidWeightException");
        assertThrows(InvalidWeightException.class, () -> OrderCalculator.validate(new TestSupport.TestOrder("L1", -2)),
                "berat negatif tidak valid: lempar InvalidWeightException");
    }

    @Test
    @DisplayName("validate melempar InvalidWeightException untuk berat di atas 20 kg")
    void throwsAboveLimit() {
        assertThrows(InvalidWeightException.class, () -> OrderCalculator.validate(new TestSupport.TestOrder("L1", 20 + 1)),
                "berat lebih dari 20 kg tidak valid");
    }

    @Test
    @DisplayName("Pesan exception: Invalid weight: 25.0")
    void exceptionMessage() {
        InvalidWeightException e = assertThrows(InvalidWeightException.class,
                () -> OrderCalculator.validate(new TestSupport.TestOrder("L1", 25)));
        assertEquals("Invalid weight: 25.0", e.getMessage(),
                "Pesannya harus \"Invalid weight: \" diikuti nilai berat, contoh: Invalid weight: 25.0");
    }

    @Test
    @DisplayName("validate tidak melempar apa pun untuk berat yang valid")
    void validDoesNotThrow() {
        assertDoesNotThrow(() -> OrderCalculator.validate(new TestSupport.TestOrder("L1", 0.5)),
                "berat 0.5 valid");
        assertDoesNotThrow(() -> OrderCalculator.validate(new TestSupport.TestOrder("L1", 20)),
                "berat tepat 20 kg masih valid");
    }
}
