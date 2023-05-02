package org.baseball.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
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

class InputView {
    private final BufferedReader br;

    public InputView(BufferedReader br) {
        this.br = br;
    }

    public int[] readUserInput() {
        System.out.println(ConsoleMessage.INPUT_NUMBER);
        try {
            return getUserNumbers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int[] getUserNumbers() throws IOException {
        char[] charArray = br.readLine().toCharArray();
        int[] userNumbers = new int[charArray.length];

        for (int index = 0; index < charArray.length; index++) {
            userNumbers[index] = charArray[index] - '0';
        }

        return userNumbers;
    }

    public GameStatus restart() {
        System.out.println(ConsoleMessage.GAME_RESTART);
        try {
            return getUserRestart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private GameStatus getUserRestart() throws IOException {
        String input = br.readLine();
        if (input.equals("1")) {
            return GameStatus.START;
        }
        if (input.equals("2")) {
            return GameStatus.END;
        }
        throw new IllegalArgumentException(ConsoleMessage.WRONG_RESTART_INPUT);
    }
}