package com.github.caay2000.metropolis;

import com.github.caay2000.metropolis.collector.DataMeter;

import java.io.PrintStream;

public class RobotConfiguration {

    private static final double MAX_ROBOT_SPEED = 3d; // meters/second
    private static final double DISTANCE_DELTA = 0.5d; // meters

    private static final int DEFAULT_COLLECT_DATA_DISTANCE = 100; // meters
    private static final int DEFAULT_PUBLISH_REPORT_TIME = 15 * 60; // seconds
    private static final int DEFAULT_DISTANCE_RANGE_STATION = 100; // meters

    private final DataMeter dataMeter;
    private final PrintStream output;

    private double maxRobotSpeed;
    private double distanceDelta;
    private int collectDataDistance;
    private int publishReportTime;
    private int distanceRangeStation;

    public RobotConfiguration(DataMeter dataMeter, PrintStream output) {
        this.dataMeter = dataMeter;
        this.output = output;
        this.maxRobotSpeed = MAX_ROBOT_SPEED;
        this.distanceDelta = DISTANCE_DELTA;
        this.collectDataDistance = DEFAULT_COLLECT_DATA_DISTANCE;
        this.publishReportTime = DEFAULT_PUBLISH_REPORT_TIME;
        this.distanceRangeStation = DEFAULT_DISTANCE_RANGE_STATION;
    }

    public DataMeter getDataMeter() {
        return dataMeter;
    }

    public PrintStream getOutput() {
        return output;
    }

    public double getMaxRobotSpeed() {
        return maxRobotSpeed;
    }

    public RobotConfiguration setMaxRobotSpeed(double maxRobotSpeed) {
        this.maxRobotSpeed = maxRobotSpeed;
        return this;
    }

    public double getDistanceDelta() {
        return distanceDelta;
    }

    public RobotConfiguration setDistanceDelta(double distanceDelta) {
        this.distanceDelta = distanceDelta;
        return this;
    }

    public int getCollectDataDistance() {
        return this.collectDataDistance;
    }

    public RobotConfiguration setCollectDataDistance(int collectDataDistance) {
        this.collectDataDistance = collectDataDistance;
        return this;
    }

    public int getPublishReportTime() {
        return this.publishReportTime;
    }

    public RobotConfiguration setPublishReportTime(int publishReportTime) {
        this.publishReportTime = publishReportTime;
        return this;
    }

    public int getDistanceRangeStation() {
        return distanceRangeStation;
    }

    public RobotConfiguration setDistanceRangeStation(int distanceRangeStation) {
        this.distanceRangeStation = distanceRangeStation;
        return this;
    }
}
