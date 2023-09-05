import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage {
    private By searchBox = By.id("ciudad");
    private By searchButton = By.className("btn-primario");
    private By searchOk = By.className("categoria");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void escribirBusqueda(String ciudad) {
        this.sendText(ciudad, searchBox);
        this.sendKey(Keys.ENTER, searchBox);
    }

    public void clickBuscar() {
        this.click(searchButton);
    }

    public String resultadoBusqueda() {
        System.out.println("RESULTADO DE LA BUSQUEDA: " + this.getText(searchOk));
        return this.getText(searchOk);
    }
}