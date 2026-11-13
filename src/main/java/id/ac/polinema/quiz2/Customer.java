package id.ac.polinema.quiz2;

/**
 * TAHAP 1 (pemanasan): pelanggan yang membuat pesanan.
 *
 * Lengkapi setiap method (ganti baris
 * {@code throw new UnsupportedOperationException(...)} dengan kode yang benar).
 * Jangan mengubah nama kelas, nama method, atau signature-nya.
 *
 * Deklarasikan sendiri field-nya sesuai diagram kelas di README.
 * Semua field harus private.
 */
public class Customer {

    private String customerId;
    private String name;
    
    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }
    public String getName() {
        return name;
    }

    /**
     * Mengubah nama. Nama yang null atau kosong (hanya spasi) tidak valid:
     * abaikan dan biarkan nama lama tetap.
     *
     * @param name nama baru
     */
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }
}

