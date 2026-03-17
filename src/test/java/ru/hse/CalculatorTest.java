package ru.hse;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса Calculator.
 * Домашнее задание 1: Реализация калькулятора.
 * Курс: Программирование на Java, HSE.
 */
@DisplayName("Тесты базового калькулятора")
class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void initAll() {
        System.out.println("Начало тестирования ДЗ №1");
        System.out.println("Студент: " + System.getProperty("user.name"));
    }

    @BeforeEach
    void init() {
        calculator = new Calculator();
        System.out.println("Создан новый экземпляр калькулятора");
    }

    @AfterEach
    void tearDown() {
        calculator = null;
        System.out.println("Очистка после теста");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Завершение тестирования ДЗ №1");
    }

    @Test
    @DisplayName("Тест 1.1: Сложение положительных чисел")
    void testAddPositiveNumbers() {
        // Arrange
        int a = 10;
        int b = 5;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(15, result, "10 + 5 должно быть 15");
        assertTrue(result > 0, "Результат должен быть положительным");
    }

    @Test
    @DisplayName("Тест 1.2: Сложение отрицательных чисел")
    void testAddNegativeNumbers() {
        assertEquals(-15, calculator.add(-10, -5),
                "-10 + (-5) должно быть -15");
    }

    @Test
    @DisplayName("Тест 1.3: Сложение с нулем")
    void testAddWithZero() {
        assertAll("Сложение с нулем",
                () -> assertEquals(10, calculator.add(10, 0), "10 + 0 = 10"),
                () -> assertEquals(0, calculator.add(0, 0), "0 + 0 = 0"),
                () -> assertEquals(-5, calculator.add(-5, 0), "-5 + 0 = -5")
        );
    }

    @Test
    @DisplayName("Тест 2.1: Вычитание положительных чисел")
    void testSubtractPositiveNumbers() {
        assertAll("Вычитание положительных чисел",
                () -> assertEquals(5, calculator.subtract(10, 5), "10 - 5 = 5"),
                () -> assertEquals(0, calculator.subtract(5, 5), "5 - 5 = 0"),
                () -> assertEquals(-5, calculator.subtract(5, 10), "5 - 10 = -5")
        );
    }

    @Test
    @DisplayName("Тест 2.2: Вычитание отрицательных чисел")
    void testSubtractNegativeNumbers() {
        assertEquals(-5, calculator.subtract(-10, -5),
                "-10 - (-5) = -5");
    }

    @Test
    @DisplayName("Тест 3.1: Умножение чисел")
    void testMultiply() {
        assertAll("Умножение",
                () -> assertEquals(50, calculator.multiply(10, 5), "10 * 5 = 50"),
                () -> assertEquals(-50, calculator.multiply(10, -5), "10 * (-5) = -50"),
                () -> assertEquals(0, calculator.multiply(10, 0), "10 * 0 = 0"),
                () -> assertEquals(25, calculator.multiply(-5, -5), "(-5) * (-5) = 25")
        );
    }

    @Test
    @DisplayName("Тест 4.1: Деление чисел")
    void testDivide() {
        assertAll("Деление",
                () -> assertEquals(2, calculator.divide(10, 5), "10 / 5 = 2"),
                () -> assertEquals(0, calculator.divide(1, 2), "1 / 2 = 0 (целочисленное деление)"),
                () -> assertEquals(-2, calculator.divide(-10, 5), "-10 / 5 = -2"),
                () -> assertEquals(2, calculator.divide(-10, -5), "-10 / -5 = 2")
        );
    }

    @Test
    @DisplayName("Тест 4.2: Деление на ноль (должно выбрасывать исключение)")
    void testDivideByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> calculator.divide(10, 0));

        assertEquals("Деление на ноль запрещено", exception.getMessage());
        assertTrue(exception.getMessage().contains("ноль"));
    }

    @Test
    @DisplayName("Тест 5.1: Проверка на четность")
    void testIsEven() {
        assertAll("Проверка четности",
                () -> assertTrue(calculator.isEven(2), "2 должно быть четным"),
                () -> assertTrue(calculator.isEven(0), "0 должно быть четным"),
                () -> assertTrue(calculator.isEven(-4), "-4 должно быть четным"),
                () -> assertFalse(calculator.isEven(3), "3 должно быть нечетным"),
                () -> assertFalse(calculator.isEven(-1), "-1 должно быть нечетным")
        );
    }

    @Test
    @DisplayName("Тест 6.1: Поиск максимума")
    void testMax() {
        assertAll("Поиск максимума",
                () -> assertEquals(10, calculator.max(10, 5), "max(10, 5) = 10"),
                () -> assertEquals(10, calculator.max(5, 10), "max(5, 10) = 10"),
                () -> assertEquals(-5, calculator.max(-5, -10), "max(-5, -10) = -5"),
                () -> assertEquals(0, calculator.max(0, -1), "max(0, -1) = 0"),
                () -> assertEquals(5, calculator.max(5, 5), "max(5, 5) = 5")
        );
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "10, -5, 5",
            "-10, -5, -15",
            "0, 5, 5",
            "100, 200, 300"
    })
    @DisplayName("Тест 1.4: Параметризованный тест сложения")
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b),
                () -> String.format("%d + %d = %d", a, b, expected));
    }

    @ParameterizedTest
    @CsvSource({
            "10, 5, 5",
            "10, 3, 1",
            "10, 10, 0",
            "10, 20, -10"
    })
    @DisplayName("Тест 2.3: Параметризованный тест вычитания")
    void testSubtractParameterized(int a, int b, int expected) {
        assertEquals(expected, calculator.subtract(a, b));
    }

    @Test
    @DisplayName("Тест производительности (должен выполняться быстро)")
    @Timeout(1)
    void testPerformance() {
        for (int i = 0; i < 1000000; i++) {
            calculator.add(i, i);
            calculator.multiply(i, 2);
        }
    }
}