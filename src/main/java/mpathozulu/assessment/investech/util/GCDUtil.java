package mpathozulu.assessment.investech.util;

public class GCDUtil {

    public int highestCommonFactor(int[] numbers) {
        if (numbers.length < 2) {
            throw new IllegalArgumentException();
        }
        int highestCommonFactor = gcd(numbers[0], numbers[1]);
        int index = 2;
        while (index < numbers.length) {
            highestCommonFactor = gcd(highestCommonFactor, numbers[index++]);
        }
        return highestCommonFactor;
    }

    public int gcd(int numOne, int numTwo) {
        if (numOne > numTwo) {
            return gcd(numTwo, numOne);
        }
        int remainder = numTwo % numOne;

        if (remainder == 0) {
            return numOne;
        }
        return gcd(remainder, numOne);
    }
}
