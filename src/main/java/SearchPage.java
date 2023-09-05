import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {
    private By searchBox = By.id("ciudad");
    private By searchButton = By.className("btn-primario");
    private By searchOk = By.className("categoria");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void escribirBusqueda(String ciudad) throws InterruptedException{
        this.sendText(ciudad, searchBox);
        this.sendKey(Keys.ENTER, searchBox);
    }

    public void clickBuscar() throws InterruptedException {
        this.click(searchButton);
    }

    public String resultadoBusqueda() throws InterruptedException {
        System.out.println("RESULTADO DE LA BUSQUEDA: " + this.getText(searchOk));
        return this.getText(searchOk);
    }
}