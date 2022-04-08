package page;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class LoginPage {
    
    WebDriver driver;

    String BOTAOENTRARVISIVEL = "//body/div[1]/section[1]/nav[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/small[1]/a[1]";

    public LoginPage(WebDriver driverVindoDoTeste) { //metodo contrutor
        this.driver = driverVindoDoTeste;
    }

    public void takeScreenshot(String nameScreenshot) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver; // Convertendo o driver(navegador) em TakeScreenshot
        File arquivo = screenshot.getScreenshotAs(OutputType.FILE);// Tira o screenshot e transforma para file
        FileUtils.copyFile(arquivo, new File("output" + File.separator + nameScreenshot + "_screenshot.jpg"));
    }

    public void clicarEntrar() {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(10))
                .ignoring(ElementClickInterceptedException.class);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(BOTAOENTRARVISIVEL))); 
        driver.findElement(By.xpath(BOTAOENTRARVISIVEL)).click();
    }

    public void loginPortal() throws IOException {
        clicarEntrar();
        takeScreenshot("LoginSucesso");
    }

}
