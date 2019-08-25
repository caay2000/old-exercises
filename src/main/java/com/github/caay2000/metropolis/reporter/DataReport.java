package com.github.caay2000.metropolis.reporter;

import java.util.Objects;
import com.github.caay2000.metropolis.engine.Position;

public class DataReport implements Report {

    private final long timestamp;
    private final Location location;
    private final String level;
    private final String source;

    public DataReport(long timestamp, Position position, String level, String source) {
        this.timestamp = timestamp;
        this.location = new Location(position.getLat(), position.getLng());
        this.level = level;
        this.source = source;
    }

    public long getTimestamp() {
        return timestamp;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataReport report = (DataReport) o;
        return timestamp == report.timestamp &&
                Objects.equals(location, report.location) &&
                Objects.equals(level, report.level) &&
                Objects.equals(source, report.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, location, level, source);
    }
}

