package com.github.caay2000.metropolis.route;

public enum Station {

    //    BUCKINGHAM("Buckingham Palace", new Position(51.501299d, -0.141935d), true),
    BUCKINGHAM("Buckingham Palace", new Position(51.50155d, -0.14200d), true),
    TEMPLE_STATION("Temple Station", new Position(51.510852d, -0.114165d), true),
    TRAFALGAR_SQUARE("Trafalgar Square", new Position(51.50807d, -0.12808), true),
    WEST_LONDON("West London", new Position(51.50487d, -0.21533d), false),
    LONDON_CITY("London City", new Position(51.51161d, -0.08644d), false);

    private final String name;
    private final Position position;
    private final boolean instantReport;

    Station(String name, Position position, boolean instantReport) {

        this.name = name;
        this.position = position;
        this.instantReport = instantReport;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isInstantReport() {
        return instantReport;
    }
}
