package org.baseball.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RefereeTest {

    private Referee referee;
    private BallsGenerator ballsGenerator = new BallsGenerator();

    @BeforeEach
    void setUp() {
        referee = new Referee();
    }

    @Test
    @DisplayName("3 스트라이크가 반환되어야 한다")
    void onlyStrike() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(1, 2, 3);

        String result = referee.judge(computerBalls, userBalls);

        assertThat(result).isEqualTo("3 스트라이크");
    }

    @Test
    @DisplayName("3 볼이 반환되어야 한다")
    void onlyBall() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(2, 3, 1);

        String result = referee.judge(computerBalls, userBalls);

        assertThat(result).isEqualTo("3 볼");
    }

    @Test
    @DisplayName("1 볼 1 스트라이크가 반환되어야 한다")
    void oneStrike_oneBall() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(1, 3, 4);

        String result = referee.judge(computerBalls, userBalls);

        assertThat(result).isEqualTo("1 볼 1 스트라이크");
    }

    @Test
    @DisplayName("낫싱이 반환되어야 한다")
    void nothing() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(4, 5, 6);

        String result = referee.judge(computerBalls, userBalls);

        assertThat(result).isEqualTo("낫싱");
    }
}



