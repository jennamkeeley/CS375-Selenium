import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Homework {
    public static void RunHomework() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Scrape Main Video Details? (Press enter)");
            scanner.nextLine();
            System.out.println(DisplayMainYoutubeVideoDetails());
        }
    }

    // HOMEWORK: Use Selenium and/or Jsoup to pull the url and title of the main youtube video on the page
    // HINT: You will need to click the button for the video to open so you can access the src attribute on the iframe that has the url
    // Both of these are implemented in a similar fashion in the methods defined above so use those as a guide
   static String DisplayMainYoutubeVideoDetails() {
        String url = "";
        String title = "";

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://acusports.com/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            WebElement youtubeSection = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("main-youtube-grid"))
            );

            WebElement titleElement = youtubeSection.findElement(
                By.cssSelector("h3[id^='main-youtube-grid-item-title']")
            );
            title = titleElement.getText();

            WebElement linkElement = youtubeSection.findElement(
                By.xpath("//a[contains(@href, 'youtube.com/watch')]")
            );
            url = linkElement.getAttribute("href");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return "Title: " + title + " Url: " + url;
    }
}
