package model;

import java.time.LocalDateTime;

public class Link {
    private String shortURL;
    private String originalURL;
    private String userUUID;
    private int clickLimit;
    private int clicks;
    private LocalDateTime expirationDate;

    public Link(String originalURL, String userUUID, int clickLimit) {
        this.originalURL = originalURL;
        this.userUUID = userUUID;
        this.clickLimit = clickLimit;
        this.clicks = 0;
        this.expirationDate = LocalDateTime.now().plusDays(1); // Срок действия — сутки
        this.shortURL = generateShortURL();
    }

    private String generateShortURL() {
        return Integer.toHexString((originalURL + userUUID).hashCode()).substring(0, 8);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

    public boolean canClick() {
        return clicks < clickLimit;
    }

    public void incrementClicks() {
        this.clicks++;
    }

    public String getShortURL() {
        return shortURL;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public int getClicks() {
        return clicks;
    }

    public int getClickLimit() {
        return clickLimit;
    }
}
