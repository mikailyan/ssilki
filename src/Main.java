import service.URLShortService;
import model.User;
import service.RedirectService;

public class Main {
    public static void main(String[] args) {
        URLShortService shortenerService = new URLShortService();
        RedirectService redirectService = new RedirectService(shortenerService);

        User user = new User();
        System.out.println("UUID пользователя: " + user.getUuid());
        
        String shortURL = shortenerService.shortenURL("https://www.baeldung.com/java-9-http-client", user.getUuid(), 5);
        System.out.println("Короткая ссылка: " + shortURL);
        System.out.println("Попытки перехода по ссылке:");

        for (int i = 0; i < 6; i++) {
            System.out.println("Попытка " + (i + 1));
            redirectService.redirect(shortURL);
        }

        System.out.println("\nСтатистика ссылки");
        shortenerService.showLinkStats(shortURL);
    }
}
