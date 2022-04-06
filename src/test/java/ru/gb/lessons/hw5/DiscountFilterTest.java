package ru.gb.lessons.hw5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountFilterTest {
    @Test
    void priceFilter() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();

        webDriver.get("https://www.respublica.ru/knigi/bestsellery/tag/russkie");

        webDriver.manage().window().setSize(new Dimension(2000, 1500));
        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


        int quantityGoods1 = Integer.parseInt(webDriver.findElement(By.xpath("//div[@class ='page-count']")).getText().replaceAll("([^0-9]*)", ""));
        System.out.println(quantityGoods1);

        webDriver.findElement(By.xpath("//div/main/div/div[2]/aside/div/div[1]/div[3]/div[3]/div[2]/label")).click();
        webDriver.findElement(By.xpath("//button[@class ='filter-check-button']")).click();

        new WebDriverWait(webDriver, 60,800).until(ExpectedConditions.urlToBe("https://www.respublica.ru/knigi/bestsellery/tag/russkie?in_sale=on"));

        int quantityGoods2 = Integer.parseInt(webDriver.findElement(By.xpath("//div[@class ='page-count']")).getText().replaceAll("([^0-9]*)", ""));
        System.out.println(quantityGoods2);

        int size = webDriver.findElements(By.xpath("//div[@class ='item']")).size();
        System.out.println(size);

         assertThat(size).isEqualTo(quantityGoods2);

    }
}
