package org.baseball.presentation;

import org.baseball.domain.BallStatus;
import org.baseball.domain.BallsGenerator;
import org.baseball.domain.GameStatus;
import org.baseball.domain.Referee;

import java.util.List;

public class GameClient {
    private final InputView inputView;
    private final ResultView resultView;
    private final Referee referee = new Referee();
    private final BallsGenerator ballsGenerator = new BallsGenerator();
    private GameStatus gameStatus;


    public GameClient(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void play() {
        progress();

        if (isRestart()) {
            progress();
            return;
        }

        resultView.exit();
    }

    private void progress() {
        List<Integer> computerBalls = ballsGenerator.randomBallsGenerate();

        while (isClear()) {
            int[] userNumbers = inputView.readUserInput();
            BallStatus judgedBallStatus = referee.judge(computerBalls, ballsGenerator.customBallsGenerate(userNumbers));
            this.gameStatus = resultView.conclude(judgedBallStatus);
        }
    }

    private boolean isClear() {
        return gameStatus == GameStatus.CLEAR;
    }

    private boolean isRestart() {
        gameStatus = inputView.restart();
        return gameStatus == GameStatus.START;
    }
}