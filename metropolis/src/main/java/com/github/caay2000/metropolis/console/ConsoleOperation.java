package com.github.caay2000.metropolis.console;

public class ConsoleOperation {

    private final Operation operation;
    private final String value;

    private ConsoleOperation(Operation operation) {
        this.operation = operation;
        this.value = null;
    }

    private ConsoleOperation(Operation operation, String value) {
        this.operation = operation;
        this.value = value;
    }

    public static ConsoleOperation anExitOperation() {
        return new ConsoleOperation(Operation.EXIT);
    }

    public static ConsoleOperation aStartOperation(String value) {
        return new ConsoleOperation(Operation.START, value);
    }

    public static ConsoleOperation aRestartOperation() {
        return new ConsoleOperation(Operation.RESTART);
    }

    public static ConsoleOperation aStopOperation() {
        return new ConsoleOperation(Operation.STOP);
    }

    public static ConsoleOperation anExampleOperation() {
        return new ConsoleOperation(Operation.EXAMPLE);
    }

    public static ConsoleOperation aReportOperation() {
        return new ConsoleOperation(Operation.REPORT);
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
        EXAMPLE,
        EXIT
    }
}
