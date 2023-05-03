package org.baseball.domain;

public class Result {

    private final String message;
    private final Status status;

    private Result(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public static Result start() {
        return new Result(null, Status.PLAYING);
    }

    public static Result clear(String message) {
        return new Result(message, Status.CLEAR);
    }

    public static Result fail(String message) {
        return new Result(message, Status.FAIL);
    }

    public static Result end(String message) {
        return new Result(message, Status.END);
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isClear() {
        return this.status == Status.CLEAR;
    }

    public boolean isEnd() {
        return this.status == Status.END;
    }

    enum Status {
        PLAYING, CLEAR, FAIL, END
    }
}