package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeskFindByIdTest {

    @Test
    @DisplayName("findById mengembalikan objek yang sama dengan yang disimpan")
    void findsStoredOrder() {
        LaundryDesk desk = new LaundryDesk(new TestSupport.RecordingPrinter());
        LaundryOrder first = new TestSupport.TestOrder("L1", 2);
        LaundryOrder second = new TestSupport.TestOrder("L2", 3);
        desk.add(first);
        desk.add(second);
        assertSame(second, desk.findById("L2"), "findById(\"L2\") harus mengembalikan objek yang sama");
        assertSame(first, desk.findById("L1"), "findById(\"L1\") harus mengembalikan objek yang sama");
    }
}
