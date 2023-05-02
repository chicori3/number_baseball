package org.baseball.presentation;

import org.baseball.domain.GameStatus;

public interface InputView {

    int[] readUserInput();

    GameStatus restart();
}
