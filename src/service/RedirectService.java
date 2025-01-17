package service;

import java.awt.Desktop;
import java.net.URI;

public class RedirectService {
    private URLShortService shortenerService;

    public RedirectService(URLShortService shortenerService) {
        this.shortenerService = shortenerService;
    }

    public void redirect(String shortURL) {
        String originalURL = shortenerService.getOriginalURL(shortURL);
        if (originalURL == null) {
            System.out.println("Ссылка недоступна.");
            return;
        }
        try {
            Desktop.getDesktop().browse(new URI(originalURL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
