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

    public String getResult() {
        StringBuilder sb = new StringBuilder();

        if (this.strikeCount == 3) {
            return allStrike(sb);
        }

        if (this.ballCount == 3) {
            return allBall(sb);
        }

        return notOnlyStrike(sb);
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


    public boolean hasStrikeCount() {
        return this.strikeCount > 0;
    }


    public boolean isNothing() {
        return this.strikeCount == 0 && this.ballCount == 0;
    }
}
