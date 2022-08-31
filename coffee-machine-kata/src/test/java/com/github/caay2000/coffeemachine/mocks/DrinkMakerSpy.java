package com.github.caay2000.coffeemachine.mocks;

import java.util.ArrayList;
import java.util.List;
import com.github.caay2000.coffeemachine.hardware.DrinkMaker;

public class DrinkMakerSpy extends DrinkMaker {

    private final List<String> commands = new ArrayList();

    @Override
    public void make(String command) {
        commands.add(command);
    }

    public boolean called() {
        return this.called(0);
    }

    public boolean called(int times) {
        return this.commands.size() == times;
    }

    public List<String> getCommands() {
        return commands;
    }
}