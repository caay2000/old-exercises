package com.merkleinc.eventsystem.utlis;

import java.util.ArrayList;
import java.util.List;
import com.merkleinc.eventsystem.SkyEventNotifier;
import com.merkleinc.eventsystem.model.SkyEventNotification;

public class SkyEventNotifierSpy extends SkyEventNotifier {

    private final List<SkyEventNotification> notificationsProcessed;

    public SkyEventNotifierSpy() {
        notificationsProcessed = new ArrayList<>();
    }

    @Override
    public void sendNotification(SkyEventNotification notification) {
        notificationsProcessed.add(notification);
    }

    public boolean hasBeenCalled() {
        return notificationsProcessed.size() > 0;
    }

    public List<SkyEventNotification> getNotificationsProcessed() {
        return notificationsProcessed;
    }
}
