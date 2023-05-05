package org.baseball.infrastructure;

import org.baseball.domain.Message;
import org.baseball.presentation.ResultView;

public class ConsoleResultView implements ResultView {

    @Override
    public void showResult(String result) {
        System.out.println(result);
    }

    @Override
    public void gameClear() {
        System.out.println(Message.GAME_CLEAR);
    }

    @Override
    public void exit() {
        System.out.println(Message.GAME_END);
    }

}