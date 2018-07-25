package ru.yandex.diabobkova;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;
import java.io.*;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class FirstTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setup() throws IOException {
        Properties property = new Properties();
        property.load(new FileReader("src/main/resources/application.properties"));

        String host = property.getProperty("pathToDriver");

        System.setProperty("webdriver.chrome.driver", host);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.e-katalog.ru/");
    }

    @Test
    public void checkOfCoast() {
        driver.findElement(By.id("ek-search")).sendKeys("Meizu MX5");
        driver.findElement(By.id("ek-search")).sendKeys(Keys.RETURN);
        WebElement MeizuMX5 = driver.findElement(By.id("product_608981"));
        MeizuMX5.sendKeys(Keys.RETURN);
        WebElement CoastOfMeizuMX5 = driver.findElement(By.id("price_608981"));
        CoastOfMeizuMX5.click();
        WebElement maxPrice = driver.findElement(By.cssSelector("span[itemprop='highPrice']"));
        Integer integerMaxPrice = Integer.valueOf(maxPrice.getText().replaceAll(" ", ""));
        assertThat(integerMaxPrice).isGreaterThan(14000);
    }

    //    @Test
//    public void userLogin() {
//        WebElement loginField = driver.findElement(By.name("Login"));
//        loginField.sendKeys("autotestorgua");
//        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
//        passwordField.sendKeys("testpass");
//        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Войти']"));
//        loginButton.click();
//        WebElement profileUser = driver.findElement(By.cssSelector(".login-button__user"));
//        String mailUser = profileUser.getText();
//        Assert.assertEquals("autotestorgua@ukr.net", mailUser);
//    }
//
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
