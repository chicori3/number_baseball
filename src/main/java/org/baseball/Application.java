package org.baseball;

import org.baseball.infrastructure.ConsoleInputView;
import org.baseball.infrastructure.ConsoleResultView;
import org.baseball.presentation.GameClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) {
        GameClient gameClient = new GameClient(
                new ConsoleInputView(new BufferedReader(new InputStreamReader(System.in))),
                new ConsoleResultView());

        gameClient.play();
    }
}