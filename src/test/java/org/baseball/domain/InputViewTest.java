package org.baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputViewTest {

    @Test
    @DisplayName("사용자 입력을 받는다.")
    void readUserInput() {
        BufferedReader br = setBufferedReader("123");
        InputView inputView = new InputView(br);

        int[] userInputNumbers = inputView.readUserInput();

        assertThat(userInputNumbers).isEqualTo(new int[]{1, 2, 3});
    }

    @Test
    @DisplayName("사용자가 1을 입력하면 GameStatus가 START여야 한다.")
    void restart() {
        BufferedReader br = setBufferedReader("1");
        InputView inputView = new InputView(br);

        GameStatus gameStatus = inputView.restart();

        assertThat(gameStatus).isEqualTo(GameStatus.START);
    }

    @Test
    @DisplayName("사용자가 2를 입력하면 GameStatus가 END여야 한다.")
    void end() {
        BufferedReader br = setBufferedReader("2");
        InputView inputView = new InputView(br);

        GameStatus gameStatus = inputView.restart();

        assertThat(gameStatus).isEqualTo(GameStatus.END);
    }

    @Test
    @DisplayName("사용자가 1 또는 2를 입력하지 않으면 IllegalArgumentException이 발생한다.")
    void wrongRestartInput() {
        BufferedReader br = setBufferedReader("3");
        InputView inputView = new InputView(br);

        assertThatThrownBy(() -> inputView.restart())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ConsoleMessage.WRONG_RESTART_INPUT);
    }

    private static BufferedReader setBufferedReader(String input) {
        return new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
    }
}

