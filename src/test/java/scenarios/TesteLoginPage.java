package scenarios;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.LoginPage;

public class TesteLoginPage {
    
    WebDriver driver;
    LoginPage loginPage;

    String urlBrfUat = "https://brfsbuat-brf.cs215.force.com/portalbrf/";
    String campoEmailVisivel = "//label[contains(text(),'Email')]";

    @Before
    public void openSetupBrowser() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(urlBrfUat);
    }

    @Test
    public void testeLogin() throws IOException {
        loginPage.loginPortal();
        Assert.assertTrue(driver.getPageSource().contains(campoEmailVisivel));
    }

    @After // Roda apos de todos os testes da classe
    public void closeBrowser() {
        driver.quit();
    }
}
