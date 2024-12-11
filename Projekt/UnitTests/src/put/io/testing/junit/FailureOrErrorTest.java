package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FailureOrErrrorTest {
    private Calculator calc;
    @BeforeEach
    void setUpCalc() {
        calc = new Calculator();
    }

    @Test
    void test1() {
        assertEquals(8, calc.multiply(2,3));
    }

    @Test
    void test2() {
        assertEquals(-3, calc.addPositiveNumbers(-2, -1));
    }

    @Test
    void test3() {
        try {
            assertEquals(5, calc.multiply(2,2));
        }
        catch (Exception ex){ ex.printStackTrace();};
    }
}