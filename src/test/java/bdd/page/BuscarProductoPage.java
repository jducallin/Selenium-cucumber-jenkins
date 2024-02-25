package bdd.page;

import bdd.webdriver.DOM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BuscarProductoPage extends DOM {

    @FindBy(xpath = "//input[@id='cb1-edit']")
    protected WebElement txtBuscar;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement btnBuscar;

    @FindBy(xpath = "//div[@class='ui-search-view-options__title']")
    protected WebElement resultadoObtenido;

    public void hacerClickenBuscar() {
        // Espera hasta que el elemento esté presente en el DOM
        WebDriverWait wait = new WebDriverWait(DOM.webDriver(), Duration.ofSeconds(30));
        WebElement txtBuscarElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='cb1-edit']")));

        // Hace clic en el elemento después de que esté presente
        DOM.onclick(txtBuscarElement);
    }

    public void escribirProducto(String txtProducto) {
        type(txtBuscar, txtProducto);
    }

    public void clickBuscarProducto () {
        onclick(btnBuscar);
    }

    public String validarResultado (){
        return getText(resultadoObtenido);

    }
}
