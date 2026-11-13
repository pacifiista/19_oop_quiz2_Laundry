package id.ac.polinema.quiz2;

import java.util.ArrayList;
import java.util.List;

/**
 * Tempat bebas untuk mencoba programmu. Kelas ini TIDAK dinilai.
 *
 * Ide percobaan (tulis sendiri setelah tiap tahap selesai):
 *  1. Buat satu Customer, lalu coba ubah namanya.
 *  2. Buat satu RegularLaundry dan satu ExpressLaundry, lalu tampilkan harga-nya.
 *  3. Masukkan keduanya ke OrderCalculator.totalPrice(...) lewat sebuah List.
 *  4. Buat LaundryDesk dengan new ConsoleReceiptPrinter(), tambahkan pesanan laundry, lalu panggil checkout(...).
 *
 * Jalankan lewat NetBeans (Run Project), VS Code (Run di atas main),
 * atau terminal: mvn -q compile exec:java
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Laundry Kiloan: tulis percobaanmu di sini.");

        // 1. buat satu Customer, lalu coba ubah namanya
        Customer cust = new Customer("C001", "Budi");
        System.out.println("Perubahan nama customer:" + cust.getName());
        cust.setName("Budi Santoso");
        System.out.println("Nama customer setelah diubah: " + cust.getName() + "\n");

        // 2. buat satu RegularLaundry dan satu ExpressLaundry, dan tampilkan harganya
        // Parameter: (orderId, customer, weightKg)
        // Contoh berat 5 kg:
        RegularLaundry reg = new RegularLaundry("L1", cust, 5.0);
        ExpressLaundry exp = new ExpressLaundry("L2", cust, 5.0);

        System.out.println("Harga Regular Laundry (5 kg): Rp" + reg.calculatePrice());
        System.out.println("Harga Express Laundry (5 kg): Rp" + exp.calculatePrice());
        System.out.println("Biaya antar-jemput Express: Rp" + exp.deliveryFee() + "\n");

        //3. Masukkan keduanya ke OrderCalculator.totalPrice(...) lewat sebuah List.
        List<LaundryOrder> orders = new ArrayList<>();
        orders.add(reg);
        orders.add(exp);

        double totalHarga = OrderCalculator.totalPrice(orders);
        double totalDelivery = OrderCalculator.totalDelivery(orders);

        System.out.println("Total harga semua pesanan: Rp " + totalHarga);
        System.out.println("Total biaya antar-jemput: Rp " + totalDelivery + "\n");


        // 4. Buat LaundryDesk dengan new ConsoleReceiptPrinter(), tambahkan pesanan laundry, lalu panggil checkout(...).
        System.out.println("Simulasi Checkout via LaundryDesk: ");
        LaundryDesk desk = new LaundryDesk(new ConsoleReceiptPrinter());
        desk.add(reg);
        desk.add(exp);

        // Memanggil checkout untuk kode L1 dan L2
        desk.checkout("L1");
        desk.checkout("L2");
    }
}

