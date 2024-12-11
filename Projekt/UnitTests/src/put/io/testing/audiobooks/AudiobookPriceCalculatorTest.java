package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {
    private Audiobook book;
    private AudiobookPriceCalculator calc;
    @BeforeEach
    void setUp() {
        book = new Audiobook("Audiobook", 100);
        calc = new AudiobookPriceCalculator();
    }

    @Test
    void testSubscription(){
        Customer customer = new Customer("Michal", Customer.LoyaltyLevel.STANDARD, true);
        assertEquals(0 * book.getStartingPrice(), calc.calculate(customer, book));
    }

    @Test
    void testSilver(){
        Customer customer = new Customer("Artur", Customer.LoyaltyLevel.SILVER, false);
        assertEquals(0.9 * book.getStartingPrice(), calc.calculate(customer, book));
    }

    @Test
    void testGold(){
        Customer customer = new Customer("Szymon", Customer.LoyaltyLevel.GOLD, false);
        assertEquals(0.8 * book.getStartingPrice(), calc.calculate(customer, book));
    }

    @Test
    void testCustomer(){
        Customer customer = new Customer("Sebastian", Customer.LoyaltyLevel.STANDARD, false);
        assertEquals(book.getStartingPrice(), calc.calculate(customer, book));
    }
}