package service;

import java.awt.Desktop;
import java.net.URI;

public class RedirectService {
    private URLShortService shortService;

    public RedirectService(URLShortService shortService) {
        this.shortService = shortService;
    }

    public void redirect(String shortURL) {
        String originalURL = shortService.getOriginalURL(shortURL);
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
