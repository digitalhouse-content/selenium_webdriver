import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void preconditions() throws InterruptedException{
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.setup();
        searchPage.url("http://testing.ctd.academy/");
    }
    @Test
    public void BusquedaExitosa_Uruguay() throws InterruptedException {
        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.escribirBusqueda("Punta del este");
        searchPage.clickBuscar();

        searchPage.resultadoBusqueda();
    }

    @Test
    public void BusquedaExitosa_Grecia() throws InterruptedException {
        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.escribirBusqueda("Paros");
        searchPage.clickBuscar();

        searchPage.resultadoBusqueda();
    }

    @AfterEach
    public void close() {
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.close();
    }
}