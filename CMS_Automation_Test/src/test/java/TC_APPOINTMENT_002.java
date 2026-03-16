import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_APPOINTMENT_002 {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void invalidAppointmentDate() throws Exception {

        driver.get("http://localhost:5173/appointment");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//button[contains(text(),'Book')]"))
                .click();

        Thread.sleep(5000);

        WebElement error = driver.findElement(
                By.xpath("//*[contains(text(),'Ngày đặt lịch không hợp lệ')]")
        );

        Assertions.assertTrue(error.isDisplayed());
    }

    @AfterEach
    void close() {
        driver.quit();
    }
}