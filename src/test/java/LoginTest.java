import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ExtentFactory;

import java.time.Duration;

public class LoginTest {
    public WebDriver driver;
    public WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reporte_Login.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void preconditions() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.setup();
        loginPage.url("http://testing.ctd.academy/login");
    }

    @Test
    @Tag("LOGIN")
    public void Login_Exitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Login Exitoso");
        test.log(Status.INFO, "Comienza nuestro test de login");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirCorreo("prueba365@gmail.com");
        loginPage.escribirContraseña("123456");
        test.log(Status.PASS, "Se cargaron los datos validos del Login");

        loginPage.clickLogin();

        Assertions.assertEquals(loginPage.saludo(), "Hola,");
        Assertions.assertEquals(loginPage.loginNombre(), "Sergio Pace");
        test.log(Status.PASS, "Se valida el Login Exitoso");
    }

    @Test
    @Tag("LOGIN")
    public void Login_DatosVacios() throws InterruptedException {
        ExtentTest test = extent.createTest("Intentar loguearse sin agregar ningun dato - Fallido");
        test.log(Status.INFO, "Comienza nuestro test de login");
        LoginPage loginPage = new LoginPage(driver, wait);

        //loginPage.escribirCorreo("");
        loginPage.escribirContraseña("");
        test.log(Status.PASS, "No se agregan datos del login");

        loginPage.clickLogin();
        loginPage.correoObligatorio();
        loginPage.contraseñaObligatoria();
        test.log(Status.PASS, "Se valida el mensaje de campos obligatorios");
    }

    @Test
    @Tag("LOGIN")
    public void Login_CorreoInvalido() throws InterruptedException {
        ExtentTest test = extent.createTest("Intentar loguearse con un correo invalido - Fallido");
        test.log(Status.INFO, "Comienza nuestro test de login");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirCorreo("prueba365");
        loginPage.escribirContraseña("123456");
        test.log(Status.PASS, "Se agregan los datos con un correo invalido");

        loginPage.clickLogin();
        loginPage.correoInvalido();
        test.log(Status.PASS, "Se valida el mensaje de correo invalido");
    }

    @Test
    @Tag("LOGIN")
    public void Login_ContraseñaCorta() throws InterruptedException {
        ExtentTest test = extent.createTest("Intentar loguearse con una contraseña menos a 6 caracteres - Fallido");
        test.log(Status.INFO, "Comienza nuestro test de login");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirCorreo("prueba365@gmail.com");
        loginPage.escribirContraseña("1234");
        test.log(Status.PASS, "Se agregan los datos con una contraseña menor a 6 caracteres");

        loginPage.clickLogin();
        loginPage.contraseñaCorta();
        test.log(Status.PASS, "Se valida el mensaje de su contraseña es menor a 6 caracteres");
    }

    @Test
    @Tag("LOGIN")
    public void Login_CredencialesInvalidas() throws InterruptedException {
        ExtentTest test = extent.createTest("Intentar loguearse con unas credenciales invalidas - Fallido");
        test.log(Status.INFO, "Comienza nuestro test de login");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirCorreo("prueba365@gmail.com");
        loginPage.escribirContraseña("1234789");
        test.log(Status.PASS, "Se agregan los datos con una contraseña erronea");

        loginPage.clickLogin();
        loginPage.credencialesInvalidas();
        test.log(Status.PASS, "Se valida el mensaje de sus credenciales son invalidas");
    }

    @AfterEach
    public void close() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }
}
