package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeskInterfaceTest {

    @Test
    @DisplayName("LaundryDesk menyimpan pencetak struk di field bertipe interface ReceiptPrinter")
    void holdsPrinterAsInterface() {
        boolean found = false;
        for (Field f : LaundryDesk.class.getDeclaredFields()) {
            if (f.getType() == ReceiptPrinter.class) {
                found = true;
            }
        }
        assertTrue(found,
                "Simpan objek dari constructor di field bertipe ReceiptPrinter (interface), bukan ConsoleReceiptPrinter");
    }

    @Test
    @DisplayName("Field ReceiptPrinter bersifat private")
    void printerFieldIsPrivate() {
        for (Field f : LaundryDesk.class.getDeclaredFields()) {
            if (f.getType() == ReceiptPrinter.class) {
                assertTrue(Modifier.isPrivate(f.getModifiers()), "Field '" + f.getName() + "' harus private");
            }
        }
    }
}
