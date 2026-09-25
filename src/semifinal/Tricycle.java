package semifinal;

// TODO 4: replace the comment in the class header
public class Tricycle extends Ride implements StudentDiscount {

    public Tricycle(String passenger, double km) {
        super(passenger, km);
    }

    // TODO 5: write your code here
    @Override
    public double fare() {
        if (getKm() <= 2) {
            return 20.00;
        } else {
            return 20.00 + (getKm() - 2) * 8.00;
        }
    }

    @Override
    public String vehicle() {
        return "Tricycle";
    }

    @Override
    public double discountedFare() {
        return fare() * 0.80;
    }
}