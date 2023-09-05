import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchTest {
    public WebDriver driver;

    @BeforeEach
    public void preconditions() throws InterruptedException{
        driver = new ChromeDriver();
        SearchPage searchPage = new SearchPage(driver);
        searchPage.setup();
        searchPage.url("http://testing.ctd.academy/");
    }
    @Test
    public void BusquedaExitosa_Uruguay() throws InterruptedException {
        SearchPage searchPage = new SearchPage(driver);

        searchPage.escribirBusqueda("Punta del este");
        searchPage.clickBuscar();

        searchPage.resultadoBusqueda();
    }

    @Test
    public void BusquedaExitosa_Grecia() throws InterruptedException {
        SearchPage searchPage = new SearchPage(driver);

        searchPage.escribirBusqueda("Paros");
        searchPage.clickBuscar();

        searchPage.resultadoBusqueda();
    }

    @AfterEach
    public void close() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.close();
    }
}