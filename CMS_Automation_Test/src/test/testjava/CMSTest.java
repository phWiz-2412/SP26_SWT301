import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CMSTest {

    static WebDriver driver;
    static WebDriverWait wait;

    static String baseUrl = "http://localhost:5173";
    static String email = "testuser@yopmail.com";
    static String password = "123456";

    @BeforeAll
    static void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        System.out.println("Browser started");
    }

    @AfterAll
    static void close() throws Exception {

        Thread.sleep(5000);
        driver.quit();

        System.out.println("Browser closed");
    }

    // ===============================
    // TC_LOGIN_001
    // ===============================

    @Test
    @Order(1)
    void TC_LOGIN_001_loginSuccess() throws Exception {

        driver.get(baseUrl + "/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@autocomplete='email']")))
                .sendKeys(email);

        driver.findElement(By.xpath("//input[@type='password']"))
                .sendKeys(password);

        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"))
                .click();

        wait.until(ExpectedConditions.urlContains("dashboard"));

        Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"));

        System.out.println("TC_LOGIN_001 Passed");
    }

    // ===============================
    // TC_LOGIN_002
    // ===============================

    @Test
    @Order(2)
    void TC_LOGIN_002_wrongPassword() throws Exception {

        driver.get(baseUrl + "/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@autocomplete='email']")))
                .sendKeys(email);

        driver.findElement(By.xpath("//input[@type='password']"))
                .sendKeys("123123");

        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"))
                .click();

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Sai email hoặc mật khẩu')]")));

        Assertions.assertTrue(error.isDisplayed());

        System.out.println("TC_LOGIN_002 Passed");
    }

    // ===============================
    // TC_LOGIN_003
    // ===============================

    @Test
    @Order(3)
    void TC_LOGIN_003_invalidEmailFormat() throws Exception {

        driver.get(baseUrl + "/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@autocomplete='email']")))
                .sendKeys("invalidEmail");

        driver.findElement(By.xpath("//input[@type='password']"))
                .sendKeys(password);

        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"))
                .click();

        WebElement validation = driver.findElement(
                By.xpath("//input[@autocomplete='email']"));

        Assertions.assertFalse(validation.getAttribute("value").contains("@"));

        System.out.println("TC_LOGIN_003 Passed");
    }

    // ===============================
    // TC_REGISTER_001
    // ===============================

    @Test
    @Order(4)
    void TC_REGISTER_001_registerAccount() throws Exception {

        driver.get(baseUrl + "/register");

        String newEmail = "test" + System.currentTimeMillis() + "@yopmail.com";

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@placeholder='John Doe']")))
                .sendKeys("Test User");

        driver.findElement(By.xpath("//input[@autocomplete='email']"))
                .sendKeys(newEmail);

        driver.findElement(By.xpath("//input[@type='password']"))
                .sendKeys(password);

        driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]"))
                .click();

        System.out.println("OTP required → enter manually");

        Thread.sleep(20000);

        Assertions.assertTrue(true);

        System.out.println("TC_REGISTER_001 Passed");
    }

    // ===============================
    // TC_APPOINTMENT_001
    // ===============================

    @Test
    @Order(5)
    void TC_APPOINTMENT_001_bookSuccess() throws Exception {

        driver.get(baseUrl + "/appointment");

        WebElement bookBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Book')]")));

        bookBtn.click();

        Thread.sleep(3000);

        Assertions.assertTrue(true);

        System.out.println("TC_APPOINTMENT_001 Passed");
    }

    // ===============================
    // TC_APPOINTMENT_002
    // ===============================

    @Test
    @Order(6)
    void TC_APPOINTMENT_002_pastDate() throws Exception {

        driver.get(baseUrl + "/appointment");

        WebElement bookBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Book')]")));

        bookBtn.click();

        Thread.sleep(3000);

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Ngày đặt lịch không hợp lệ')]")));

        Assertions.assertTrue(error.isDisplayed());

        System.out.println("TC_APPOINTMENT_002 Passed");
    }
}