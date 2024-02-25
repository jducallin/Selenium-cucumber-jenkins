import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class SimpleSeleniumMecardo {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("driver.chrome.webdriver", "C:\\chromeDriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        //Ruta donde se almacena las capturas de pantalla
        String screenshotDir = "C:\\Users\\José Angel\\Desktop\\proyectos\\Mi_Primer_Script_Web\\src\\test\\resources\\Evidencias";

        driver.get("https://www.mercadolibre.cl/");

        driver.manage().window().maximize();

        //Realiza acciones y toma captura de pantalla
        takeScreenshot(driver,screenshotDir, "screeshot1.png");

        Thread.sleep(3000);

        WebElement txtBuscar = driver.findElement(By.xpath("//input[@id='cb1-edit']"));
        WebElement btnBuscar = driver.findElement(By.xpath("//button[@type='submit']"));

        txtBuscar.click();

        txtBuscar.sendKeys("notebook");
        takeScreenshot(driver,screenshotDir, "screeshot2.png");
        btnBuscar.click();

        WebElement lblResultado = driver.findElement(By.xpath("//div[@class='ui-search-view-options__title']"));

        String ResultadoObtenido = lblResultado.getText();
        String ResultadoEsperado = "Ordenar por";

        Assertions.assertEquals(ResultadoEsperado,ResultadoObtenido);
        takeScreenshot(driver,screenshotDir, "screeshot3.png");
        driver.quit();
    }

    public static void takeScreenshot(WebDriver driver, String screenshotDir, String fileName) {
        // Toma la captura de pantalla
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define la ruta de almacenamiento
        String destinationPath = screenshotDir + File.separator + fileName; // Usar File.separator para independencia de plataforma

        // Copiar el archivo a la dirección
        try {
            FileUtils.copyFile(screenshotFile, new File(destinationPath));
            System.out.println("Captura de pantalla guardada con exito");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la captura de pantalla");
            throw new RuntimeException(e);
        }
    }

}
