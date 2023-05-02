package org.baseball.domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class GameClient {
    private final InputView inputView = new InputView(new BufferedReader(new InputStreamReader(System.in)));
    private final ResultView resultView = new ResultView();
    private final Referee referee;
    private final BallsGenerator ballsGenerator;
    private GameStatus gameStatus;


    public GameClient(Referee referee, BallsGenerator ballsGenerator) {
        this.referee = referee;
        this.ballsGenerator = ballsGenerator;
    }

    public List<Integer> setUp() {
        return ballsGenerator.randomBallsGenerate();
    }

    public void play() {
        List<Integer> computerBalls = setUp();

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