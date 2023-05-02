package org.baseball.infrastructure;

import org.baseball.domain.BallStatus;
import org.baseball.domain.ConsoleMessage;
import org.baseball.domain.GameStatus;
import org.baseball.presentation.ResultView;

public class ConsoleResultView implements ResultView {
    public GameStatus conclude(BallStatus ballStatus) {
        return showResult(ballStatus);
    }

    @Override
    public void exit() {
        end();
    }

    private GameStatus showResult(BallStatus ballStatus) {
        if (ballStatus.isNothing()) {
            nothing();
            return GameStatus.FAIL;
        }

        String result = buildResult(ballStatus);
        System.out.println(result);

        if (ballStatus.allStrike()) {
            gameClear();
            return GameStatus.CLEAR;
        }

        return GameStatus.FAIL;
    }

    private void nothing() {
        System.out.println(ConsoleMessage.NOTHING);
    }

    private String buildResult(BallStatus ballStatus) {
        StringBuilder sb = new StringBuilder();

        sb.append(ballStatus.getBallCount())
                .append(ConsoleMessage.BALL);

        if (ballStatus.hasStrikeCount()) {
            sb.append(" ")
                    .append(ballStatus.getStrikeCount())
                    .append(ConsoleMessage.STRIKE);
        }

        return sb.toString();
    }

    private void gameClear() {
        System.out.println(ConsoleMessage.GAME_CLEAR);
    }

    private void end() {
        System.out.println(ConsoleMessage.GAME_END);
    }
}