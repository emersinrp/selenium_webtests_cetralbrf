package page;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    
    WebDriver driver;

    String HOMEBOTAOENTRARVISIVEL = "//section[1]//div[2]/div/div/small[1]/div[1]";
    String PREENCHEREMAIL = "//input[@id='email']";
    String PREENCHERSENHA = "//input[@id='pass']";
    String BOTAOENTRAR = "//button[contains(text(),'Entrar')]";
    String ESQUECIMINHASENHA = "//small[1]/div/ul/li/form/section[2]/a[1]";
    String BOTAOCADASTROCONTA = "//small[1]/div/ul/li/form/section[2]/a[2]";


    public LoginPage(WebDriver driver) { //metodo construtor

        this.driver = driver;
    }

    public void takeScreenshot(String nameScreenshot) throws IOException {
        Date data = new Date();
        TakesScreenshot screenshot = (TakesScreenshot) driver; // Convertendo o driver(navegador) em TakeScreenshot
        File arquivo = screenshot.getScreenshotAs(OutputType.FILE);// Tira o screenshot e transforma para file
        FileUtils.copyFile(arquivo, new File("output" + File.separator + nameScreenshot + data + "_screenshot.jpg"));
    }

    public void aguardaBotaoEntrar() {
        boolean btnEntrarVisivel = false;

        while (!btnEntrarVisivel) {
           try {
                driver.findElement(By.xpath(HOMEBOTAOENTRARVISIVEL)).click();
                btnEntrarVisivel = true;
            } catch (Exception e) {
                btnEntrarVisivel = false;
            }
        }
    }

    public void preencherEmail(String email) {

        driver.findElement(By.xpath(PREENCHEREMAIL)).sendKeys(email);
    }

    public void preencherSenha(String senha) {

        driver.findElement(By.xpath(PREENCHERSENHA)).sendKeys(senha);
    }

    public void clicaBotaoEntrar() {

        driver.findElement(By.xpath(BOTAOENTRAR)).click();
    }

    public void clicaEsqueciMinhaSenha() {

        driver.findElement((By.xpath(ESQUECIMINHASENHA))).click();
    }

    public void clicaBotaoCadastroConta() {

        driver.findElement(By.xpath(BOTAOCADASTROCONTA)).click();
    }

}
