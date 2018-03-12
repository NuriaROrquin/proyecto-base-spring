package ar.edu.grupoesfera.cursospring.aceptacion.cucumber;

import ar.edu.grupoesfera.cursospring.aceptacion.TestDeAceptacion;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrarmeStepDef extends TestDeAceptacion {

    String url = urlBase + "/nuevo-usuario";

    @Given("que ingreso el usuario (.*) con clave (.*)")
    public void ingresoUsuario(String usuario, String clave){
        seleniumDriver.get(url);
        seleniumDriver.findElement(By.id("email")).sendKeys(usuario);
        seleniumDriver.findElement(By.id("password")).sendKeys(clave);

    }

    @When("intento registrarme")
    public void registrarme(){
        seleniumDriver.findElement(By.id("btn-registrarme")).click();
    }

    @Then("el usuario se crea y me redirige a la vista (.*)")
    public void redirigeA(String vista){
        System.out.println(seleniumDriver.getPageSource());
        assertThat(seleniumDriver.getCurrentUrl()).contains(vista);
    }

}
