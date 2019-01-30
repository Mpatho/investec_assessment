package mpathozulu.assessment.investech.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class GCDUtilTest {

    private final GCDUtil gcdUtil = new GCDUtil();

    @Test
    public void highestCommonFactorOfSixTwelveEighteenAndTwentyFour() {
        int number[] = {6, 24, 18, 12};
        int expected = 6;

        int result = gcdUtil.highestCommonFactor(number);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void highestCommonFactorOfSixNineEighteenAndTwentyFour() {
        int number[] = {6, 9, 18, 24};
        int expected = 3;

        int result = gcdUtil.highestCommonFactor(number);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void highestCommonFactorOfEightAndNine() {
        int number[] = {8, 9};
        int expected = 1;

        int result = gcdUtil.highestCommonFactor(number);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void gcdOfSixAndNine() {
        int numberOne = 9;
        int numberTwo = 6;
        int expected = 3;

        int result = gcdUtil.gcd(numberOne, numberTwo);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void gcdOfTenAndFive() {
        int numberOne = 10;
        int numberTwo = 5;
        int expected = 5;

        int result = gcdUtil.gcd(numberOne, numberTwo);

        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void gcdOfTenAndSeven() {
        int numberOne = 10;
        int numberTwo = 7;
        int expected = 1;

        int result = gcdUtil.gcd(numberOne, numberTwo);

        assertThat(result, is(equalTo(expected)));
    }
}