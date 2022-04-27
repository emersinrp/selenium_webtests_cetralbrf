package page;

import java.util.Arrays;
import java.util.Random;

import com.github.javafaker.Faker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
    WebDriver driver;
    Faker faker = new Faker();
    String BUTTONQUEROSERCLIENTE = "//button[contains(text(),'Não, mas quero ser')]";
    String NOMECOMPLETO = "//form[3]/div[1]/input";
    String RAZAOSOCIAL = "//input[@id='companyname']";
    String CNPJ = "//input[@id='cnpj']";
    String SEGMENTO = "//*[@id='segment']/label/p";
    String OUTROSSEGMENTOS = "//input[@id='othersegments']";
    String CEP = "//input[@id='cep']";
    String ESTADO = "//*[@id='state']/label";
    String CELULAR = "//input[@id='phone-2']";
    String SEGUNDOCELULAR = "//input[@id='mobile']";
    String EMAIL = "//section[1]/div[1]/form[3]/div[9]/input[1]";
    String CONFIRMAEMAIL = "//section[1]/div[1]/form[3]/div[10]/input[1]";
    String BUTTONFINALIZACADASTRO = "//section[1]/div[1]/form[3]/section[1]/button[1]";
    String EMAILADDRESPREENCHIDO = faker.internet().emailAddress();
    String CELULARPREENCHIDO = faker.numerify("###########");
    String SEGUNDOCELULARPREENCHIDO = faker.numerify("###########");
    String BUTTONCADASTROOK = "//button[1][contains(text(),'OK, obrigado')]";

    public RegisterPage(WebDriver driver) {

        this.driver = driver;
    }

    public String[] cnpjValidosTeste() {
        String[] CNPJVALIDO = new String[5];

        CNPJVALIDO[0] = "31.381.360/0001-04";
        CNPJVALIDO[1] = "28.543.196/0001-80";
        CNPJVALIDO[2] = "32.015.020/0001-22";
        CNPJVALIDO[3] = "03.865.598/0001-70";
        CNPJVALIDO[4] = "07.564.104/0001-78";

        return CNPJVALIDO;
    }

    public void aguardaBotaoQueroSerCliente() {
        boolean btnQueroSerClienteVisivel = false;

        while (!btnQueroSerClienteVisivel) {
            try {
                driver.findElement(By.xpath(BUTTONQUEROSERCLIENTE)).click();
                btnQueroSerClienteVisivel = true;
            } catch (Exception e) {
                btnQueroSerClienteVisivel = false;
            }
        }
    }

    public void preencherNomeCompleto() {
        driver.findElement(By.xpath(NOMECOMPLETO)).sendKeys(faker.name().fullName());
    }

    public void preencherRazaoSocial() {

        driver.findElement(By.xpath(RAZAOSOCIAL)).sendKeys(faker.company().name());
    }

    public void preencherCnpj() {
        int valorCnpj = new Random().nextInt(5);
        String cnpjsTeste = cnpjValidosTeste()[valorCnpj];
        driver.findElement(By.xpath(CNPJ)).sendKeys(cnpjsTeste);
    }

    public void selecionarSegmento(String segmentoSelecionado) {
        driver.findElement(By.xpath(SEGMENTO)).click();
        String segmento = "//label[contains(text(),'"+ segmentoSelecionado +"')]";
        driver.findElement(By.xpath(segmento)).click();
    }

    public void preencherOutrosSegmentos() {
        driver.findElement(By.xpath(OUTROSSEGMENTOS)).sendKeys(faker.name().title());
    }

    public void preencherCep() {

        driver.findElement(By.xpath(CEP)).sendKeys("14090329");
    }

    public void selecionaEstado() {
        driver.findElement(By.xpath(ESTADO)).click();
        String estadoSP = "//*[@id='state']/ul/li[26]/label";
        driver.findElement(By.xpath(estadoSP)).click();
    }

    public void preencheCelular() {

        driver.findElement(By.xpath(CELULAR)).sendKeys(CELULARPREENCHIDO);
    }

    public void preencheSegundoCelular() {
        driver.findElement(By.xpath(SEGUNDOCELULAR)).sendKeys(SEGUNDOCELULARPREENCHIDO);
    }

    public void preenchePrimeiroEmail() {

        driver.findElement(By.xpath(EMAIL)).sendKeys(EMAILADDRESPREENCHIDO);
    }

    public void preencheSegundoEmail() {

        driver.findElement(By.xpath(CONFIRMAEMAIL)).sendKeys(EMAILADDRESPREENCHIDO);
    }

    public void clickButtonFinalizaCadastro() {

        driver.findElement(By.xpath(BUTTONFINALIZACADASTRO)).click();
    }

    public void clickButtonCadastroOk() {

        driver.findElement(By.xpath(BUTTONCADASTROOK)).click();
    }
}
