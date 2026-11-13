package id.ac.polinema.quiz2;

/**
 * Kelas ABSTRAK untuk semua jenis pesanan laundry. Kelas ini SUDAH LENGKAP,
 * jangan diubah.
 *
 * Kelas abstrak belum lengkap: objeknya tidak boleh dibuat lewat {@code new}.
 * Setiap subclass wajib mengisi method abstrak {@code calculatePrice()}.
 */
public abstract class LaundryOrder {

    private String orderId;
    private Customer customer;
    private double weightKg;

    public LaundryOrder(String orderId, Customer customer, double weightKg) {
        this.orderId = orderId;
        this.customer = customer;
        this.weightKg = weightKg;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getWeightKg() {
        return weightKg;
    }

    /**
     * Menghitung harga pesanan laundry ini. Aturannya berbeda untuk tiap subclass.
     *
     * @return harga dalam Rupiah
     */
    public abstract double calculatePrice();
}
