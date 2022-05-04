package scenarios;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.LoginPage;
import page.RegisterPage;

import java.util.concurrent.TimeUnit;

public class TestRegisterPage {

    WebDriver driver;
    LoginPage loginPage;
    RegisterPage registerPage;
    ChromeOptions options = new ChromeOptions();
    String urlBrfUatRegister = "https://brfsbuat-brf.cs215.force.com/portalbrf/cadastro";
    String msgRegisterSuccess = "//*[@id='swal2-title'][contains(text(),'Cadastro OK!')]";

    @Before
    public void openSetupBrowser() {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1920, 980));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);
        driver.get(urlBrfUatRegister);
    }
    @Test
    public void testRegisterSuccess() {
        registerPage.aguardaBotaoQueroSerCliente();
        registerPage.preencherNomeCompleto();
        registerPage.preencherRazaoSocial();
        registerPage.preencherCnpj();
        registerPage.selecionarSegmento("Rotisserie");
        registerPage.preencherOutrosSegmentos();
        registerPage.preencherCep();
        registerPage.selecionaEstado();
        registerPage.preencheCelular();
        registerPage.preencheSegundoCelular();
        registerPage.preenchePrimeiroEmail();
        registerPage.preencheSegundoEmail();
        registerPage.clickButtonFinalizaCadastro();
        validarMsgEsperada("Cadastro OK!", msgRegisterSuccess);
        registerPage.clickButtonCadastroOk();
    }
    public void validarMsgEsperada(String msgEsperada, String msgExibida) {
        Assert.assertEquals(msgEsperada, driver.findElement(By.xpath(msgExibida)).getText());
    }
    @After
    public void closeBrowser() {

        driver.quit();
    }

}
