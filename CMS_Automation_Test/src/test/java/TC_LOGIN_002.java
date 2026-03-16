import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class TC_LOGIN_002 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    void loginWrongPassword() throws Exception {

        driver.get("http://localhost:5173/login");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@autocomplete='email']"))
                .sendKeys("testuser@yopmail.com");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@type='password']"))
                .sendKeys("123123");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"))
                .click();

        Thread.sleep(5000);

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Sai email hoặc mật khẩu')]")
                )
        );

        Assertions.assertTrue(error.isDisplayed());
    }

    @AfterEach
    void close() {
        driver.quit();
    }
}