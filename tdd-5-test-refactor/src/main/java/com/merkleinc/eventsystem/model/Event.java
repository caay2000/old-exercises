package com.merkleinc.eventsystem.model;

import java.util.Date;

public class Event {

    private final String id;
    private final String type;
    private final Date eventTime;
    private final String engineerId;
    private final String visitNumber;


    private Date eta;
    private int estimatedTime;

    private String completeStatus;
    private String completeDescription;
    private String notDoneReason;

    private String organisation;

    public Event(String id, String type, Date eventTime, String engineerId, String visitNumber) {
        this.id = id;
        this.type = type;
        this.eventTime = eventTime;
        this.engineerId = engineerId;
        this.visitNumber = visitNumber;
    }

    public String getId() {
        return id;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public String getEngineerId() {
        return engineerId;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getCompleteDescription() {
        return completeDescription;
    }

    public void setCompleteDescription(String completeDescription) {
        this.completeDescription = completeDescription;
    }

    public String getNotDoneReason() {
        return notDoneReason;
    }

    public void setNotDoneReason(String notDoneReason) {
        this.notDoneReason = notDoneReason;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public boolean isAccept() {
        return "ACCEPT".equals(type);
    }

    public boolean isRoute() {
        return "ROUTE".equals(type);
    }

    public boolean isStart() {
        return "START".equals(type);
    }

    public boolean isComplete() {
        return "COMPLETE".equals(type);
    }

    public boolean isNotDone() {
        return "NOT_DONE".equals(type);
    }
}