package id.ac.polinema.quiz2;

/**
 * Implementasi ReceiptPrinter yang mencetak ke layar. Kelas ini SUDAH LENGKAP,
 * jangan diubah.
 *
 * Dipakai di Main untuk mencoba program. LaundryDesk TIDAK boleh membuat objek
 * kelas ini sendiri: objeknya harus diberikan dari luar lewat constructor.
 */
public class ConsoleReceiptPrinter implements ReceiptPrinter {

    @Override
    public void print(String orderId, double amount) {
        System.out.println("Receipt " + orderId + ": Rp " + amount);
    }
}
