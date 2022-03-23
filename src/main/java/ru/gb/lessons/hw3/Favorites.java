package ru.gb.lessons.hw3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Favorites {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().create();

        webDriver.get("https://www.respublica.ru/");

        webDriver.manage().window().setSize(new Dimension(1500, 1100));
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("//div/header/div[3]/div/div/div[2]/div[2]/div[2]/a")).click();



        WebElement authPopup = webDriver.findElement(By.xpath("//div/div[5]/div[2]/div/div[2]"));

        authPopup.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div[2]/div/div[2]/div/div/form/ul/li[1]/input")).sendKeys("lashankova@mail.ru");
        authPopup.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div[2]/div/div[2]/div/div/form/ul/li[2]/input")).sendKeys("123456");
        authPopup.findElement(By.xpath("//div/div[5]/div[2]/div/div[2]/div/div/form/ul/li[3]/button")).click();


        webDriver.findElement(By.xpath("//div/header/div[3]/div/div/div[1]/button[2]")).click();
        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//div/header/div[4]/div/div[1]/div/a[1]")))
                .build()
                .perform();

        webDriver.findElement(By.xpath("//div/header/div[4]/div/div[2]/div[1]/div/div[2]/a")).click();

        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class= 'item']"));
        WebElement selectedProduct = products.get(0);

        selectedProduct.findElement(By.xpath("//div[@class = 'wish-icon']")).click();

        Thread.sleep(10000);

        webDriver.findElement(By.xpath("//div/header/div[3]/div/div/div[2]/div[2]/div[3]")).click();

        Thread.sleep(10000);

        System.out.print("Actual products: ");
        webDriver.findElement(By.className("item"))
                .findElements(By.xpath("//div/main/div/div/div/div[3]/div/div[4]/a"))
                .forEach(product -> System.out.print(product.getText() + " "));


        webDriver.quit();

    }
}
