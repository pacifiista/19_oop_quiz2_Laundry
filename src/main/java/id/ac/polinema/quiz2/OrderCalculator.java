package id.ac.polinema.quiz2;

import java.util.List;

/**
 * TAHAP 3 (polimorfisme dan exception): perhitungan atas banyak pesanan laundry.
 *
 * Lengkapi setiap method (ganti baris
 * {@code throw new UnsupportedOperationException(...)}).
 * Semua method memproses objek lewat tipe {@code LaundryOrder}, tanpa mengecek
 * nama subclass.
 */
public class OrderCalculator {

    /**
     * Menjumlahkan harga semua pesanan laundry di daftar.
     * Satu looping untuk semua jenis, tanpa cabang if per subclass.
     *
     * @param orders daftar pesanan laundry
     * @return jumlah semua harga, atau 0 bila daftar kosong
     */
    public static double totalPrice(List<LaundryOrder> orders) {
        if (orders == null || orders.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (LaundryOrder order : orders) {
            total += order.calculatePrice();
        }
        return total;
    }

    /**
     * Menjumlahkan biaya antar-jemput dari pesanan laundry yang punya
     * kemampuan itu. Periksa kemampuannya dengan {@code instanceof Deliverable},
     * bukan nama subclass.
     *
     * @param orders daftar pesanan laundry
     * @return jumlah biaya antar-jemput, atau 0 bila tidak ada yang punya
     */
    public static double totalDelivery(List<LaundryOrder> orders) {
       if (orders == null || orders.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (LaundryOrder order : orders) {
            if (order instanceof Deliverable) {
                total += ((Deliverable) order).deliveryFee();
            }
        }
        return total;
    }

    /**
     * Memeriksa berat. Valid hanya bila berat lebih dari 0 dan paling banyak 20 kg.
     * Bila tidak valid, lempar {@code InvalidWeightException} dengan pesan
     * "Invalid weight: " diikuti nilai berat-nya.
     *
     * @param order pesanan laundry yang diperiksa
     * @throws InvalidWeightException bila berat tidak valid
     */
    public static void validate(LaundryOrder order) throws InvalidWeightException {
        if (order == null || order.getWeightKg() <= 0 || order.getWeightKg() > 20) {
            double weight = (order != null) ? order.getWeightKg() : 0.0;
            throw new InvalidWeightException("Invalid weight: " + weight);
        }
    }

    /**
     * Memanggil {@link #validate} di dalam try/catch.
     *
     * @param order pesanan laundry yang diperiksa
     * @return true bila valid, false bila validate melempar InvalidWeightException
     */
    public static boolean isAcceptable(LaundryOrder order) {
        try {
            validate(order);
            return true;
        } catch (InvalidWeightException e) {
            return false;
        }
    }
}


