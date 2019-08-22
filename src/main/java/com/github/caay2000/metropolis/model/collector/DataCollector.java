package com.github.caay2000.metropolis.model.collector;

import com.github.caay2000.metropolis.model.Position;

public interface DataCollector {

    CollectedData collect(Position position);
}
