package org.baseball.domain;

public class BallStatus {
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

    public boolean hasStrikeCount() {
        return this.strikeCount > 0;
    }

    public boolean allStrike() {
        return this.strikeCount == 3;
    }

    public boolean isNothing() {
        return this.strikeCount == 0 && this.ballCount == 0;
    }
}
