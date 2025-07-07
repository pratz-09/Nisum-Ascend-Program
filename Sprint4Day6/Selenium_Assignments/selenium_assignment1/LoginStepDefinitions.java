import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginStepDefinitions {
    WebDriver driver;

    @Given("User is on the login page")
    public void user_on_login_page() {
        driver = new ChromeDriver();
        driver.get("https://example.com/login");
    }

    @When("User enters valid username and password")
    public void user_enters_valid_credentials() {
        driver.findElement(By.id("username")).sendKeys("validUser");
        driver.findElement(By.id("password")).sendKeys("validPass");
    }

    @When("User clicks on login button")
    public void user_clicks_login() {
        driver.findElement(By.id("loginBtn")).click();
    }

    @Then("User should be redirected to the dashboard")
    public void user_redirected_dashboard() {
        assert driver.getCurrentUrl().contains("dashboard");
        driver.quit();
    }

    @When("User enters {string} and {string}")
    public void user_enters_invalid_credentials(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("Login error message should be displayed")
    public void login_error_displayed() {
        boolean errorDisplayed = driver.findElement(By.id("errorMsg")).isDisplayed();
        assert errorDisplayed;
        driver.quit();
    }
}
