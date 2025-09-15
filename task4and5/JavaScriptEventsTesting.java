package task45;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class JavaScriptEventsTesting {
    @Test
    public void main() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            driver.get("https://testpages.herokuapp.com/styled/events/javascript-events.html");


            List<WebElement> buttons = driver.findElements(By.cssSelector("button"));
            Actions actions = new Actions(driver);


            for (WebElement btn : buttons) {
                String id = btn.getAttribute("id");
                System.out.println("Button handling: " + id);

                switch (id) {
                    case "onclick":
                        btn.click();
                        break;
                    case "oncontextmenu":
                        actions.contextClick(btn).perform();
                        break;
                    case "ondoubleclick":
                        actions.doubleClick(btn).perform();
                        break;
                    case "onfocus":
                        btn.click();
                        break;
                    case "onblur":

                        btn.click();

                        driver.findElement(By.tagName("body")).click();
                        break;
                    case "onmouseover":
                        actions.moveToElement(btn).perform();
                        break;
                    case "onmouseleave":
                  actions.moveToElement(btn).perform();
                        actions.moveByOffset(100, 0).perform();
                        break;
                    case "onmousedown":
                        actions.clickAndHold(btn).perform();
                        actions.release().perform();
                        break;
                    case "onmouseup":
                        actions.clickAndHold(btn).perform();
                        actions.release().perform();
                        break;
                    case "onkeydown":
                    case "onkeypress":
                    case "onkeyup":
                        btn.sendKeys("a");
                        break;
                    default:
                        btn.click();
                        break;
                }


                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                String logSelector = "#log";
                WebElement log = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("log")));

                String logText = log.getText();
                System.out.println("Log after interacting with " + id + " => " + logText);

                if (logText.contains(id)) {
                    System.out.println(" " + id + " event fired");
                } else {
                    System.out.println("️ " + id + " event *not* fired!");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}