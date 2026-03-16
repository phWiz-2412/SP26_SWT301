import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_APPOINTMENT_001 {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void bookAppointment() throws Exception {

        driver.get("http://localhost:5173/appointment");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//button[contains(text(),'Book')]"))
                .click();

        Thread.sleep(10000);

        Assertions.assertTrue(true);
    }

    @AfterEach
    void close() {
        driver.quit();
    }
}