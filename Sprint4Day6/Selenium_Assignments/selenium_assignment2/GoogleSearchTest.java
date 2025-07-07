import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("BDD in Selenium");
        searchBox.submit();

        boolean resultsPresent = driver.findElements(By.cssSelector("h3")).size() > 0;
        System.out.println("Results appeared: " + resultsPresent);

        driver.quit();
    }
}
