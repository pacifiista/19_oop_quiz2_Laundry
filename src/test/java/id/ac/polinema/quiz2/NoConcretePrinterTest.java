package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoConcretePrinterTest {

    @Test
    @DisplayName("LaundryDesk tidak punya field bertipe kelas konkret ConsoleReceiptPrinter")
    void noConcreteField() {
        for (Field f : LaundryDesk.class.getDeclaredFields()) {
            assertFalse(ConsoleReceiptPrinter.class.isAssignableFrom(f.getType()),
                    "Field '" + f.getName() + "' tidak boleh bertipe ConsoleReceiptPrinter: pakai interface ReceiptPrinter");
        }
    }

    @Test
    @DisplayName("Field ReceiptPrinter tidak static: tiap LaundryDesk memakai pencetak struk-nya sendiri")
    void printerFieldIsNotStatic() {
        for (Field f : LaundryDesk.class.getDeclaredFields()) {
            assertFalse(Modifier.isStatic(f.getModifiers()) && ReceiptPrinter.class.isAssignableFrom(f.getType()),
                    "Field '" + f.getName() + "' tidak boleh static: tiap LaundryDesk menyimpan objek dari constructor-nya");
        }
    }

    @Test
    @DisplayName("Dua LaundryDesk memakai pencetak struk masing-masing")
    void twoDesksUseTheirOwnPrinters() {
        TestSupport.RecordingPrinter one = new TestSupport.RecordingPrinter();
        TestSupport.RecordingPrinter two = new TestSupport.RecordingPrinter();
        LaundryDesk deskOne = new LaundryDesk(one);
        LaundryDesk deskTwo = new LaundryDesk(two);
        deskOne.add(new TestSupport.TestOrder("L1", 1));
        deskTwo.add(new TestSupport.TestOrder("L2", 1));
        deskOne.checkout("L1");
        deskTwo.checkout("L2");
        assertEquals("L1", one.ids.get(0), "Objek ReceiptPrinter pertama hanya boleh menerima L1");
        assertEquals("L2", two.ids.get(0), "Objek ReceiptPrinter kedua hanya boleh menerima L2");
        assertEquals(1, one.ids.size(), "Objek ReceiptPrinter pertama menerima tepat satu panggilan");
    }
}
