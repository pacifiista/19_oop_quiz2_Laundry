package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerEncapsulationTest {

    @Test
    @DisplayName("Semua field Customer private")
    void allFieldsPrivate() {
        Field[] fields = Customer.class.getDeclaredFields();
        assertTrue(fields.length >= 2, "Customer harus punya field untuk kode dan nama");
        for (Field f : fields) {
            assertTrue(Modifier.isPrivate(f.getModifiers()),
                    "Field '" + f.getName() + "' harus private");
        }
    }

    @Test
    @DisplayName("setName mengubah nama")
    void setNameUpdatesName() {
        Customer entity = new Customer("C001", "Budi");
        entity.setName("Budi Santoso");
        assertEquals("Budi Santoso", entity.getName(), "setName harus mengubah nama");
    }

    @Test
    @DisplayName("setName mengabaikan nama null")
    void setNameIgnoresNull() {
        Customer entity = new Customer("C001", "Budi");
        entity.setName(null);
        assertEquals("Budi", entity.getName(), "Nama null tidak valid: nama lama harus tetap");
    }

    @Test
    @DisplayName("setName mengabaikan nama kosong atau hanya spasi")
    void setNameIgnoresBlank() {
        Customer entity = new Customer("C001", "Budi");
        entity.setName("   ");
        assertEquals("Budi", entity.getName(), "Nama yang hanya spasi tidak valid: nama lama harus tetap");
        entity.setName("");
        assertEquals("Budi", entity.getName(), "Nama kosong tidak valid: nama lama harus tetap");
    }
}
