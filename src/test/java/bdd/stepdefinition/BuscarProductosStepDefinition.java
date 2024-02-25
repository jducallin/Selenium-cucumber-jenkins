package bdd.stepdefinition;

import bdd.page.BuscarProductoPage;
import bdd.step.BuscarProductoStep;
import bdd.webdriver.DOM;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;

public class BuscarProductosStepDefinition {

    private final BuscarProductoStep buscarProductoStep = new BuscarProductoStep();

    private final DOM dom = new DOM();

    private Scenario scenario;

    @Before
    public void beforeScenario() {
        this.scenario = null;
    }

    @After
    public void afterScenario(){
        dom.quitDriver();
    }

    @Given("ingreso a la pagina web de mercado libre {string}")
    public void ingresoALaPaginaWebDeMercadoLibre(String url) {
        buscarProductoStep.stepIniciarNavegador(url);

    }

    @When("ingreso el texto {string}")
    public void ingresoElTexto(String txtProducto) {
        buscarProductoStep.stephacerClickenBuscar();
        buscarProductoStep.stepEscribirProducto(txtProducto);
    }

    @And("le doy click al boton buscar")
    public void leDoyClickAlBotonBuscar() {
        buscarProductoStep.stepClickBuscarProducto();
    }

    @Then("Valido que me muestre el valor {string}")
    public void validoQueMeMuestreElValor(String txtResultadoEsperado) {

        Assertions.assertEquals(txtResultadoEsperado, buscarProductoStep.stepValidarResultado());

    }
}
