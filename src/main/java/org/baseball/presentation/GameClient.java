package org.baseball.presentation;

import org.baseball.domain.BallsGenerator;
import org.baseball.domain.Referee;
import org.baseball.domain.Result;

import java.util.List;

public class GameClient {
    private final InputView inputView;
    private final ResultView resultView;
    private final Referee referee = new Referee();
    private final BallsGenerator ballsGenerator = new BallsGenerator();
    private Result result = Result.start();


    public GameClient(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void play() {
        do {
            progress();
        } while (!result.isEnd());

        resultView.exit();
    }

    private void progress() {
        List<Integer> computerBalls = ballsGenerator.randomBallsGenerate();

        while (!result.isClear()) {
            int[] userNumbers = inputView.readUserInput();
            result = referee.judge(computerBalls, ballsGenerator.customBallsGenerate(userNumbers));
            resultView.showResult(result.getMessage());
        }

        resultView.gameClear();
        result = inputView.restart();
    }
}