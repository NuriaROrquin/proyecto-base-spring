package ar.edu.grupoesfera.cursospring.aceptacion;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrarmeAceptacionIT {

    private String port;
    private String urlBase;
    private WebDriver seleniumDriver;

    @Before
    public void setUp() throws Exception {
        port = System.getProperty("servlet.port", "8080");
        urlBase = "http://localhost:" + port + "/sitio";

        seleniumDriver = new PhantomJSDriver();
        seleniumDriver.manage().window().maximize();
        seleniumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        seleniumDriver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
    }

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
        System.out.println(seleniumDriver.getPageSource());

        seleniumDriver.findElement(By.id("email")).sendKeys("seba@seba.com");
        seleniumDriver.findElement(By.id("password")).sendKeys("1234");
        seleniumDriver.findElement(By.id("btn-registrarme")).click();

        //System.out.println(seleniumDriver.getPageSource());
        assertThat(seleniumDriver.getPageSource()).contains("validar-login");
    }

}

