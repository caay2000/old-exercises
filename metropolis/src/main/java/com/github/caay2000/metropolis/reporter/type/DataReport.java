package com.github.caay2000.metropolis.reporter.type;

import com.github.caay2000.metropolis.reporter.Report;
import com.github.caay2000.metropolis.route.Position;

public class DataReport extends Report {

    private final Location location;
    private final String level;
    private final String source;

    public DataReport(long timestamp, Position position, String level, String source) {
        super((timestamp));
        this.location = new Location(position.getLat(), position.getLng());
        this.level = level;
        this.source = source;
    }

    public Location getLocation() {
        return location;
    }

    public String getLevel() {
        return level;
    }

    public String getSource() {
        return source;
    }
}

