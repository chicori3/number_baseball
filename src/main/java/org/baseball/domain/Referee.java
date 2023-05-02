package org.baseball.domain;

import java.util.List;

public class Referee {

    private BallStatus ballStatus;

    public String judge(List<Integer> computerBalls, List<Integer> userBalls) {
        calculateBallCount(computerBalls, userBalls);

        if (this.ballStatus.isNothing()) {
            return Message.NOTHING;
        }

        for (int index = 0; index < computerBalls.size(); index++) {
            ifStrikeThenIncrease(computerBalls, userBalls, index);
        }

        return this.ballStatus.getResult();
    }

    private void calculateBallCount(List<Integer> computerBalls, List<Integer> userBalls) {
        this.ballStatus = new BallStatus((int) computerBalls.stream()
                .filter(userBalls::contains)
                .count());
    }

    private void ifStrikeThenIncrease(List<Integer> computerBalls, List<Integer> userBalls, int index) {
        if (isStrike(computerBalls, userBalls, index)) {
            ballStatus.increaseStrikeCount();
        }
    }

    private boolean isStrike(List<Integer> computerBalls, List<Integer> userBalls, int index) {
        return computerBalls.get(index).equals(userBalls.get(index));
    }
}
