package semifinal;
/**
 * Taxi - a child of Ride with NO student discount.
 *
 * Fare: PHP 45.00 flag-down, plus PHP 13.50 per km.
 */
public class Taxi extends Ride {
    public Taxi(String passenger, double km) {
        super(passenger, km);
    }
    
@Override
    public double fare() {
        return 45.00 + getKm() * 13.50;
    }
    
@Override
    public String vehicle() {
        return "Taxi";
    }
}