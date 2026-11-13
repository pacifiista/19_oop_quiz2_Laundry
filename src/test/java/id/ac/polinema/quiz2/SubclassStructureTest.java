package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** RegularLaundry dan ExpressLaundry belum ada di kode awal, jadi dicari lewat reflection. */
class SubclassStructureTest {

    @Test
    @DisplayName("Kelas RegularLaundry ada dan extends LaundryOrder")
    void sub1ExtendsBase() {
        Class<?> c = TestSupport.loadClass("RegularLaundry");
        assertTrue(LaundryOrder.class.isAssignableFrom(c) && c != LaundryOrder.class,
                "RegularLaundry harus extends LaundryOrder");
    }

    @Test
    @DisplayName("Kelas ExpressLaundry ada dan extends LaundryOrder")
    void sub2ExtendsBase() {
        Class<?> c = TestSupport.loadClass("ExpressLaundry");
        assertTrue(LaundryOrder.class.isAssignableFrom(c) && c != LaundryOrder.class,
                "ExpressLaundry harus extends LaundryOrder");
    }

    @Test
    @DisplayName("Kedua subclass bukan kelas abstrak (objeknya bisa dibuat)")
    void subclassesAreConcrete() {
        assertFalse(Modifier.isAbstract(TestSupport.loadClass("RegularLaundry").getModifiers()),
                "RegularLaundry tidak boleh abstract: method calculatePrice() harus diisi");
        assertFalse(Modifier.isAbstract(TestSupport.loadClass("ExpressLaundry").getModifiers()),
                "ExpressLaundry tidak boleh abstract: method calculatePrice() harus diisi");
    }

    @Test
    @DisplayName("Constructor (orderId, Customer, weightKg) meneruskan kode ke superclass")
    void constructorPassesToSuper() throws Throwable {
        LaundryOrder a = TestSupport.newStudentOrder("RegularLaundry", "L1", 5);
        LaundryOrder b = TestSupport.newStudentOrder("ExpressLaundry", "L2", 3);
        assertEquals("L1", a.getOrderId(), "Panggil super(...) di constructor RegularLaundry");
        assertEquals(3.0, b.getWeightKg(), 0.001, "Panggil super(...) di constructor ExpressLaundry");
    }
}
