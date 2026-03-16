import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class RegisterTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        // mở yopmail
        driver.get("https://yopmail.com/wm");

        Thread.sleep(5000);

        // lấy email
        WebElement emailElement = driver.findElement(By.className("bname"));
        String email = emailElement.getText();

        System.out.println("Generated email: " + email);

        // mở tab register
        driver.switchTo().newWindow(org.openqa.selenium.WindowType.TAB);
        driver.get("http://localhost:5173/register");

        Thread.sleep(3000);

        // nhập name
        WebElement name = driver.findElement(By.xpath("//input[@placeholder='John Doe']"));
        name.sendKeys("Test User");
        Thread.sleep(5000);

        // nhập email
        WebElement emailInput = driver.findElement(By.xpath("//input[@autocomplete='email']"));
        emailInput.sendKeys(email);
        Thread.sleep(5000);

        // nhập password
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("123456");
        Thread.sleep(5000);

        // click đăng ký
        WebElement registerBtn = driver.findElement(By.xpath("//button[contains(text(),'Đăng ký')]"));
        registerBtn.click();

        System.out.println("Register submitted");
        System.out.println("Please enter OTP manually...");

        // chờ sau khi verify OTP chuyển sang login
        wait.until(ExpectedConditions.urlContains("login"));

        System.out.println("OTP verified → start login");

        Thread.sleep(5000);

        // nhập email login
        WebElement loginEmail = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@autocomplete='email']")
                )
        );
        loginEmail.sendKeys(email);
        Thread.sleep(5000);

        // nhập password
        WebElement loginPassword = driver.findElement(By.xpath("//input[@type='password']"));
        loginPassword.sendKeys("123456");
        Thread.sleep(5000);

        // chờ button Sign in
        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Sign in')]")
                )
        );

        Thread.sleep(5000);

        // click login
        loginBtn.click();

        System.out.println("Login successful");

        // chờ 10s sau khi login
        Thread.sleep(10000);

        driver.quit();
    }
}