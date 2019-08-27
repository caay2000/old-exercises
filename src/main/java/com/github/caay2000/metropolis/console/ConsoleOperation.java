package com.github.caay2000.metropolis.console;

public class ConsoleOperation {

    private final Operation operation;
    private final String value;

    private ConsoleOperation(Operation operation, String value) {
        this.operation = operation;
        this.value = value;
    }

    public static ConsoleOperation aQuitOperation() {
        return new ConsoleOperation(Operation.QUIT, null);
    }

    public static ConsoleOperation aStartOperation(String value) {
        return new ConsoleOperation(Operation.START, value);
    }

    public static ConsoleOperation aRestartOperation() {
        return new ConsoleOperation(Operation.RESTART, null);
    }

    public static ConsoleOperation aStopOperation() {
        return new ConsoleOperation(Operation.STOP, null);
    }

    public static ConsoleOperation aReportOperation() {
        return new ConsoleOperation(Operation.REPORT, null);
    }

    public Operation getOperation() {
        return operation;
    }

    public String getValue() {
        return value;
    }

    public enum Operation {
        START,
        STOP,
        RESTART,
        REPORT,
        QUIT
    }
}
