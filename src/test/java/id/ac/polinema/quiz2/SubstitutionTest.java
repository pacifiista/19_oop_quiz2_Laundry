package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SubstitutionTest {

    @Test
    @DisplayName("Setiap subclass LaundryOrder bisa menggantikan LaundryOrder di LaundryDesk")
    void anySubclassWorks() {
        TestSupport.RecordingPrinter printer = new TestSupport.RecordingPrinter();
        LaundryDesk desk = new LaundryDesk(printer);
        LaundryOrder a = new TestSupport.TestOrder("A", 3);
        LaundryOrder b = new TestSupport.TestAbilityOrder("B", 2);
        LaundryOrder c = new TestSupport.FreeOrder("C", 7);
        LaundryOrder d = new TestSupport.PremiumLaundry("D", 1);
        List<LaundryOrder> all = List.of(a, b, c, d);
        for (LaundryOrder order : all) {
            desk.add(order);
        }
        for (LaundryOrder order : all) {
            desk.checkout(order.getOrderId());
        }
        assertEquals(4, printer.ids.size(), "Semua jenis harus bisa di-checkout dengan cara yang sama");
        for (int i = 0; i < all.size(); i++) {
            assertEquals(all.get(i).calculatePrice(), printer.amounts.get(i), 0.001,
                    "Jumlah untuk " + all.get(i).getOrderId() + " harus sama dengan calculatePrice() miliknya");
        }
    }

    @Test
    @DisplayName("totalPrice memperlakukan semua jenis sama")
    void totalTreatsAllKindsAlike() {
        LaundryDesk desk = new LaundryDesk(new TestSupport.RecordingPrinter());
        desk.add(new TestSupport.TestOrder("A", 3));
        desk.add(new TestSupport.FreeOrder("C", 7));
        desk.add(new TestSupport.PremiumLaundry("D", 1));
        assertEquals(3000.0 + 0.0 + 5000.0, OrderCalculator.totalPrice(desk.getOrders()), 0.001,
                "Subclass dengan harga 0 tetap harus ikut diproses tanpa error");
    }
}
