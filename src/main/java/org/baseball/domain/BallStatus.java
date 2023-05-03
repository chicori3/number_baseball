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

    public Result conclude() {
        StringBuilder sb = new StringBuilder();

        if (this.strikeCount == 3) {
            return Result.clear(allStrike(sb));
        }

        if (isNothing()) {
            return Result.fail(Message.NOTHING);
        }

        if (this.ballCount == 3) {
            return Result.fail(allBall(sb));
        }

        return Result.fail(notOnlyStrike(sb));
    }

    private String allStrike(StringBuilder sb) {
        return sb.append(3)
                .append(Message.STRIKE)
                .toString();
    }

    private String allBall(StringBuilder sb) {
        return sb.append(3)
                .append(Message.BALL)
                .toString();
    }

    private String notOnlyStrike(StringBuilder sb) {
        return sb.append(this.ballCount)
                .append(Message.BALL)
                .append(" ")
                .append(this.strikeCount)
                .append(Message.STRIKE)
                .toString();
    }

    public boolean isNothing() {
        return this.strikeCount == 0 && this.ballCount == 0;
    }
}
