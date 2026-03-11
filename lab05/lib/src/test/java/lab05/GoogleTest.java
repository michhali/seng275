package lab05;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleTest {

    WebDriver browser;

    @BeforeEach
    public void setUp() {
        // macOS ChromeDriver path (relative to lab05 root)
        String chromeDriverPath = System.getProperty("user.dir")
                + "/../drivers/chromedriver-mac/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        browser = new ChromeDriver(options);
        browser.manage().window().maximize();
    }

    @AfterEach
    public void cleanUp() {
        if (browser != null) {
            browser.quit();
        }
    }

    @Test
    public void googlePageLoads() {
        browser.get("https://www.google.com");

        new WebDriverWait(browser, Duration.ofSeconds(10))
                .until(ExpectedConditions.or(
                        ExpectedConditions.titleContains("Google"),
                        ExpectedConditions.urlContains("google.com")
                ));

        assertTrue(browser.getTitle().contains("Google"));
    }
    @Test
    public void googleSearchBoxAppears() {
        browser.get("https://www.google.com");

        WebElement inputBox = new WebDriverWait(browser, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        assertTrue(inputBox.isEnabled());
    }


    @Test
    public void googleSearchButtonAppears() {
        browser.get("https://www.google.com");

        WebElement inputBox = new WebDriverWait(browser, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        inputBox.sendKeys("u"); // makes the search button appear/enable

        WebElement searchButton = new WebDriverWait(browser, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.name("btnK")));

        assertTrue(searchButton.isDisplayed());
        assertTrue(searchButton.isEnabled());
    }

    @Test
    public void googleSearchTermAppears() {
        browser.get("https://www.google.com");

        WebElement inputBox = new WebDriverWait(browser, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        inputBox.sendKeys("uvic");
        assertEquals("uvic", inputBox.getAttribute("value"));
    }

    @Test
    public void googleSearchResultsAppear() {
        browser.get("https://www.google.com");

        WebElement inputBox = new WebDriverWait(browser, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        inputBox.sendKeys("uvic");
        inputBox.submit();

        new WebDriverWait(browser, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleContains("uvic"));

        assertTrue(browser.getTitle().toLowerCase().contains("uvic"));
    }
}