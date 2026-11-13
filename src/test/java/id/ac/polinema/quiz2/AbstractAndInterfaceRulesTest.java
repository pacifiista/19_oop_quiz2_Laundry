package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbstractAndInterfaceRulesTest {

    @Test
    @DisplayName("LaundryOrder tetap kelas abstrak")
    void baseStaysAbstract() {
        assertTrue(Modifier.isAbstract(LaundryOrder.class.getModifiers()),
                "LaundryOrder harus tetap abstract: objek pesanan laundry umum tidak masuk akal");
    }

    @Test
    @DisplayName("Deliverable tetap interface kecil dengan satu method")
    void abilityIsSmallInterface() {
        assertTrue(Deliverable.class.isInterface(), "Deliverable harus tetap interface");
        assertEquals(1, Deliverable.class.getDeclaredMethods().length,
                "Deliverable harus kecil dan fokus: satu method saja");
    }

    @Test
    @DisplayName("RegularLaundry TIDAK implements Deliverable")
    void sub1DoesNotImplementAbility() throws Throwable {
        LaundryOrder order = TestSupport.newStudentOrder("RegularLaundry", "L1", 3);
        assertFalse(order instanceof Deliverable,
                "RegularLaundry tidak punya antar-jemput, jadi tidak boleh implements Deliverable");
    }

    @Test
    @DisplayName("Hanya ExpressLaundry yang implements Deliverable")
    void onlySub2ImplementsAbility() throws Throwable {
        LaundryOrder order = TestSupport.newStudentOrder("ExpressLaundry", "L1", 3);
        assertTrue(order instanceof Deliverable, "ExpressLaundry harus implements Deliverable");
    }
}
