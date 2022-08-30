package com.merkleinc.eventsystem.model;

import java.util.Date;
import java.util.Objects;

public class SkyEventNotification {

    private String type;
    private Date eventTime;
    private String engineerId;
    private String visitNumber;

    private int travelTime;
    private int estimatedTime;

    private String completionStatus;
    private String completionDescription;
    private String notDoneReason;

    private String engineerType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    public String getCompletionDescription() {
        return completionDescription;
    }

    public void setCompletionDescription(String completionDescription) {
        this.completionDescription = completionDescription;
    }

    public String getNotDoneReason() {
        return notDoneReason;
    }

    public void setNotDoneReason(String notDoneReason) {
        this.notDoneReason = notDoneReason;
    }

    public String getEngineerType() {
        return engineerType;
    }

    public void setEngineerType(String engineerType) {
        this.engineerType = engineerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SkyEventNotification that = (SkyEventNotification) o;
        return travelTime == that.travelTime &&
                Objects.equals(type, that.type) &&
                Objects.equals(eventTime, that.eventTime) &&
                Objects.equals(engineerId, that.engineerId) &&
                Objects.equals(visitNumber, that.visitNumber) &&
                Objects.equals(estimatedTime, that.estimatedTime) &&
                Objects.equals(completionStatus, that.completionStatus) &&
                Objects.equals(completionDescription, that.completionDescription) &&
                Objects.equals(notDoneReason, that.notDoneReason) &&
                Objects.equals(engineerType, that.engineerType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, eventTime, engineerId, visitNumber, travelTime, estimatedTime, completionStatus, completionDescription, notDoneReason, engineerType);
    }
}
