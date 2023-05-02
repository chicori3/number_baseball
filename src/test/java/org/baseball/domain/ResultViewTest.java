package org.baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ResultViewTest {

    private ResultView resultView = new ResultView();
    private BallStatus ballStatus;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("3 스트라이크가 아니라면 FAIL을 반환한다")
    void return_fail(int strikeCount) {
        setBallStatus(strikeCount);

        GameStatus concluded = resultView.conclude(ballStatus);

        assertThat(concluded).isEqualTo(GameStatus.FAIL);
    }

    @Test
    @DisplayName("3 스트라이크라면 CLEAR를 반환한다")
    void return_clear() {
        setBallStatus(3);

        GameStatus concluded = resultView.conclude(ballStatus);

        assertThat(concluded).isEqualTo(GameStatus.CLEAR);
    }

    @Test
    @DisplayName("게임을 종료한다")
    void exit() {
        assertThatCode(() -> resultView.exit())
                .doesNotThrowAnyException();
    }


    private BallStatus setBallStatus(int strikeCount) {
        ballStatus = new BallStatus(3);
        for (int i = 0; i < strikeCount; i++) {
            ballStatus.increaseStrikeCount();
        }
        return ballStatus;
    }
}

class ResultView {
    public GameStatus conclude(BallStatus ballStatus) {
        return showResult(ballStatus);
    }

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
            restart();
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

    private void restart() {
        System.out.println(ConsoleMessage.GAME_RESTART);
    }

    private void end() {
        System.out.println(ConsoleMessage.GAME_END);
    }
}