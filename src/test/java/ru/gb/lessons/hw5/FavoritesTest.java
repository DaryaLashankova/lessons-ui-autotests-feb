package ru.gb.lessons.hw5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FavoritesTest {

    @ParameterizedTest
    @ValueSource(strings = {"Тайные виды на гору Фудзи"})
    void favorites(String productName)  {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();

        webDriver.get("https://www.respublica.ru/");

        webDriver.manage().window().setSize(new Dimension(2000,1500));
        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


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

        new WebDriverWait(webDriver, 60,800).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='photo-wrapper']")));

        WebElement selectedProduct = products.get(0);


        selectedProduct.findElement(By.xpath("//div[@class = 'wish-icon']")).click();

        webDriver.findElement(By.xpath("//div/header/div[3]/div/div/div[2]/div[2]/div[3]")).click();

        new WebDriverWait(webDriver, 60,800).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='page']")));

        List<String> actualProductsInFavorites = webDriver.findElement(By.className("page"))
                .findElements(By.xpath("//div[@class='title']"))
                .stream()
                .map(product -> product.findElement(By.className("title-link")).getText())
                .collect(Collectors.toList());

        assertThat(actualProductsInFavorites).containsExactlyInAnyOrder(productName);

        webDriver.quit();
    }
}
