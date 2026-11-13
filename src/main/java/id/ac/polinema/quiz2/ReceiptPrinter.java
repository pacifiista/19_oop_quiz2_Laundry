package id.ac.polinema.quiz2;

/**
 * INTERFACE untuk pencetak struk. Kelas ini SUDAH LENGKAP, jangan diubah.
 *
 * LaundryDesk hanya boleh mengenal interface ini, bukan kelas konkretnya.
 */
public interface ReceiptPrinter {

    /**
     * @param orderId kode pesanan laundry
     * @param amount jumlah yang harus dibayar, dalam Rupiah
     */
    void print(String orderId, double amount);
}
