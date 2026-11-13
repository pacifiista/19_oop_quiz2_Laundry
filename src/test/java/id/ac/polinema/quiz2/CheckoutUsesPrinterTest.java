package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CheckoutUsesPrinterTest {

    @Test
    @DisplayName("checkout memanggil ReceiptPrinter yang diberikan, dengan kode dan harga")
    void callsInjectedPrinter() {
        TestSupport.RecordingPrinter printer = new TestSupport.RecordingPrinter();
        LaundryDesk desk = new LaundryDesk(printer);
        desk.add(new TestSupport.TestOrder("L1", 4));
        desk.checkout("L1");
        assertEquals(1, printer.ids.size(),
                "checkout harus memanggil print() tepat satu kali pada objek yang diberikan lewat constructor");
        assertEquals("L1", printer.ids.get(0), "Kode yang dikirim ke print() harus L1");
        assertEquals(4000.0, printer.amounts.get(0), 0.001, "Jumlah harus calculatePrice() milik pesanan laundry itu");
    }

    @Test
    @DisplayName("checkout memakai objek yang dikirim, bukan objek buatan sendiri")
    void usesTheGivenObject() {
        TestSupport.RecordingPrinter first = new TestSupport.RecordingPrinter();
        TestSupport.RecordingPrinter second = new TestSupport.RecordingPrinter();
        LaundryDesk desk = new LaundryDesk(second);
        desk.add(new TestSupport.TestOrder("L1", 1));
        desk.checkout("L1");
        assertEquals(0, first.ids.size(), "Objek lain tidak boleh dipanggil");
        assertEquals(1, second.ids.size(), "Objek yang dikirim ke constructor harus dipanggil");
    }

    @Test
    @DisplayName("checkout untuk kode yang tidak ada tidak memanggil pencetak struk dan tidak error")
    void unknownIdDoesNothing() {
        TestSupport.RecordingPrinter printer = new TestSupport.RecordingPrinter();
        LaundryDesk desk = new LaundryDesk(printer);
        assertDoesNotThrow(() -> desk.checkout("L9"), "Kode yang tidak ada tidak boleh menyebabkan error");
        assertEquals(0, printer.ids.size(), "Kode yang tidak ada tidak boleh diteruskan ke pencetak struk");
    }
}
