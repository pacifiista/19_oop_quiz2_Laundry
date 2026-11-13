package id.ac.polinema.quiz2;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pembantu untuk semua tes. Tahap 3 sampai 5 memakai kelas buatan tes sendiri
 * (bukan kelas buatanmu), jadi satu tahap yang macet tidak menggagalkan
 * tahap lain.
 */
final class TestSupport {

    private TestSupport() {
    }

    /** Mencari kelas buatan siswa lewat reflection. */
    static Class<?> loadClass(String name) {
        try {
            return Class.forName("id.ac.polinema.quiz2." + name);
        } catch (ClassNotFoundException e) {
            return fail("Kelas " + name + " belum dibuat. Buat kelas ini di src/main/java sesuai diagram kelas di README.");
        }
    }

    /** Membuat objek subclass buatan siswa (entitas diisi null, tidak dipakai). */
    static LaundryOrder newStudentOrder(String className, String id, double weightKg) throws Throwable {
        Class<?> c = loadClass(className);
        Constructor<?> k;
        try {
            k = c.getConstructor(String.class, Customer.class, double.class);
        } catch (NoSuchMethodException e) {
            return fail("Constructor " + className + "(String, Customer, double) tidak ditemukan. Lihat diagram kelas di README.");
        }
        try {
            return (LaundryOrder) k.newInstance(id, null, weightKg);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        } catch (ClassCastException e) {
            return fail(className + " harus extends LaundryOrder.");
        }
    }

    /** Menjalankan aksi dan mengembalikan semua yang tercetak ke layar. */
    static String captureOut(Action action) throws Throwable {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }

    interface Action {
        void run() throws Throwable;
    }

    /** Subclass buatan tes: harga = berat x 1000. */
    static class TestOrder extends LaundryOrder {
        TestOrder(String id, double weightKg) {
            super(id, null, weightKg);
        }

        @Override
        public double calculatePrice() {
            return getWeightKg() * 1000;
        }
    }

    /** Subclass buatan tes dengan kemampuan antar-jemput (biaya 500). */
    static class TestAbilityOrder extends LaundryOrder implements Deliverable {
        TestAbilityOrder(String id, double weightKg) {
            super(id, null, weightKg);
        }

        @Override
        public double calculatePrice() {
            return getWeightKg() * 1000;
        }

        @Override
        public double deliveryFee() {
            return 500;
        }
    }

    /** Jenis baru yang tidak dikenal siswa: dipakai untuk uji OCP dan LSP. */
    static class PremiumLaundry extends LaundryOrder {
        PremiumLaundry(String id, double weightKg) {
            super(id, null, weightKg);
        }

        @Override
        public double calculatePrice() {
            return getWeightKg() * 5000;
        }
    }

    /** Jenis yang harga-nya selalu 0: dipakai untuk uji LSP. */
    static class FreeOrder extends LaundryOrder {
        FreeOrder(String id, double weightKg) {
            super(id, null, weightKg);
        }

        @Override
        public double calculatePrice() {
            return 0;
        }
    }

    /** pencetak struk palsu yang mencatat setiap pemanggilan. */
    static class RecordingPrinter implements ReceiptPrinter {
        final List<String> ids = new ArrayList<>();
        final List<Double> amounts = new ArrayList<>();

        @Override
        public void print(String orderId, double amount) {
            ids.add(orderId);
            amounts.add(amount);
        }
    }
}
