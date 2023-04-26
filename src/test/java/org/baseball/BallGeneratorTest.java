package org.baseball;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class BallGeneratorTest {

    private BallsGenerator ballsGenerator;

    @BeforeEach
    void setUp() {
        ballsGenerator = new BallsGenerator();
    }

    @Test
    @DisplayName("컴퓨터의 랜덤한 숫자 3개의 리스트를 생성한다")
    void randomGenerate() {
        List<Integer> randomBalls = ballsGenerator.randomBallsGenerate();

        assertThat(randomBalls.size()).isEqualTo(3);
        assertThat(Set.copyOf(randomBalls).size()).isEqualTo(3);
    }

    @Test
    @DisplayName("사용자의 숫자 3개를 입력받아 리스트를 생성한다")
    void customGenerate_success() {
        assertThatCode(() -> ballsGenerator.customBallsGenerate(1, 2, 3))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("provideWrongLengthNumbers")
    @DisplayName("사용자의 숫자가 3개가 아닌 경우 예외가 발생한다")
    void customGenerate_wrongLength(int... numbers) {
        assertThatCode(() -> ballsGenerator.customBallsGenerate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("3개의 숫자를 입력해주세요.");
    }

    @ParameterizedTest
    @MethodSource("provideUnValidNumbers")
    @DisplayName("사용자의 숫자에 1~9가 아닌 숫자가 포함된 경우 예외가 발생한다")
    void customGenerate_wrongNumber(int[] numbers) {
        assertThatCode(() -> ballsGenerator.customBallsGenerate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1~9 사이의 숫자를 입력해주세요.");
    }

    static Stream<Arguments> provideWrongLengthNumbers() {
        return Stream.of(
                Arguments.of(new int[]{1}),
                Arguments.of(new int[]{1, 2, 3, 4})
        );
    }

    static Stream<Arguments> provideUnValidNumbers() {
        return Stream.of(
                Arguments.of(new int[]{0, 1, 2}),
                Arguments.of(new int[]{10, 9, 8}),
                Arguments.of(new int[]{10, 9, 0})
        );
    }
}

class BallsGenerator {
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
                    throw new IllegalArgumentException("1~9 사이의 숫자를 입력해주세요.");
                });
    }

    private void hasDuplicatedNumber(int[] numbers) {
        Set<Integer> numbersSet = convertArrToSet(numbers);

        if (numbers.length < numbersSet.size()) {
            throw new IllegalArgumentException("중복된 숫자가 있습니다.");
        }
    }

    private void isValidLength(int[] numbers) {
        if (numbers.length != 3) {
            throw new IllegalArgumentException("3개의 숫자를 입력해주세요.");
        }
    }

    private Set<Integer> convertArrToSet(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toSet());
    }
}

