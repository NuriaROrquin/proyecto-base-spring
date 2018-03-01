package ar.edu.grupoesfera.cursospring.aceptacion;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicoAceptacionIT {

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

	@Test
	public void smoke() throws Exception {
		URL url = new URL(urlBase + "/login");
		System.out.println(url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		assertThat(connection.getResponseCode()).isEqualTo(200);
	}

}
