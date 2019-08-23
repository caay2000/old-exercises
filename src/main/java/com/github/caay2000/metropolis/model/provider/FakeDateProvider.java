package com.github.caay2000.metropolis.model.provider;

import java.util.Date;

public class FakeDateProvider implements DateProvider {

    private long epoch;

    public FakeDateProvider() {
        this.epoch = new Date().getTime();
    }

    @Override
    public long getEpoch() {
        return epoch;
    }

    @Override
    public void forward(long millis) {
        this.epoch += millis;
    }
}
