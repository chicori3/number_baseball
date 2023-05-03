package org.baseball.infrastructure;

import org.baseball.domain.Message;
import org.baseball.domain.Result;
import org.baseball.presentation.InputView;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleInputView implements InputView {
    private final BufferedReader br;

    public ConsoleInputView(BufferedReader br) {
        this.br = br;
    }

    @Override
    public int[] readUserInput() {
        System.out.println(Message.INPUT_NUMBER);
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

    @Override
    public Result restart() {
        System.out.println(Message.GAME_RESTART);
        try {
            return getUserRestart();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Result getUserRestart() throws IOException {
        String input = br.readLine();
        if (input.equals("1")) {
            return Result.start();
        }
        if (input.equals("2")) {
            return Result.end(Message.GAME_END);
        }
        throw new IllegalArgumentException(Message.WRONG_RESTART_INPUT);
    }
}