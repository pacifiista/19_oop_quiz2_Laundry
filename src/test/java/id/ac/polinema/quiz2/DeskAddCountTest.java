package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeskAddCountTest {

    @Test
    @DisplayName("LaundryDesk baru berisi 0 pesanan laundry")
    void newDeskIsEmpty() {
        LaundryDesk desk = new LaundryDesk(new TestSupport.RecordingPrinter());
        assertEquals(0, desk.count(), "LaundryDesk yang baru dibuat harus kosong");
    }

    @Test
    @DisplayName("add menambah jumlah pesanan laundry")
    void addIncreasesCount() {
        LaundryDesk desk = new LaundryDesk(new TestSupport.RecordingPrinter());
        desk.add(new TestSupport.TestOrder("L1", 2));
        desk.add(new TestSupport.TestOrder("L2", 3));
        assertEquals(2, desk.count(), "Setelah dua kali add, count() harus 2");
    }
}
