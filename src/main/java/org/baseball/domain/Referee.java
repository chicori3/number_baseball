package org.baseball.domain;

import java.util.List;

public class Referee {

    private BallStatus ballStatus;

    public Result judge(List<Integer> computerBalls, List<Integer> userBalls) {
        calculateBallCount(computerBalls, userBalls);
        calculateStrikeCount(computerBalls, userBalls);

        return this.ballStatus.conclude();
    }


    private void calculateBallCount(List<Integer> computerBalls, List<Integer> userBalls) {
        this.ballStatus = new BallStatus((int) computerBalls.stream()
                .filter(userBalls::contains)
                .count());
    }

    private void calculateStrikeCount(List<Integer> computerBalls, List<Integer> userBalls) {
        for (int index = 0; index < computerBalls.size(); index++) {
            increaseIfStrike(computerBalls, userBalls, index);
        }
    }

    private void increaseIfStrike(List<Integer> computerBalls, List<Integer> userBalls, int index) {
        if (isStrike(computerBalls, userBalls, index)) {
            ballStatus.increaseStrikeCount();
        }
    }

    private boolean isStrike(List<Integer> computerBalls, List<Integer> userBalls, int index) {
        return computerBalls.get(index).equals(userBalls.get(index));
    }
}
