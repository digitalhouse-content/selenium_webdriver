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
    public void Login_Exitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Login Exitoso");
        test.log(Status.INFO, "Comienza nuestro test de login");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.escribirCorreo("prueba365@gmail.com");
        loginPage.escribirContraseña("123456");
        test.log(Status.PASS, "Se cargaron los datos validos del Login");

        loginPage.clickLogin();
        loginPage.saludo();
        loginPage.loginNombre();
        test.log(Status.PASS, "Se valida el Login Exitoso");
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
