package es.iessaladillo.pedrojoya.pr01.bmi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static es.iessaladillo.pedrojoya.pr01.bmi.BmiCalculator.BmiClasification.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BmiCalculatorTest {

    @DisplayName("Should calculate BMI correctly")
    @Test
    void should_calculate_Bmi_correctly() {
        BmiCalculator sut = new BmiCalculator();

        float actual = sut.calculateBmi(100, 2);

        assertEquals(25, actual);
    }

    @DisplayName("Should throw IllegalArgumentException when height is not positive")
    @ParameterizedTest
    @ValueSource(floats = {-1, 0})
    void should_throw_IllegalArgumentException_when_height_not_positive(float height) {
        BmiCalculator sut = new BmiCalculator();

        assertThrows(IllegalArgumentException.class, () -> sut.calculateBmi(100, height));
    }

    @DisplayName("Should throw IllegalArgumentException when weight is not positive")
    @ParameterizedTest
    @ValueSource(floats = {-1, 0})
    void should_throw_IllegalArgumentException_when_weight_not_positive(float weight) {
        BmiCalculator sut = new BmiCalculator();

        assertThrows(IllegalArgumentException.class, () -> sut.calculateBmi(weight, 2));
    }

    @DisplayName("Should return LOW_WEIGHT")
    @ParameterizedTest
    @ValueSource(floats = {-1, 0, 10, 17.99f})
    void should_return_LOW_WEIGHT(float bmi) {
        BmiCalculator sut = new BmiCalculator();

        BmiCalculator.BmiClasification actual = sut.getBmiClasification(bmi);

        assertEquals(LOW_WEIGHT, actual);
    }

    @DisplayName("Should return NORMAL_WEIGHT")
    @ParameterizedTest
    @ValueSource(floats = {18, 20, 24.89f})
    void should_return_normal_weight(float bmi) {
        BmiCalculator sut = new BmiCalculator();

        BmiCalculator.BmiClasification actual = sut.getBmiClasification(bmi);

        assertEquals(NORMAL_WEIGHT, actual);
    }

    @DisplayName("Should return OVERWEIGHT")
    @ParameterizedTest
    @ValueSource(floats = {24.9f, 25.5f, 26.89f})
    void should_return_overweight(float bmi) {
        BmiCalculator sut = new BmiCalculator();

        BmiCalculator.BmiClasification actual = sut.getBmiClasification(bmi);

        assertEquals(OVERWWEIGHT, actual);
    }

    @DisplayName("Should return OBESITY_GRADE1")
    @ParameterizedTest
    @ValueSource(floats = {26.9f, 28f, 29.89f})
    void should_return_obesity_grade1(float bmi) {
        BmiCalculator sut = new BmiCalculator();

        BmiCalculator.BmiClasification actual = sut.getBmiClasification(bmi);

        assertEquals(OBESITY_GRADE_1, actual);
    }

    @DisplayName("Should return OBESITY_GRADE2")
    @ParameterizedTest
    @ValueSource(floats = {29.9f, 34f, 39.89f})
    void should_return_obesity_grade2(float bmi) {
        BmiCalculator sut = new BmiCalculator();

        BmiCalculator.BmiClasification actual = sut.getBmiClasification(bmi);

        assertEquals(OBESITY_GRADE_2, actual);
    }

    @DisplayName("Should return OBESITY_GRADE3")
    @ParameterizedTest
    @ValueSource(floats = {39.9f, 42})
    void should_return_obesity_grade3(float bmi) {
        BmiCalculator sut = new BmiCalculator();

        BmiCalculator.BmiClasification actual = sut.getBmiClasification(bmi);

        assertEquals(OBESITY_GRADE_3, actual);
    }

}