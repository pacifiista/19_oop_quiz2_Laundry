package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExpressLaundryPriceTest {

    @Test
    @DisplayName("ExpressLaundry: harga = berat x Rp 9.000 per kg")
    void priceIsMeasureTimesRate() throws Throwable {
        LaundryOrder order = TestSupport.newStudentOrder("ExpressLaundry", "L1", 5);
        assertEquals(5 * 9000.0, order.calculatePrice(), 0.001,
                "ExpressLaundry: 5 kg harus Rp 9.000 per kg x 5");
    }

    @Test
    @DisplayName("ExpressLaundry: berat pecahan dihitung benar")
    void fractionalMeasure() throws Throwable {
        LaundryOrder order = TestSupport.newStudentOrder("ExpressLaundry", "L1", 1.5);
        assertEquals(1.5 * 9000.0, order.calculatePrice(), 0.001,
                "ExpressLaundry: 1.5 kg harus Rp 9.000 per kg x 1.5 (pakai tipe double)");
    }
}
