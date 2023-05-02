package org.baseball.infrastructure;

import org.baseball.domain.ConsoleMessage;
import org.baseball.domain.GameStatus;
import org.baseball.presentation.InputView;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleInputView implements InputView {
    private final BufferedReader br;

    public ConsoleInputView(BufferedReader br) {
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