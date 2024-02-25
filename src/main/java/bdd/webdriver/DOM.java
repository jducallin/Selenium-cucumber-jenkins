package bdd.webdriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

//Document Object Model
public class DOM {

    static String screenshotDir = "C:\\proyectos\\Mi_Primer_Script_Web\\src\\test\\resources\\Evidencias";

    private static WebDriver driver; // Declarar la variable estática

    public static WebDriver webDriver() {
        if (driver == null) {
            String chromeDriverPath = "C:\\chromeDriver\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath); // Corregir la propiedad del sistema
            driver = new ChromeDriver();
        }
        return driver;
    }

    public DOM(){

        PageFactory.initElements(webDriver(), this);
    }

    public static void iniciarNavegador (String url){
        webDriver().get(url);
        webDriver().manage().window().maximize();
    }

    public static void takeScreenshot(String fileName) {
        // Toma la captura de pantalla
        File screenshotFile = ((TakesScreenshot) webDriver()).getScreenshotAs(OutputType.FILE);

        // Define la ruta de almacenamiento
        String destinationPath = screenshotDir + fileName + ".png"; // Usar File.separator para independencia de plataforma

        // Copiar el archivo a la dirección
        try {
            FileUtils.copyFile(screenshotFile, new File(destinationPath));
            System.out.println("Captura de pantalla guardada con exito");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la captura de pantalla");
            throw new RuntimeException(e);
        }
    }

    public static void onclick(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        element.sendKeys(text);
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public void quitDriver(){
        webDriver().quit();
    }
}
