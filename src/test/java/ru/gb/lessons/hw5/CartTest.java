package ru.gb.lessons.hw5;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CartTest  {


    @ParameterizedTest
    @ValueSource(strings = {"Тайные виды на гору Фудзи"})
     void cart(String productName)  {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().create();


        webDriver.get("https://www.respublica.ru/");

        webDriver.manage().window().setSize(new Dimension(2000,1500));
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


        webDriver.findElement(By.xpath("//div/header/div[3]/div/div/div[1]/button[2]")).click();
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//div/header/div[4]/div/div[1]/div/a[1]")))
                .build()
                .perform();

        webDriver.findElement(By.xpath("//div/header/div[4]/div/div[2]/div[1]/div/div[2]/a")).click();

        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class= 'item']"));
        WebElement selectedProduct = products.get(0);


        selectedProduct.findElement(By.xpath("//button[@class = 'buy']")).click();


       // Thread.sleep(10000);
        webDriver.findElement(By.xpath("//div/header/div[3]/div/div/div[2]/div[2]/div[4]")).click();
        //Thread.sleep(10000);


       List<String> actualProductsInCart = webDriver.findElement(By.className("item-content"))
                .findElements(By.xpath("//div[@class='item-name']"))
                .stream()
                .map(product -> product.findElement(By.className("item-name-link")).getText())
                .collect(Collectors.toList());

        assertThat(actualProductsInCart).containsExactlyInAnyOrder(productName);

        webDriver.quit();
    }
}
