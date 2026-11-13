package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbilityInterfaceTest {

    @Test
    @DisplayName("ExpressLaundry implements Deliverable")
    void sub2ImplementsAbility() throws Throwable {
        LaundryOrder order = TestSupport.newStudentOrder("ExpressLaundry", "L1", 4);
        assertTrue(order instanceof Deliverable,
                "ExpressLaundry harus implements Deliverable");
    }

    @Test
    @DisplayName("deliveryFee() mengembalikan Rp 5.000")
    void abilityFee() throws Throwable {
        Deliverable order = (Deliverable) TestSupport.newStudentOrder("ExpressLaundry", "L1", 4);
        assertEquals(5000.0, order.deliveryFee(), 0.001,
                "deliveryFee() harus Rp 5.000, berapa pun berat-nya");
    }

    @Test
    @DisplayName("Biaya antar-jemput tidak bergantung pada berat")
    void abilityFeeIsFixed() throws Throwable {
        Deliverable small = (Deliverable) TestSupport.newStudentOrder("ExpressLaundry", "L1", 1);
        Deliverable large = (Deliverable) TestSupport.newStudentOrder("ExpressLaundry", "L2", 10);
        assertEquals(small.deliveryFee(), large.deliveryFee(), 0.001,
                "Biaya antar-jemput adalah tarif tetap Rp 5.000");
    }
}
