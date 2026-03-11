package lab05;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UVicTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String UVIC_HOME = "https://www.uvic.ca/";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Keep it simple. Headless sometimes breaks UI selectors on some sites.
        // If your lab requires headless, uncomment the next line.
        // options.addArguments("--headless=new");

        // These flags help on some machines, but are usually optional.
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        // IMPORTANT: assign to the field, not a local variable
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().setSize(new Dimension(1280, 800));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ---------- Helper methods ----------

    private WebElement firstVisible(List<By> locators) {
        for (By by : locators) {
            List<WebElement> found = driver.findElements(by);
            for (WebElement el : found) {
                try {
                    if (el.isDisplayed() && el.isEnabled()) return el;
                } catch (StaleElementReferenceException ignored) {
                    // try next element
                }
            }
        }
        return null;
    }

    private WebElement waitVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private WebElement waitClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    private void goHome() {
        driver.get(UVIC_HOME);
        wait.until(ExpectedConditions.urlContains("uvic.ca"));
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    private WebElement findSearchButtonOrFail() {
        // UVic site changes markup sometimes, so we use a few likely locators.
        WebElement searchBtn = firstVisible(List.of(
                By.cssSelector("button[aria-label*='Search' i]"),
                By.cssSelector("a[aria-label*='Search' i]"),
                By.cssSelector("button[title*='Search' i]"),
                By.cssSelector("a[title*='Search' i]"),
                By.cssSelector("button[id*='search' i]"),
                By.cssSelector("a[id*='search' i]"),
                By.cssSelector("button[class*='search' i]"),
                By.cssSelector("a[class*='search' i]")
        ));

        assertNotNull(searchBtn, "Could not find a visible Search button/link on the UVic homepage.");
        return searchBtn;
    }

    private WebElement findSearchInputOrFail() {
        // Common patterns for site search inputs
        WebElement input = firstVisible(List.of(
                By.cssSelector("input[type='search']"),
                By.cssSelector("input[aria-label*='Search' i]"),
                By.cssSelector("input[name*='search' i]"),
                By.cssSelector("input[id*='search' i]"),
                By.cssSelector("input[class*='search' i]"),
                By.cssSelector("input[type='text'][placeholder*='Search' i]"),
                By.cssSelector("input[placeholder*='Search' i]")
        ));

        if (input != null) return input;

        // If it is in a dialog/overlay that takes time, try waiting for a generic search input
        try {
            return waitVisible(By.cssSelector("input[type='search'], input[placeholder*='Search' i], input[aria-label*='Search' i]"));
        } catch (TimeoutException e) {
            fail("Could not find a Search input after opening the Search UI.");
            return null;
        }
    }

    private void openSearchUI() {
        WebElement searchBtn = findSearchButtonOrFail();
        try {
            searchBtn.click();
        } catch (ElementClickInterceptedException ex) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchBtn);
        }
        // Wait for any search input to appear
        findSearchInputOrFail();
    }

    // ---------- Tests ----------

    @Test
    public void uvicHomePageLoads() {
        goHome();
        String title = driver.getTitle();
        assertNotNull(title);
        assertFalse(title.trim().isEmpty(), "Page title should not be empty.");
        assertTrue(driver.getCurrentUrl().contains("uvic.ca"), "URL should contain uvic.ca");
    }

    @Test
    public void uvicSearchButtonExists() {
        goHome();
        WebElement searchBtn = findSearchButtonOrFail();
        assertTrue(searchBtn.isDisplayed(), "Search button should be displayed.");
    }

    @Test
    public void clickingSearchShowsSearchBar() {
        goHome();
        openSearchUI();
        WebElement input = findSearchInputOrFail();
        assertTrue(input.isDisplayed(), "Search input should be visible after clicking Search.");
    }

    @Test
    public void typingCscAppearsInSearchBar() {
        goHome();
        openSearchUI();
        WebElement input = findSearchInputOrFail();

        input.clear();
        input.sendKeys("csc");

        // Verify the input value updates
        wait.until(d -> "csc".equalsIgnoreCase(input.getAttribute("value")));
        assertEquals("csc", input.getAttribute("value"));
    }

    @Test
    public void submittingSearchLoadsSearchResultsTitle() {
        goHome();
        openSearchUI();
        WebElement input = findSearchInputOrFail();

        input.clear();
        input.sendKeys("csc");
        input.sendKeys(Keys.ENTER);

        // Wait for a navigation or results page content to load
        wait.until(ExpectedConditions.or(
                ExpectedConditions.titleContains("Search"),
                ExpectedConditions.urlContains("search"),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("main, #main, .main"))
        ));

        String title = driver.getTitle();
        assertNotNull(title);
        assertFalse(title.trim().isEmpty());
        // Usually contains "Search" but allow partial, since sites change wording
        assertTrue(title.toLowerCase().contains("search") || driver.getCurrentUrl().toLowerCase().contains("search"),
                "After submitting a search, the page title or URL should indicate search results.");
    }

    @Test
    public void computerSciencePageHasPhoneNumber() {
        // Go directly to the UVic Computer Science unit page and look for a phone-ish pattern.
        driver.get("https://www.uvic.ca/ecs/computerscience/");
        wait.until(ExpectedConditions.urlContains("uvic.ca"));
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

        String bodyText = driver.findElement(By.tagName("body")).getText();

        // Basic phone pattern checks (UVic Victoria numbers often 250-xxx-xxxx).
        boolean hasPhone =
                bodyText.contains("250") &&
                        (bodyText.matches("(?s).*250\\D{0,6}\\d{3}\\D{0,6}\\d{4}.*") ||
                                bodyText.matches("(?s).*\\(250\\)\\D{0,6}\\d{3}\\D{0,6}\\d{4}.*") ||
                                bodyText.toLowerCase().contains("phone"));

        assertTrue(hasPhone, "Computer Science page should contain a phone number (or clearly labeled phone contact).");
    }
}