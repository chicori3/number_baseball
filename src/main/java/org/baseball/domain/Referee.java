package org.baseball.domain;

import java.util.List;

public class Referee {

    private BallStatus ballStatus;

    public BallStatus judge(List<Integer> computerBalls, List<Integer> userBalls) {
        calculateBallCount(computerBalls, userBalls);

        if (this.ballStatus.isNothing()) {
            return this.ballStatus;
        }

        for (int index = 0; index < computerBalls.size(); index++) {
            isStrike(computerBalls, userBalls, index);
        }

        return this.ballStatus;
    }

    private void calculateBallCount(List<Integer> computerBalls, List<Integer> userBalls) {
        this.ballStatus = new BallStatus((int) computerBalls.stream()
                .filter(userBalls::contains)
                .count());
    }

    private void isStrike(List<Integer> computerBalls, List<Integer> userBalls, int index) {
        if (computerBalls.get(index).equals(userBalls.get(index))) {
            ballStatus.increaseStrikeCount();
        }
    }
}
