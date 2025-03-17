package com.ivrs.config;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private final Map<String , Instant> activeSessions = new ConcurrentHashMap<>();
    private static final Duration SESSION_TIMEOUT = Duration.ofMinutes(10);

    public void activateSession(String mobileNumber) {
        activeSessions.put(mobileNumber, Instant.now().plus(SESSION_TIMEOUT));
    }

    public boolean isSessionActive(String mobileNumber) {
        Instant expiry = activeSessions.get(mobileNumber);
        return expiry != null && Instant.now().isBefore(expiry);
    }

    public void removeSession(String mobileNumber) {
        activeSessions.remove(mobileNumber);
    }

}
