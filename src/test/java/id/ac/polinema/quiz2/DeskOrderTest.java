package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeskOrderTest {

    @Test
    @DisplayName("getOrders mengembalikan daftar menurut urutan penambahan")
    void keepsInsertionOrder() {
        LaundryDesk desk = new LaundryDesk(new TestSupport.RecordingPrinter());
        desk.add(new TestSupport.TestOrder("L3", 1));
        desk.add(new TestSupport.TestOrder("L1", 1));
        desk.add(new TestSupport.TestOrder("L2", 1));
        List<LaundryOrder> orders = desk.getOrders();
        assertNotNull(orders, "getOrders() tidak boleh null");
        assertEquals(3, orders.size(), "getOrders() harus berisi tiga pesanan laundry");
        assertEquals("L3", orders.get(0).getOrderId(), "Urutan pertama harus L3 (urutan penambahan)");
        assertEquals("L1", orders.get(1).getOrderId(), "Urutan kedua harus L1");
        assertEquals("L2", orders.get(2).getOrderId(), "Urutan ketiga harus L2");
    }

    @Test
    @DisplayName("getOrders pada LaundryDesk kosong mengembalikan daftar kosong")
    void emptyDeskGivesEmptyList() {
        LaundryDesk desk = new LaundryDesk(new TestSupport.RecordingPrinter());
        assertEquals(0, desk.getOrders().size(), "LaundryDesk kosong harus menghasilkan daftar kosong");
    }
}
