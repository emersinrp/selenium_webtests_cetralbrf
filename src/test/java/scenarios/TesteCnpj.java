package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.LoginPage;

import java.util.Random;

public class TesteCnpj {
    WebDriver driver;
    LoginPage loginPage;
    ChromeOptions options = new ChromeOptions();

    public static String[] cnpjValidosTeste() {

        String[] CNPJVALIDO = new String[5];

        CNPJVALIDO[0] = "31.381.360/0001-04";
        CNPJVALIDO[1] = "28.543.196/0001-80";
        CNPJVALIDO[2] = "32.015.020/0001-22";
        CNPJVALIDO[3] = "03.865.598/0001-70";
        CNPJVALIDO[4] = "07.564.104/0001-78";

        return CNPJVALIDO;

    }

    public static void main(String args[]) {
        int valorCnpj = new Random().nextInt(5);
        String cnpjsTeste = cnpjValidosTeste()[valorCnpj];
        System.out.println(cnpjsTeste);
    }


}
