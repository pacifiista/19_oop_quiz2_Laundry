package id.ac.polinema.quiz2;

public class ExpressLaundry extends LaundryOrder implements Deliverable {

    public ExpressLaundry(String orderId, Customer customer, double weightKg) {
        super(orderId, customer, weightKg);
    }

    @Override
    public double calculatePrice() {
        return getWeightKg() * 9000.0;
    }

    @Override
    public double deliveryFee() {
        return 5000.0;
    }

}
