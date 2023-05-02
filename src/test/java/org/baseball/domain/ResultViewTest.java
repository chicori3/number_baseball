package org.baseball.domain;

import org.baseball.infrastructure.ConsoleResultView;
import org.baseball.presentation.ResultView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ResultViewTest {

    private final ResultView resultView = new ConsoleResultView();

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("3 스트라이크가 아니라면 FAIL을 반환한다")
    void return_fail(int strikeCount) {
        BallStatus ballStatus = setBallStatus(strikeCount);

        GameStatus concluded = resultView.conclude(ballStatus);

        assertThat(concluded).isEqualTo(GameStatus.FAIL);
    }

    @Test
    @DisplayName("3 스트라이크라면 CLEAR를 반환한다")
    void return_clear() {
        BallStatus ballStatus = setBallStatus(3);

        GameStatus concluded = resultView.conclude(ballStatus);

        assertThat(concluded).isEqualTo(GameStatus.CLEAR);
    }

    @Test
    @DisplayName("게임을 종료한다")
    void exit() {
        assertThatCode(resultView::exit)
                .doesNotThrowAnyException();
    }

    private BallStatus setBallStatus(int strikeCount) {
        BallStatus ballStatus = new BallStatus(3);
        for (int i = 0; i < strikeCount; i++) {
            ballStatus.increaseStrikeCount();
        }

        return ballStatus;
    }
}