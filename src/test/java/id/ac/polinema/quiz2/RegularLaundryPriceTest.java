package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegularLaundryPriceTest {

    @Test
    @DisplayName("RegularLaundry: harga = berat x Rp 6.000 per kg")
    void priceIsMeasureTimesRate() throws Throwable {
        LaundryOrder order = TestSupport.newStudentOrder("RegularLaundry", "L1", 5);
        assertEquals(5 * 6000.0, order.calculatePrice(), 0.001,
                "RegularLaundry: 5 kg harus Rp 6.000 per kg x 5");
    }

    @Test
    @DisplayName("RegularLaundry: berat pecahan dihitung benar")
    void fractionalMeasure() throws Throwable {
        LaundryOrder order = TestSupport.newStudentOrder("RegularLaundry", "L1", 2.5);
        assertEquals(2.5 * 6000.0, order.calculatePrice(), 0.001,
                "RegularLaundry: 2.5 kg harus Rp 6.000 per kg x 2.5 (pakai tipe double)");
    }
}
