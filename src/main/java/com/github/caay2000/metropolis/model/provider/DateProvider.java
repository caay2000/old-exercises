package com.github.caay2000.metropolis.model.provider;

public interface DateProvider {

    long getEpoch();

    void forward(long millis);
}
