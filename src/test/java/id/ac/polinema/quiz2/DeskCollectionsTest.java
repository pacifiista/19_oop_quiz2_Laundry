package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeskCollectionsTest {

    @Test
    @DisplayName("LaundryDesk punya field bertipe List")
    void hasListField() {
        boolean found = false;
        for (Field f : LaundryDesk.class.getDeclaredFields()) {
            if (List.class.isAssignableFrom(f.getType())) {
                found = true;
            }
        }
        assertTrue(found, "Simpan pesanan laundry di field bertipe List, misalnya ArrayList");
    }

    @Test
    @DisplayName("LaundryDesk punya field bertipe Map")
    void hasMapField() {
        boolean found = false;
        for (Field f : LaundryDesk.class.getDeclaredFields()) {
            if (Map.class.isAssignableFrom(f.getType())) {
                found = true;
            }
        }
        assertTrue(found, "Cari pesanan laundry lewat kode memakai field bertipe Map");
    }

    @Test
    @DisplayName("LaundryDesk tidak memakai array dan semua field private")
    void noArraysAndPrivate() {
        for (Field f : LaundryDesk.class.getDeclaredFields()) {
            assertFalse(f.getType().isArray(), "Field '" + f.getName() + "' tidak boleh array: pakai List atau Map");
            assertTrue(Modifier.isPrivate(f.getModifiers()), "Field '" + f.getName() + "' harus private");
        }
    }
}
