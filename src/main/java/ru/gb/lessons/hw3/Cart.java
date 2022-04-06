package ru.gb.lessons.hw3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;
//import java.util.stream.Stream;

public class Cart {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().create();

        webDriver.get("https://www.respublica.ru/");
        webDriver.manage().window().setSize(new Dimension(1500,1100));
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        webDriver.findElement(By.xpath("//div/header/div[3]/div/div/div[1]/button[2]")).click();
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//div/header/div[4]/div/div[1]/div/a[1]")))
                .build()
                .perform();

        webDriver.findElement(By.xpath("//div/header/div[4]/div/div[2]/div[1]/div/div[2]/a")).click();

       List<WebElement> products = webDriver.findElements(By.xpath("//div[@class= 'item']"));
        WebElement selectedProduct = products.get(0);


        selectedProduct.findElement(By.xpath("//button[@class = 'buy']")).click();


        Thread.sleep(10000);
        webDriver.findElement(By.xpath("//div/header/div[3]/div/div/div[2]/div[2]/div[4]")).click();
        Thread.sleep(10000);


        System.out.print("Actual products: ");
        webDriver.findElement(By.className("item-content"))
                        .findElements(By.xpath("//div/main/section/span/div/div[2]/div[1]/div[1]/span[1]/div/div[3]/div[1]/div[1]/a"))
                        .forEach(product -> System.out.print(product.getText() + " "));


        webDriver.quit();


    }
}
