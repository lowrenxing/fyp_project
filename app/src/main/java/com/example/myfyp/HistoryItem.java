package com.example.myfyp;

public class HistoryItem {

    private String sessionId;
    private String bestPlatform;
    private String timestamp;

    public HistoryItem(
            String sessionId,
            String bestPlatform,
            String timestamp
    ) {
        this.sessionId = sessionId;
        this.bestPlatform = bestPlatform;
        this.timestamp = timestamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getBestPlatform() {
        return bestPlatform;
    }

    public String getTimestamp() {
        return timestamp;
    }
}