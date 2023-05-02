package org.baseball.presentation;

import org.baseball.domain.*;

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
        List<Integer> computerBalls = ballsGenerator.randomBallsGenerate();

        while (gameStatus != GameStatus.CLEAR) {
            int[] userNumbers = inputView.readUserInput();
            BallStatus judgedBallStatus = referee.judge(computerBalls, ballsGenerator.customBallsGenerate(userNumbers));
            this.gameStatus = resultView.conclude(judgedBallStatus);
        }

        if (gameStatus == GameStatus.CLEAR) {
            gameStatus = inputView.restart();
        }

        if (gameStatus == GameStatus.END) {
            resultView.exit();
            return;
        }

        play();
    }
}