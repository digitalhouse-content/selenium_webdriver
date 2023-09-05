import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private By mail = By.id("email");
    private By password = By.id("password");
    private By login = By.className("btn-primario");
    private By hello = By.className("txt-hola");
    private By name = By.className("txt-nombre");
    private By mailReq = By.xpath("//small[@class='small-feedback']");
    private By passwordReq = By.xpath("//small[@class='small-feedback']");
    private By mailInv = By.xpath("//div[@class='form-group']//small[@class='small-feedback'][normalize-space()='El email es inválido']");
    private By passwordShort = By.xpath("//small[normalize-space()='La contraseña debe tener más de 6 caracteres']");
    private By credencials = By.className("form-feedback");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    public void escribirCorreo(String correo) throws InterruptedException{
        this.sendText(correo, mail);
    }

    public void escribirContraseña(String contraseña) throws InterruptedException{
        this.sendText(contraseña, password);
    }

    public void clickLogin() throws InterruptedException {
        this.click(login);
    }

    public String saludo() throws InterruptedException {
        System.out.println("MENSAJE DE SALUDO: " + this.getText(hello));
        return this.getText(hello);
    }

    public String loginNombre() throws InterruptedException {
        System.out.println("SE VERIFICA EL NOMBRE DEL LOGIN: " + this.getText(name));
        return this.getText(name);
    }

    public String correoObligatorio() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(mailReq));
        return this.getText(mailReq);
    }

    public String contraseñaObligatoria() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(passwordReq));
        return this.getText(passwordReq);
    }

    public String correoInvalido() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(mailInv));
        return this.getText(mailInv);
    }

    public String contraseñaCorta() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(passwordShort));
        return this.getText(passwordShort);
    }

    public String credencialesInvalidas() throws InterruptedException {
        System.out.println("MENSAJE DE ERROR: " + this.getText(credencials));
        return this.getText(credencials);
    }
}
