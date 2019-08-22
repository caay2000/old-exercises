package com.github.caay2000.coffeemachine.mocks;

import java.util.ArrayList;
import java.util.List;
import com.github.caay2000.coffeemachine.hardware.Pad;

public class PadSpy extends Pad {

    private final List<String> messages = new ArrayList();

    @Override
    public void sendMessage(String message) {
        messages.add(message);
    }

    public boolean called() {
        return this.called(0);
    }

    public boolean called(int times) {
        return this.messages.size() == times;
    }

    public List<String> getMessages() {
        return messages;
    }
}
