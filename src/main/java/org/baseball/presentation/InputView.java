package org.baseball.presentation;

import org.baseball.domain.Result;

public interface InputView {

    int[] readUserInput();

    Result restart();
}
