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
    @DisplayName("3 스트라이크 테스트")
    void onlyStrike() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(1, 2, 3);

        Result result = referee.judge(computerBalls, userBalls);

        assertThat(result.getMessage()).isEqualTo("3스트라이크");
        assertThat(result.getStatus()).isEqualTo(Result.Status.CLEAR);
    }

    @Test
    @DisplayName("3 볼 테스트")
    void onlyBall() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(2, 3, 1);

        Result result = referee.judge(computerBalls, userBalls);

        assertThat(result.getMessage()).isEqualTo("3볼");
        assertThat(result.getStatus()).isEqualTo(Result.Status.FAIL);
    }

    @Test
    @DisplayName("1볼 1스트라이크 테스트")
    void oneStrike_oneBall() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(1, 3, 4);

        Result result = referee.judge(computerBalls, userBalls);

        assertThat(result.getMessage()).isEqualTo("1볼 1스트라이크");
        assertThat(result.getStatus()).isEqualTo(Result.Status.FAIL);
    }

    @Test
    @DisplayName("낫싱 테스트")
    void nothing() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(4, 5, 6);

        Result result = referee.judge(computerBalls, userBalls);

        assertThat(result.getMessage()).isEqualTo("낫싱");
        assertThat(result.getStatus()).isEqualTo(Result.Status.FAIL);
    }
}



