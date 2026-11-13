package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * PremiumLaundry dibuat di dalam tes, bukan oleh siswa. Jenis baru ini harus
 * langsung bekerja tanpa mengubah LaundryDesk atau OrderCalculator.
 */
class NewSubclassNoChangeTest {

    @Test
    @DisplayName("LaundryDesk menyimpan dan menemukan jenis pesanan laundry yang baru")
    void storesNewKind() {
        LaundryDesk desk = new LaundryDesk(new TestSupport.RecordingPrinter());
        LaundryOrder fresh = new TestSupport.PremiumLaundry("N1", 2);
        desk.add(fresh);
        assertSame(fresh, desk.findById("N1"),
                "Jenis baru harus bisa disimpan dan ditemukan tanpa mengubah LaundryDesk");
    }

    @Test
    @DisplayName("checkout meneruskan harga jenis yang baru ke pencetak struk")
    void checkoutPrintsNewKind() {
        TestSupport.RecordingPrinter printer = new TestSupport.RecordingPrinter();
        LaundryDesk desk = new LaundryDesk(printer);
        desk.add(new TestSupport.PremiumLaundry("N1", 2));
        desk.checkout("N1");
        assertEquals(2 * 5000.0, printer.amounts.get(0), 0.001,
                "checkout harus memakai calculatePrice() milik objeknya, bukan mengecek jenis subclass");
    }

    @Test
    @DisplayName("OrderCalculator ikut menghitung jenis yang baru")
    void calculatorCountsNewKind() {
        LaundryDesk desk = new LaundryDesk(new TestSupport.RecordingPrinter());
        desk.add(new TestSupport.PremiumLaundry("N1", 2));
        desk.add(new TestSupport.TestOrder("L1", 1));
        assertEquals(2 * 5000.0 + 1000.0, OrderCalculator.totalPrice(desk.getOrders()), 0.001,
                "Jumlahkan lewat calculatePrice() agar jenis baru langsung terhitung (OCP: terbuka untuk diperluas)");
        assertEquals(0.0, OrderCalculator.totalDelivery(desk.getOrders()), 0.001,
                "Jenis baru tanpa Deliverable tidak punya biaya antar-jemput");
    }
}
