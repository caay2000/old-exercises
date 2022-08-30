package com.merkleinc.eventsystem;

import com.merkleinc.eventsystem.model.SkyEventNotification;

public class SkyEventNotifier {

    public void sendNotification(SkyEventNotification notification) {
        throw new RuntimeException("this shouldn't be called in test code");
    }
}
