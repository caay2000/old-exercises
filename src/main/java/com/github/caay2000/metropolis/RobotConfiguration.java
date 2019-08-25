package com.github.caay2000.metropolis;

import com.github.caay2000.metropolis.collector.DataMeter;
import com.github.caay2000.metropolis.reporter.Reporter;

public class RobotConfiguration {

    private static final double MAX_ROBOT_SPEED = 3d; // meters/second
    private static final double DISTANCE_DELTA = 0.5d; // meters

    private static final int DEFAULT_COLLECT_DATA_DISTANCE = 100; // meters
    private static final int DEFAULT_PUBLISH_REPORT_TIME = 15 * 60; // seconds

    private DataMeter dataMeter;
    private Reporter reporter;

    private double maxRobotSpeed;
    private double distanceDelta;
    private int collectDataDistance;
    private int publishReportTime;

    public RobotConfiguration(DataMeter dataMeter, Reporter reporter) {
        this.dataMeter = dataMeter;
        this.reporter = reporter;
        this.maxRobotSpeed = MAX_ROBOT_SPEED;
        this.distanceDelta = DISTANCE_DELTA;
        this.collectDataDistance = DEFAULT_COLLECT_DATA_DISTANCE;
        this.publishReportTime = DEFAULT_PUBLISH_REPORT_TIME;
    }

    public DataMeter getDataMeter() {
        return dataMeter;
    }

    public RobotConfiguration setDataMeter(DataMeter dataMeter) {
        this.dataMeter = dataMeter;
        return this;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public RobotConfiguration setReporter(Reporter reporter) {
        this.reporter = reporter;
        return this;
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
}
