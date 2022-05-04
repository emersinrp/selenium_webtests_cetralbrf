package scenarios;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class TestLoginPage {
    
    WebDriver driver;
    LoginPage loginPage;
    ChromeOptions options = new ChromeOptions();

    String emailBruna = "bruna.moraes@brf.com";
    String senhaBruna = "teste123";
    
    String urlBrfUat = "https://brfsbuat-brf.cs215.force.com/portalbrf/";
    String msgBoasVindas = "//small[contains(text(),'Bem vindo, Bruna')]";
    String msgEmailNaoCadastrado = "Este email não está cadastrado. Cadastre-se clicando no botão “Criar Conta”.";
    String caminhoEmailNaoCadastrado = "//div[contains(text(),'Este email não está cadastrado.')]";
    String msgSenhaIncorreta = "Senha incorreta. Tente novamente ou clique em “Esqueceu a senha?” para redefini-la.";
    String caminhoSenhaIncorreta = "//div[contains(text(),'Senha incorreta. Tente novamente ou clique em “Esqueceu a senha?” para redefini-la.')]";
    
    @Before
    public void openSetupBrowser() {
        options.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1920, 980));
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);
        driver.get(urlBrfUat);
    }
    @Test
    public void testLoginSucesso() throws IOException {
        loginPage.aguardaBotaoEntrar();
        loginPage.preencherEmail(emailBruna);
        loginPage.preencherSenha(senhaBruna);
        loginPage.clicaBotaoEntrar();
        validarMsgEsperada("Bem vindo, Bruna", msgBoasVindas);
        loginPage.takeScreenshot("LoginSucesso");
    }
    @Test
    public void testLoginSemPreenchimento() throws IOException {
        loginPage.aguardaBotaoEntrar();
        loginPage.clicaBotaoEntrar();
        validarMsgEsperada(msgEmailNaoCadastrado, caminhoEmailNaoCadastrado);
        loginPage.takeScreenshot("LoginSemPreenchimento");
    }
    @Test
    public void testLoginSemEmail() throws IOException {
        loginPage.aguardaBotaoEntrar();
        loginPage.preencherSenha(senhaBruna);
        loginPage.clicaBotaoEntrar();
        validarMsgEsperada(msgEmailNaoCadastrado, caminhoEmailNaoCadastrado);
        loginPage.takeScreenshot("LoginSemEmail");
    }
    @Test
    public void testLoginSemSenha() throws IOException {
        loginPage.aguardaBotaoEntrar();
        loginPage.preencherEmail(emailBruna);
        loginPage.clicaBotaoEntrar();
        validarMsgContida("Oops...");
        loginPage.takeScreenshot("LoginSemSenha");
    }
    @Test
    public void testLoginEmailIncorreto() throws IOException {
        loginPage.aguardaBotaoEntrar();
        loginPage.preencherEmail("teste171@teste.com");
        loginPage.preencherSenha(senhaBruna);
        loginPage.clicaBotaoEntrar();
        validarMsgEsperada(msgEmailNaoCadastrado, caminhoEmailNaoCadastrado);
        loginPage.takeScreenshot("LoginEmailIncorreto");
    }
    @Test
    public void testLoginSenhaIncorreta() throws IOException {
        loginPage.aguardaBotaoEntrar();
        loginPage.preencherEmail(emailBruna);
        loginPage.preencherSenha("123456");
        loginPage.clicaBotaoEntrar();
        validarMsgEsperada(msgSenhaIncorreta, caminhoSenhaIncorreta);
        loginPage.takeScreenshot("LoginSenhaIncorreta");
    }
    @Test
    public void testClickEsqueciMinhaSenha() throws IOException {
        loginPage.aguardaBotaoEntrar();
        loginPage.clicaEsqueciMinhaSenha();
        validarUrl("/esqueci-minha-senha");
        loginPage.takeScreenshot("ClickEsqueciMinhaSenha");
    }
    @Test
    public void testClickCadastroConta() throws IOException {
        loginPage.aguardaBotaoEntrar();
        loginPage.clicaBotaoCadastroConta();
        validarUrl("/cadastro");
        loginPage.takeScreenshot("ClickCadastroConta");
    }

    public void validarMsgContida(String mensagem) {
        Assert.assertTrue(driver.getPageSource().contains(mensagem));
    }
    public void validarUrl(String enderecoUrl) {
        Assert.assertTrue(driver.getCurrentUrl().contains(enderecoUrl));
    }
    public void validarMsgEsperada(String msgEsperada, String msgExibida) {
        Assert.assertEquals(msgEsperada, driver.findElement(By.xpath(msgExibida)).getText());
    }

    @After
    public void closeBrowser() {

        driver.quit();
    }
}
