package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IsAcceptableTest {

    @Test
    @DisplayName("isAcceptable bernilai true untuk berat yang valid")
    void trueWhenValid() {
        assertTrue(OrderCalculator.isAcceptable(new TestSupport.TestOrder("L1", 5)), "berat 5 valid");
        assertTrue(OrderCalculator.isAcceptable(new TestSupport.TestOrder("L1", 20)), "berat 20 masih valid");
    }

    @Test
    @DisplayName("isAcceptable bernilai false (bukan melempar) untuk berat tidak valid")
    void falseWhenInvalid() {
        assertFalse(OrderCalculator.isAcceptable(new TestSupport.TestOrder("L1", 0)),
                "Tangkap InvalidWeightException dengan catch lalu kembalikan false");
        assertFalse(OrderCalculator.isAcceptable(new TestSupport.TestOrder("L1", 20 + 0.5)),
                "berat di atas 20 kg harus menghasilkan false");
    }

    @Test
    @DisplayName("isAcceptable tidak pernah melempar exception")
    void neverThrows() {
        assertDoesNotThrow(() -> OrderCalculator.isAcceptable(new TestSupport.TestOrder("L1", -1)),
                "Exception harus ditangkap di dalam isAcceptable, bukan diteruskan");
    }
}
