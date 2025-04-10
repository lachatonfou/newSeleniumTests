package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class seleniumDZ7 {

    WebDriver driver;
    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void proverkaInfiniteScroll () {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")));
        List<WebElement> initParagraph = driver.findElements(By.tagName("p"));
        int initParagraphAmount = initParagraph.size();

        WebElement finalParagraphs = driver.findElement(By.xpath(String.format("//p[%d]", initParagraphAmount)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", finalParagraphs);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("p")));

        List<WebElement> finalParagraph = driver.findElements(By.tagName("p"));
        int finalParagraphAmount = finalParagraph.size();
        System.out.println("начальное " + initParagraphAmount);
        System.out.println("конечное " + finalParagraphAmount);
        assertNotEquals(initParagraphAmount,finalParagraphAmount);
    }

    @Test
    void proverkaShadowDom () {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html");
        WebElement zagolovok = driver.findElement(By.className("display-6"));
        String textZagolovok = zagolovok.getText();
        assertEquals(textZagolovok, "Shadow DOM");

        WebElement content = driver.findElement(By.id("content"));
        SearchContext shadowRoot = content.getShadowRoot();
        WebElement textElement = shadowRoot.findElement(By.cssSelector("p"));
        String label = textElement.getText();
        assertEquals(label, "Hello Shadow DOM");
    }

    @Test
    void proverkaCookies() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/cookies.html");
        Set<Cookie> cookies = driver.manage().getCookies();
        int kolvoCookies = cookies.size();
        assertEquals(kolvoCookies, 2);

        driver.manage().addCookie(new Cookie("username", "ololo"));
        Cookie cookieChangeg = driver.manage().getCookieNamed("username");
        String cookieChangedValue = cookieChangeg.getValue();
        assertEquals(cookieChangedValue, "ololo");

        driver.manage().addCookie(new Cookie("novoe", "fff"));
        Cookie cookieNew = driver.manage().getCookieNamed("novoe");
        String cookieNewValue = cookieNew.getValue();
        assertEquals(cookieNewValue, "fff");
        driver.findElement(By.id("refresh-cookies")).click();
        Thread.sleep(2000);

        Set<Cookie> cookies2 = driver.manage().getCookies();
        int kolvoCookies2 = cookies2.size();
        assertEquals(kolvoCookies2, 3);

        driver.manage().deleteCookieNamed("novoe");
        Set<Cookie> cookies3 = driver.manage().getCookies();
        int kolvoCookies3 = cookies3.size();
        driver.findElement(By.id("refresh-cookies")).click();
        Thread.sleep(2000);
        assertEquals(kolvoCookies3, 2);
    }

    @Test
    void proverkaIFrames() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("lead")));

        WebElement iframe = driver.findElement(By.id("my-iframe"));
        driver.switchTo().frame(iframe);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("lead")));
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("display-6")));
        assertThat(driver.findElement(By.className("lead")).getText()).contains("Lorem ipsum dolor sit amet");

        driver.switchTo().defaultContent();
        String title = driver.findElement(By.className("display-6")).getText();
        assertEquals("IFrame", title);
    }

    @Test
    void proverkaDialogBoxes() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement launchAlertButton = driver.findElement(By.id("my-alert"));
        launchAlertButton.click();
        Alert launchAlert = wait.until(ExpectedConditions.alertIsPresent());
        String launchAllertText = launchAlert.getText();
        assertEquals(launchAllertText, "Hello world!");
        Thread.sleep(2000);
        launchAlert.accept();
        Thread.sleep(2000);

        WebElement launchConfirmButton = driver.findElement(By.id("my-confirm"));
        launchConfirmButton.click();
        Alert launchConfirm = wait.until(ExpectedConditions.alertIsPresent());
        String launchConfirmText = launchConfirm.getText();
        assertEquals(launchConfirmText, "Is this correct?");
        Thread.sleep(2000);
        launchConfirm.accept();
        Thread.sleep(2000);
        String launchConfirmFinalText = driver.findElement(By.id("confirm-text")).getText();
        assertEquals(launchConfirmFinalText, "You chose: true");

        WebElement launchPromptButton = driver.findElement(By.id("my-prompt"));
        launchPromptButton.click();
        Alert launchPrompt = wait.until(ExpectedConditions.alertIsPresent());
        String launchPromptText = launchPrompt.getText();
        assertEquals(launchPromptText, "Please enter your name");
        launchPrompt.sendKeys("Mariya");
        Thread.sleep(2000);
        launchPrompt.accept();
        Thread.sleep(2000);
        String launchPromptFinalText = driver.findElement(By.id("prompt-text")).getText();
        assertEquals(launchPromptFinalText, "You typed: Mariya");

        WebElement launchModalButton = driver.findElement(By.id("my-modal"));
        launchModalButton.click();
        Thread.sleep(2000);
        String launchModalText = driver.findElement(By.className("modal-body")).getText();
        assertEquals(launchModalText, "This is the modal body");
        Thread.sleep(2000);
        driver.findElement(By.className("btn-primary")).click();
        Thread.sleep(2000);
        String launchModalFinalText = driver.findElement(By.id("modal-text")).getText();
        assertEquals(launchModalFinalText, "You chose: Save changes");
    }

    @Test
    void proverkaWebStorage() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-storage.html");
        WebStorage webStorage = (WebStorage) driver;

        LocalStorage localStorage = webStorage.getLocalStorage();
        System.out.println("local storage size = " + localStorage.size());

        SessionStorage sessionStorage = webStorage.getSessionStorage();
        System.out.println("session storage size = " + sessionStorage.size());
        assertThat(sessionStorage.size()).isEqualTo(2);

        sessionStorage.setItem("new", "super new");
        SessionStorage sessionStorageNew = webStorage.getSessionStorage();
        System.out.println("new session storage size = " + sessionStorageNew.size());
        assertThat(sessionStorageNew.size()).isEqualTo( 3);

        driver.findElement(By.id("display-local")).click();
        assertEquals(driver.findElement(By.id("local-storage")).getText(), "{}");
        driver.findElement(By.id("display-session")).click();
        assertEquals(driver.findElement(By.id("session-storage")).getText(), "{\"lastname\":\"Doe\",\"name\":\"John\",\"new\":\"super new\"}");
        Thread.sleep(2000);
    }

    @Test
    void proverkaLoadingImages() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("compass")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("calendar")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("award")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("landscape")));
    }

    @Test
    void proverkaSlowCalculator() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
        driver.findElement(By.xpath("//div[@class='keys']/span[text()='2']")).click();
        driver.findElement(By.xpath("//div[@class='keys']/span[text()='+']")).click();
        driver.findElement(By.xpath("//div[@class='keys']/span[text()='3']")).click();
        driver.findElement(By.xpath("//div[@class='keys']/span[text()='=']")).click();
        By result = By.className("screen");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(result, "5"));
    }
}
