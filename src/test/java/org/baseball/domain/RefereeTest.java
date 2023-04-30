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
    @DisplayName("STRIKE의 카운트는 3, BALL의 카운트는 0이어야 한다")
    void onlyStrike() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(1, 2, 3);

        referee.judge(computerBalls, userBalls);

        assertThat(referee.getStrikeCount()).isEqualTo(3);
        assertThat(referee.getBallCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("STRIKE의 카운트는 0, BALL의 카운트는 3이어야 한다")
    void onlyBall() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(2, 3, 1);

        referee.judge(computerBalls, userBalls);

        assertThat(referee.getStrikeCount()).isEqualTo(0);
        assertThat(referee.getBallCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("STRIKE의 카운트는 1, BALL의 카운트는 1이어야 한다")
    void oneStrike_oneBall() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(1, 3, 4);

        referee.judge(computerBalls, userBalls);

        assertThat(referee.getStrikeCount()).isEqualTo(1);
        assertThat(referee.getBallCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("STRIKE, BALL의 카운트가 없는 경우 isNothing이 true를 반환해야 한다")
    void nothing() {
        List<Integer> computerBalls = ballsGenerator.customBallsGenerate(1, 2, 3);
        List<Integer> userBalls = ballsGenerator.customBallsGenerate(4, 5, 6);

        referee.judge(computerBalls, userBalls);

        assertThat(referee.isNothing()).isTrue();
    }
}

class Referee {

    private BallStatus ballStatus;

    public void judge(List<Integer> computerBalls, List<Integer> userBalls) {
        calculateBallCount(computerBalls, userBalls);

        if (this.ballStatus.isNothing()) {
            return;
        }

        for (int i = 0; i < computerBalls.size(); i++) {
            if (computerBalls.get(i).equals(userBalls.get(i))) {
                ballStatus.increaseStrikeCount();
            }
        }
    }

    public int getStrikeCount() {
        return ballStatus.getStrikeCount();
    }

    public int getBallCount() {
        return ballStatus.getBallCount();
    }

    public boolean isNothing() {
        return ballStatus.isNothing();
    }

    private void calculateBallCount(List<Integer> computerBalls, List<Integer> userBalls) {
        this.ballStatus = new BallStatus((int) computerBalls.stream()
                .filter(userBalls::contains)
                .count());
    }
}

class BallStatus {
    private int strikeCount = 0;
    private int ballCount = 0;

    public BallStatus(int ballCount) {
        this.ballCount = ballCount;
    }

    public void increaseStrikeCount() {
        this.strikeCount++;
        this.ballCount--;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isNothing() {
        return this.strikeCount == 0 && this.ballCount == 0;
    }
}