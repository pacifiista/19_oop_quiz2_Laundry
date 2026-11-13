package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalAbilityTest {

    @Test
    @DisplayName("totalDelivery hanya menjumlahkan yang punya kemampuan Deliverable")
    void sumsOnlyAbleOrders() {
        List<LaundryOrder> orders = new ArrayList<>();
        orders.add(new TestSupport.TestOrder("L1", 2));
        orders.add(new TestSupport.TestAbilityOrder("L2", 3));
        orders.add(new TestSupport.TestAbilityOrder("L3", 1));
        assertEquals(1000.0, OrderCalculator.totalDelivery(orders), 0.001,
                "Periksa dengan instanceof Deliverable, lalu jumlahkan deliveryFee()");
    }

    @Test
    @DisplayName("Tanpa kemampuan Deliverable hasilnya 0")
    void noneAbleIsZero() {
        List<LaundryOrder> orders = new ArrayList<>();
        orders.add(new TestSupport.TestOrder("L1", 2));
        assertEquals(0.0, OrderCalculator.totalDelivery(orders), 0.001,
                "Bila tidak ada yang Deliverable, hasilnya harus 0");
    }

    @Test
    @DisplayName("Daftar kosong menghasilkan 0")
    void emptyListIsZero() {
        assertEquals(0.0, OrderCalculator.totalDelivery(new ArrayList<>()), 0.001, "Daftar kosong harus 0");
    }
}
