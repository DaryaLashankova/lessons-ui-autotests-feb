package ru.gb.lessons.hw5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {

    @Test
    void login() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().create();



        webDriver.get("https://www.respublica.ru/");
        webDriver.manage().window().setSize(new Dimension(1500,1100));
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//div/header/div[3]/div/div/div[2]/div[2]/div[2]/a")).click();

        WebElement authPopup = webDriver.findElement(By.xpath("//div/div[5]/div[2]/div/div[2]"));

        authPopup.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div[2]/div/div[2]/div/div/form/ul/li[1]/input")).sendKeys("lashankova@mail.ru");
        authPopup.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div[2]/div/div[2]/div/div/form/ul/li[2]/input")).sendKeys("123456");
        authPopup.findElement(By.xpath("//div/div[5]/div[2]/div/div[2]/div/div/form/ul/li[3]/button")).click();

    }

}
