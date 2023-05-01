package org.baseball.domain;

import java.util.List;

public class Referee {

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
