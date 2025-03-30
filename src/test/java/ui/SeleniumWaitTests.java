package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.openqa.selenium.PageLoadStrategy.NONE;

public class SeleniumWaitTests {

    WebDriver driver;
    //private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
       // driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void loadingImagesImplicitWaitTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement landscape = driver.findElement(By.id("landscape"));
        assertThat(landscape.getAttribute("src")).containsIgnoringCase("landscape");
    }

    @Test
    void loadingImagesExplicitWaitTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id = 'image-container']/img"), 4);
        WebElement landscape = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("landscape")));
        assertThat(landscape.getAttribute("src")).containsIgnoringCase("landscape");
    }

    @Test
    void loadingImagesFluentWaitTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        WebElement landscape = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("landscape")));
        assertThat(landscape.getAttribute("src")).containsIgnoringCase("landscape");
    }

    @Test
    void checkSlowCalcSum() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        //locators
        By calcButtonLocator = By.xpath("//a[text()='Slow calculator']");
        By oneButtonLocator = By.xpath("//div[@class='keys']/span[text()='1']");
        By plusButtonLocator = By.xpath("//div[@class='keys']/span[text()='+']");
        By equalButtonLocator = By.xpath("//div[@class='keys']/span[text()='=']");
        By resultField = By.xpath("//div[@class='screen']");

        //actions
        driver.findElement(calcButtonLocator).click();
        driver.findElement(oneButtonLocator).click();
        driver.findElement(plusButtonLocator).click();
        driver.findElement(oneButtonLocator).click();
        driver.findElement(equalButtonLocator).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(resultField, "2"));
    }

    @Test
    void cookiesTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        WebDriver.Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        assertThat(cookies).hasSize(2);
        Cookie username = options.getCookieNamed("username");
        assertThat(username.getValue()).isEqualTo("John Doe");
        assertThat(username.getPath()).isEqualTo("/");
        Thread.sleep(1000);

        driver.findElement(By.id("refresh-cookies")).click();
        Thread.sleep(1000);

        Cookie newCookie = new Cookie("new-cookie-key", "new-cookie-value");
        options.addCookie(newCookie);
        String readValue = options.getCookieNamed(newCookie.getName())
                .getValue();
        assertThat(newCookie.getValue()).isEqualTo(readValue);
        Thread.sleep(1000);

        cookies = options.getCookies();
        assertThat(cookies).hasSize(3);

        driver.findElement(By.id("refresh-cookies")).click();
        Thread.sleep(1000);

        options.deleteCookie(username);
        assertThat(options.getCookies()).hasSize(cookies.size() - 1);

        driver.findElement(By.id("refresh-cookies")).click();
        Thread.sleep(1000);
    }

    @Test
    void chromeTest() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("123.0.6312.86");
        chromeOptions.setPageLoadStrategy(NONE);
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setPageLoadTimeout(Duration.ofSeconds(60));
        chromeOptions.setScriptTimeout(Duration.ofSeconds(5));
        driver = new ChromeDriver(chromeOptions);

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String html = driver.getPageSource();
        assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
        assertThat(html).contains("Hands-On Selenium WebDriver with Java");
    }

    @Test
    void iframeTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("lead")));
        WebElement iframeElement = driver.findElement(By.id("my-iframe"));
        driver.switchTo().frame(iframeElement);
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("display-6")));
        assertThat(driver.findElement(By.className("lead")).getText()).contains("dolor sit");
        driver.switchTo().defaultContent();
        assertThat(driver.findElement(By.className("display-6")).getText()).contains("IFrame");
    }

    @Test
    void dialogBoxesTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        driver.findElement(By.id("my-alert")).click();;
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertThat(alert.getText()).isEqualTo("Hello world!");
        alert.accept();
        driver.findElement(By.id("my-confirm")).click();
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.id("confirm-text")).getText()).isEqualTo("You chose: true");
        driver.findElement(By.id("my-confirm")).click();
        driver.switchTo().alert().dismiss();
        assertThat(driver.findElement(By.id("confirm-text")).getText()).isEqualTo("You chose: false");
        driver.findElement(By.id("my-prompt")).click();
        driver.switchTo().alert().sendKeys("Test");
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.id("prompt-text")).getText()).isEqualTo("You typed: Test");
        driver.findElement(By.id("my-modal")).click();
        //driver.findElement(By.id("my-modal")).click();
        WebElement save = driver.findElement(By.xpath("//button[normalize-space()= 'Save changes']"));
        wait.until(ExpectedConditions.elementToBeClickable(save));
        save.click();
    }

    @Test
    void navigateTest() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        driver.navigate().to("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        driver.navigate().back();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/", driver.getCurrentUrl());
        driver.navigate().forward();
        driver.navigate().refresh();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html", driver.getCurrentUrl());
    }

    @Test
    void testNewTab() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String initHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        assertThat(driver.getWindowHandles()).hasSize(2);

        driver.switchTo().window(initHandle);
        driver.close();
        assertThat(driver.getWindowHandles()).hasSize(1);
    }

    @Test
    void testNewWindow() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String initHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        assertThat(driver.getWindowHandles()).hasSize(2);

        driver.switchTo().window(initHandle);
        driver.close();
        assertThat(driver.getWindowHandles()).hasSize(1);
    }

    @Test
    void WindowTest() {
        //для теста надо выключить максимайз
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        WebDriver.Window window = driver.manage().window();

        Point initialPosition = window.getPosition();
        Dimension initialSize = window.getSize();
        System.out.printf("Initial window: position {%s} -- size {%s}\n", initialPosition, initialSize);

        window.maximize();

        Point maximizedPosition = window.getPosition();
        Dimension maximizedSize = window.getSize();
        System.out.printf("Maximized window: position {%s} -- size {%s}\n", maximizedPosition, maximizedSize);

        assertThat(initialPosition).isNotEqualTo(maximizedPosition);
        assertThat(initialSize).isNotEqualTo(maximizedSize);
    }

    @Test
    void ColorPickerTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        String initColor = colorPicker.getAttribute("value");
        System.out.println("The initial color is " + initColor);

        Color red = new Color(255,0,0,1);
        String script = String.format("arguments[0].setAttribute('value', '%s');", red.asHex());
        Thread.sleep(3000);
        js.executeScript(script, colorPicker);

        String finalColor = colorPicker.getAttribute("value");
        System.out.println("The final color is " + finalColor);
        assertThat(finalColor).isNotEqualTo(initColor);
        assertThat(Color.fromString(finalColor)).isEqualTo(red);
        Thread.sleep(3000);
    }

    @Test
    void infiniteScrollTest() throws InterruptedException, IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By pLocator = By.tagName("p");
        List<WebElement> paragraphs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, 0));
        int initParagraphsNumber = paragraphs.size();

        WebElement lastParagraph = driver.findElement(By.xpath(String.format("//p[%d]", initParagraphsNumber)));
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script, lastParagraph);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, initParagraphsNumber));
        Thread.sleep(3000);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./image.png"));
    }

    @Test
    void testShadowDom() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html");

        assertThrows(NoSuchElementException.class, ()-> driver.findElement(By.cssSelector("p")));
        WebElement content = driver.findElement(By.id("content"));
        SearchContext shadowRoot = content.getShadowRoot();
        WebElement textElement = shadowRoot.findElement(By.cssSelector("p"));
        assertThat(textElement.getText()).contains("Hello Shadow DOM");
    }

    @Test
    void testWebStorage() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-storage.html");
        WebStorage webStorage = (WebStorage) driver;

        LocalStorage localStorage = webStorage.getLocalStorage();
        System.out.printf("Local storage elements: {/%s}\n", localStorage.size());

        SessionStorage sessionStorage = webStorage.getSessionStorage();
        sessionStorage.keySet()
                .forEach(key -> System.out.printf("Session storage: {/%n}={/%n}\n", key, sessionStorage.getItem(key)));
        assertThat(sessionStorage.size()).isEqualTo(2);

        sessionStorage.setItem("new element", "new value");
        assertThat(sessionStorage.size()).isEqualTo(3);

        driver.findElement(By.id("display-session")).click();

    }
}