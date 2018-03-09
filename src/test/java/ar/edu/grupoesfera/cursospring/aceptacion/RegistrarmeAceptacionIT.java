package ar.edu.grupoesfera.cursospring.aceptacion;


import org.junit.Test;
import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrarmeAceptacionIT extends TestDeAceptacion {

    private String obtenerContenidoRespuesta(String url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        return new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream())).readLine();
    }

    @Test
    public void registroExitoso() {

        String url = urlBase + "/nuevo-usuario";
        seleniumDriver.get(url);
        //System.out.println(seleniumDriver.getPageSource());

        seleniumDriver.findElement(By.id("email")).sendKeys("seba@seba.com");
        seleniumDriver.findElement(By.id("password")).sendKeys("1234");
        seleniumDriver.findElement(By.id("btn-registrarme")).click();

        //System.out.println(seleniumDriver.getPageSource());
        assertThat(seleniumDriver.getPageSource()).contains("validar-login");
    }

}

