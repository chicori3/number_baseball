package org.baseball.presentation;

import org.baseball.domain.BallStatus;
import org.baseball.domain.GameStatus;

public interface ResultView {

    GameStatus conclude(BallStatus ballStatus);

    void exit();
}
