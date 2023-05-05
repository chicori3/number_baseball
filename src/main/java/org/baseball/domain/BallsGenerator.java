package org.baseball.domain;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.baseball.domain.Message.*;

public class BallsGenerator {
    public List<Integer> randomBallsGenerate() {
        SecureRandom random = new SecureRandom();
        return IntStream.generate(() -> random.nextInt(9) + 1)
                .distinct()
                .limit(3)
                .boxed()
                .collect(Collectors.toList());
    }

    public List<Integer> customBallsGenerate(int... numbers) {
        isValid(numbers);

        return IntStream.of(numbers)
                .boxed()
                .collect(Collectors.toList());
    }

    private void isValid(int[] numbers) {
        isValidLength(numbers);
        isValidNumber(numbers);
        hasDuplicatedNumber(numbers);
    }

    private void isValidNumber(int[] numbers) {
        IntStream.of(numbers)
                .filter(number -> number < 1 || number > 9)
                .findAny()
                .ifPresent((number) -> {
                    throw new IllegalArgumentException(WRONG_NUMBER_RANGE);
                });
    }

    private void hasDuplicatedNumber(int[] numbers) {
        Set<Integer> numbersSet = convertArrToSet(numbers);

        if (numbers.length < numbersSet.size()) {
            throw new IllegalArgumentException(NUMBER_DUPLICATED);
        }
    }

    private void isValidLength(int[] numbers) {
        if (numbers.length != 3) {
            throw new IllegalArgumentException(WRONG_NUMBER_LENGTH);
        }
    }

    private Set<Integer> convertArrToSet(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toSet());
    }
}
