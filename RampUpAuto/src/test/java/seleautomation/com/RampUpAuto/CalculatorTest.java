package seleautomation.com.RampUpAuto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
	Calculator calculator;
	

	public CalculatorTest() {
		calculator = new Calculator();
	}

	@BeforeEach
	public void setUp() {
		System.out.println("Starting a boring test");
	}

	@AfterEach
	public void tearDown() {
		System.out.println("Finished a boring test");
	}

	@BeforeAll
	public static void oneTimeSetUp() {
		System.out.println("Started testing the great calculator app");
	}

	@AfterAll
	public static void oneTimeTearDown() {
		System.out.println("Finished testing the great calculator app");
	}

	@Test
	public void testAddMethodWhenTheTwoNumbersIsPositive() {
		Assertions.assertEquals(6, calculator.add(2.5, 3.5));
	}

	@Test
	public void testAddMethodWhenTheTwoNumbersIsNegative() {
		Assertions.assertEquals(-6, calculator.add(-2.5, -3.5));
	}

	@Test
	public void testAddMethodWhenTheFirstNumberIsPositiveAndSecondNumberIsNegative() {
		Assertions.assertEquals(-1, calculator.add(+2.5, -3.5));
	}

	@Test
	public void testAddMethodWhenTheFirstNumberIsNegativeAndSecondNumberIsPositive() {
		Assertions.assertEquals(1, calculator.add(-2.5, +3.5));
	}

	@Test
	public void testSubtract() {
		Assertions.assertEquals(2.1, calculator.subtract(5.5, 3.4));
	}

	@Test
	public void testMultiply() {
		Assertions.assertEquals(20, calculator.multiply(10, 2));
	}

	@Test
	public void testDivide() {
		Assertions.assertEquals(4, calculator.divide(20, 5));
	}

}
