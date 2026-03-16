import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class TC_LOGIN_001 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    void loginSuccess() throws Exception {

        driver.get("http://localhost:5173/login");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@autocomplete='email']"))
                .sendKeys("testuser@yopmail.com");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@type='password']"))
                .sendKeys("123456");

        Thread.sleep(5000);

        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Sign in')]")
                )
        );

        loginBtn.click();

        Thread.sleep(10000);

        Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @AfterEach
    void close() {
        driver.quit();
    }
}