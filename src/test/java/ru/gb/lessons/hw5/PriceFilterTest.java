package ru.gb.lessons.hw5;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import java.util.List;

public class PriceFilterTest {
     @Test
    void priceFilter(){
         ChromeOptions chromeOptions = new ChromeOptions();
         chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

         WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();

         webDriver.get("https://www.respublica.ru/knigi/bestsellery/tag/russkie");

         webDriver.manage().window().setSize(new Dimension(2000,1500));
         webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

         int startPrice = Integer.parseInt(webDriver.findElement(By.xpath("//div[@class='prices-value prices-value-min']/div[@class='prices-input']")).getText().replaceAll("([^0-9]*)", ""));
         System.out.println(startPrice);
         int toPrice = Integer.parseInt(webDriver.findElement(By.xpath("//div[@class='prices-value prices-value-max']/div[@class='prices-input']")).getText().replaceAll("([^0-9]*)", ""));
         System.out.println(toPrice);

         int quarter = (toPrice -startPrice)/4;

         List<WebElement> sliders = webDriver.findElements(By.xpath("//div[@class='vue-slider-dot']"));

         Actions actions = new Actions(webDriver);

         int width = sliders.get(0).getSize().width;
        Point slider1point = sliders.get(0).getLocation();
        Point slider2point = sliders.get(1).getLocation();

        int length = slider2point.getX() -slider1point.getX() + width;

        actions.dragAndDropBy(sliders.get(0), (length)/4, 0)
                .build()
                .perform();


        new WebDriverWait(webDriver,5).until(ExpectedConditions.textToBe( By.xpath("//div[@class='prices-value prices-value-min']/div[@class='prices-input']"), String.valueOf(startPrice + quarter + width/2)));


         actions.dragAndDropBy(sliders.get(1), -(length)/4, 0)
                 .build()
                 .perform();


         new WebDriverWait(webDriver,5).until(ExpectedConditions.textToBe( By.xpath("//div[@class='prices-value prices-value-max']/div[@class='prices-input']"), String.valueOf(toPrice - quarter - width/2 - sliders.size())));

     }
}
