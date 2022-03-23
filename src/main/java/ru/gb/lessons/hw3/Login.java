package ru.gb.lessons.hw3;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Login {
    public static void main( String[] args ) throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().create();

        webDriver.get("https://www.respublica.ru/");
        webDriver.manage().window().setSize(new Dimension(1500,1100));
        webDriver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//div/header/div[3]/div/div/div[2]/div[2]/div[2]/a")).click();



        //new WebDriverWait(webDriver,50,600).until(ExpectedConditions.presenceOfElementLocated(authPopupLocator));

        WebElement authPopup = webDriver.findElement(By.xpath("//div/div[5]/div[2]/div/div[2]"));

        authPopup.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div[2]/div/div[2]/div/div/form/ul/li[1]/input")).sendKeys("lashankova@mail.ru");
        authPopup.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div[2]/div/div[2]/div/div/form/ul/li[2]/input")).sendKeys("123456");
        authPopup.findElement(By.xpath("//div/div[5]/div[2]/div/div[2]/div/div/form/ul/li[3]/button")).click();

        Thread.sleep(100000);

    }
}
