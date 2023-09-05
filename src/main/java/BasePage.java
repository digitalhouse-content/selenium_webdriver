import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void url(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public void sendText(String imputText, By locator) {
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(imputText);
    }

    public void sendKey(CharSequence key, By locator) {
        this.findElement(locator).sendKeys(key);
    }

    public void click(By locator) {
        this.findElement(locator).click();
    }

    public String getText(By locator) {
        return this.findElement(locator).getText();
    }
}