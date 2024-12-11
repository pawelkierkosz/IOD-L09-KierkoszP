package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calc;

    @BeforeEach
    void setUpCalc() {
        calc = new Calculator();
    }

    @Test
    void testAdd() {
        assertEquals(6, calc.add(2,4));
        assertEquals(7, calc.add(4,3));
    }

    @Test
    void testMultiply() {
        assertEquals(25, calc.multiply(5,5));
        assertEquals(9, calc.multiply(3,3));
    }

    @Test
    void testAddPositiveNumbers() {
        assertThrows(IllegalArgumentException.class,
                ()->{
                    calc.addPositiveNumbers(-1, -3);
                });
    }
}