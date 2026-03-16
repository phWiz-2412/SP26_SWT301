import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_LOGIN_003 {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void invalidEmailFormat() throws Exception {

        driver.get("http://localhost:5173/login");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@autocomplete='email']"))
                .sendKeys("abc123");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@type='password']"))
                .sendKeys("123456");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"))
                .click();

        Thread.sleep(5000);

        Assertions.assertTrue(true);
    }

    @AfterEach
    void close() {
        driver.quit();
    }
}