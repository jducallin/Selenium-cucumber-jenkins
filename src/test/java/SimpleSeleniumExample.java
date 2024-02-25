import org.junit.jupiter.api.Assertions;
import org.junit.platform.commons.function.Try;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;

public class SimpleSeleniumExample {

    public static void main(String[] args){

        //Indicar la ubicación del contralados de Selenium
        System.setProperty("driver.chrome.driver","C:\\chromeDriver\\chromedriver.exe");

        //Inicializa el controlador
        WebDriver driver = new ChromeDriver();

        //Abre Url en el navegador
        driver.get("https://www.amazon.com/");

        //Maximiza el navegador
        driver.manage().window().maximize();

        //Espera un momento para ver que el navegador se ha abierto

        try {
           Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Buscar y almacernar elementos Web de una página
        WebElement txtBuscar = driver.findElement(By.id("twotabsearchtextbox"));
        WebElement btnBuscar = driver.findElement(By.id("nav-search-submit-button"));

        //Automatizando la pagina web de Amazon
        //Paso 1 Hacer click en la caja de texto para buscar
        txtBuscar.click();

        //Paso 2 Escribir el texto a buscar
        txtBuscar.sendKeys("Libro de selenium");

        //Paso 3 Click en el boton Buscar
        btnBuscar.click();

        //Almacenar el valor de la pagina web (Resultado Obtenido)
        WebElement lblResultadoObtenido = driver.findElement(By.xpath("//*[contains(text(),'Resultados')]"));
        String resultadoObtenido = lblResultadoObtenido.getText();
        String resultadoEsperado = "Resultados";

        //Validación de resultado esperado vs resultado obtenido

        Assertions.assertEquals(resultadoEsperado,resultadoObtenido);
        //cierra el navegador
        driver.quit();
    }

}
