package task45;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
public class RobotClasswithNavigation {
    @Test
    public void main() throws AWTException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("https://the-internet.herokuapp.com/login");

            // 1. اكتب اسم المستخدم
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys("tomsmith");

            // 2. استخدم Robot للانتقال إلى حقل كلمة المرور
            Robot robot = new Robot();
            robot.setAutoDelay(300);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);

            // 3. أدخل كلمة المرور
            WebElement password = driver.switchTo().activeElement();
            password.sendKeys("SuperSecretPassword!");

            // 4. استبدال الضغط على Enter بالنقر على زر الدخول
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            // 5. انتظر الرسالة
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement flashMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("flash"))
            );

            String message = flashMessage.getText();
            if (message.contains("You logged into a secure area!")) {
                System.out.println("✅ Login successful.");
            } else {
                System.out.println("❌ Login failed. Message: " + message);
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}