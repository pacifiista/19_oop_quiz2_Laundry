package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalPriceTest {

    @Test
    @DisplayName("totalPrice menjumlahkan harga semua jenis dengan satu looping")
    void sumsAllKinds() {
        List<LaundryOrder> orders = new ArrayList<>();
        orders.add(new TestSupport.TestOrder("L1", 2));
        orders.add(new TestSupport.TestAbilityOrder("L2", 3));
        assertEquals(5000.0, OrderCalculator.totalPrice(orders), 0.001,
                "Panggil calculatePrice() pada tiap LaundryOrder lalu jumlahkan");
    }

    @Test
    @DisplayName("Daftar kosong menghasilkan 0")
    void emptyListIsZero() {
        assertEquals(0.0, OrderCalculator.totalPrice(new ArrayList<>()), 0.001, "Daftar kosong harus 0");
    }

    @Test
    @DisplayName("Jenis yang tidak dikenal ikut dihitung")
    void unknownKindIsCounted() {
        List<LaundryOrder> orders = new ArrayList<>();
        orders.add(new TestSupport.PremiumLaundry("L1", 2));
        assertEquals(2 * 5000.0, OrderCalculator.totalPrice(orders), 0.001,
                "Jangan mengecek nama subclass: biarkan tiap objek menghitung harga-nya sendiri");
    }
}
