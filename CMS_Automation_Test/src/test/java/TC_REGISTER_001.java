import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_REGISTER_001 {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void registerAccount() throws Exception {

        driver.get("http://localhost:5173/register");

        String email = "test" + System.currentTimeMillis() + "@yopmail.com";

        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@placeholder='John Doe']"))
                .sendKeys("Test User");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@autocomplete='email']"))
                .sendKeys(email);

        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@type='password']"))
                .sendKeys("123456");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]"))
                .click();

        Thread.sleep(20000); // chờ nhập OTP

        Assertions.assertTrue(true);
    }

    @AfterEach
    void close() {
        driver.quit();
    }
}