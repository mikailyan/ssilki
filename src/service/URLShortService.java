package service;

import java.util.HashMap;
import java.util.Map;

import model.Link;

public class URLShortService {
    private Map<String, Link> links = new HashMap<>();

    public String shortenURL(String originalURL, String userUUID, int clickLimit) {
        Link link = new Link(originalURL, userUUID, clickLimit);
        links.put(link.getShortURL(), link);
        return link.getShortURL();
    }

    public String getOriginalURL(String shortURL) {
        Link link = links.get(shortURL);
        if (link.isExpired()) {
            System.out.println("Срок действия ссылки истек.");
            return null;
        }
        if (!link.canClick()) {
            System.out.println("Лимит переходов по ссылке исчерпан.");
            return null;
        }
        link.incrementClicks();
        return link.getOriginalURL();
    }

    public void showLinkStats(String shortURL) {
        Link link = links.get(shortURL);
        System.out.println("Оригинальная ссылка: " + link.getOriginalURL());
        System.out.println("Количество переходов: " + link.getClicks() + "/" + link.getClickLimit());
    }
}
