package org.baseball.domain;

import org.baseball.infrastructure.ConsoleResultView;
import org.baseball.presentation.ResultView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class ResultViewTest {

    private final ResultView resultView = new ConsoleResultView();

    @Test
    @DisplayName("게임을 종료한다")
    void exit() {
        assertThatCode(resultView::exit)
                .doesNotThrowAnyException();
    }
}