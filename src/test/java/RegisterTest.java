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

public class RegisterTest {
    public WebDriver driver;
    public WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/Reportes_registro.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.url("http://testing.ctd.academy/");
    }

    @Test
    @Tag("REGISTRO")
    public void RegistroExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Exitoso");
        test.log(Status.INFO, "Comienza el Test");
        SearchPage searchPage = new SearchPage(driver, wait);
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickCrearCuenta();
            test.log(Status.PASS, "Ingreso en el formulario de Registro");

            registerPage.escribirNombre("Sergio");
            registerPage.escribirApellido("Pace");
            registerPage.escribirCorreo("prueba00095@gmail.com");
            registerPage.escribirContraseña("123456");
            registerPage.repetirContraseña("123456");
            test.log(Status.PASS, "Ingreso todos los datos del Registro");

            registerPage.clickRegistrarse();

            if (registerPage.cuentaCreadaGracias().equals("¡Cuenta registrada con éxito!")) {
                test.log(Status.PASS, "Validación de Registro Exitoso");
            } else {
                test.log(Status.FAIL, "Fallo el mensaje de Registro Exitoso");
            }

            if (registerPage.cuentaCreadaExito().equals("Te enviamos un email para confirmar tu cuenta")) {
                test.log(Status.PASS, "Validación de mensaje de mail por el Registro Exitoso");
            } else {
                test.log(Status.FAIL, "Fallo el mensaje de envio de mail luego del Registro Exitoso");
            }
        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepción durante la ejecución del test: " + error.getMessage());
        }
    }

    @Test
    @Tag("REGISTRO")
    public void RegistroFallidoMailRepetido() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido por correo ya usado");
        test.log(Status.INFO, "Comienza el Test");
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickCrearCuenta();
            test.log(Status.PASS, "Ingreso en el formulario de Registro");

            registerPage.escribirNombre("Sergio");
            registerPage.escribirApellido("Pace");
            registerPage.escribirCorreo("prueba00004@gmail.com");
            registerPage.escribirContraseña("123456");
            registerPage.repetirContraseña("123456");
            test.log(Status.PASS, "Ingreso todos los datos del Registro con un mail ya registrado");

            registerPage.clickRegistrarse();

            if (registerPage.mailRegistrado().equals("Ese email ya se encuentra registrado")) {
                test.log(Status.PASS, "Validación de mensaje de mail ya registrado");
            } else {
                test.log(Status.FAIL, "Fallo el mensaje de mail ya registrado");
            }
        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepción durante la ejecución del test: " + error.getMessage());
        }
    }

    @Test
    @Tag("REGISTRO")
    public void RegistroFallidoContraseña() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido por contraseñas que no coinciden");
        test.log(Status.INFO, "Comienza el Test");
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickCrearCuenta();
            test.log(Status.PASS, "Ingreso en el formulario de Registro");

            registerPage.escribirNombre("Sergio");
            registerPage.escribirApellido("Pace");
            registerPage.escribirCorreo("prueba00004@gmail.com");
            registerPage.escribirContraseña("123456321");
            registerPage.repetirContraseña("123456123");
            test.log(Status.PASS, "Ingreso todos los datos del Registro con contraseñas distintas");

            registerPage.clickRegistrarse();

            if (registerPage.contraseñaNoCoinciden().equals("Las contraseñas deben ser iguales")) {
                test.log(Status.PASS, "Validación del mensaje de que las contraseñas deben coincidir");
            } else {
                test.log(Status.FAIL, "Fallo el mensaje de que las contraseñas deben coincidir");
            }
        } catch (Exception error) {
            test.log(Status.FAIL, "Se produjo una excepción durante la ejecución del test: " + error.getMessage());
        }
    }

    @AfterEach
    public void cerrar() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
    }
}
