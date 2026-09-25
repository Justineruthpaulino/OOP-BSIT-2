package semifinal;
/**
 * StudentDiscount - an INTERFACE (a list of promises).
 * Only rides that give students a discount implement this.
 */
public interface StudentDiscount {
    double discountedFare();
    
// GIVEN: a default method. Every class that implements this
    // interface gets it for free. Notice it calls discountedFare().
    default void printDiscount() {
        System.out.printf(" Student fare : PHP %.2f%n", discountedFare());
    }
}