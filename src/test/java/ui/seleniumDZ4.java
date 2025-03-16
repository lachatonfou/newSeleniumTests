package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class seleniumDZ4 {

    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @Tag ("Chapter3")
    void proverkaWebForm() {
        driver.findElement(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[@href='web-form.html']")).click();
        String webFormUrl = "web-form.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + webFormUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[text()='Web form']")).getText();
        assertEquals("Web form", title);
    }

    @Test
    @Tag ("Chapter3")
    void proverkaNavigation() {

        driver.findElement(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[@href='navigation1.html']")).click();
        String navigationUrl = "navigation1.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + navigationUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Navigation example", title);
    }

    @Test
    @Tag ("Chapter3")
    void proverkaDropDownMenu() {

        driver.findElement(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[@href='dropdown-menu.html']")).click();
        String dropDownMenuUrl = "dropdown-menu.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + dropDownMenuUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Dropdown menu", title);
    }

    @Test
    @Tag ("Chapter3")
    void proverkaMouseOver() {

        driver.findElement(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[@href='mouse-over.html']")).click();
        String mouseOverUrl = "mouse-over.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + mouseOverUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Mouse over", title);
    }

    @Test
    @Tag ("Chapter3")
    void proverkaDragAndDrop() {

        driver.findElement(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[@href='drag-and-drop.html']")).click();
        String dragAndDropUrl = "drag-and-drop.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + dragAndDropUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Drag and drop", title);
    }

    @Test
    @Tag ("Chapter3")
    void proverkaDrawInCanvas() {

        driver.findElement(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[@href='draw-in-canvas.html']")).click();
        String drawInCanvasUrl = "draw-in-canvas.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + drawInCanvasUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Drawing in canvas", title);
    }

    @Test
    @Tag ("Chapter3")
    void proverkaLoadingImages() {

        driver.findElement(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[@href='loading-images.html']")).click();
        String loadingImagesUrl = "loading-images.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + loadingImagesUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Loading images", title);
    }

    @Test
    @Tag ("Chapter3")
    void proverkaSlowCalculator() {

        driver.findElement(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']/../a[@href='slow-calculator.html']")).click();
        String slowCalculatorUrl = "slow-calculator.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + slowCalculatorUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Slow calculator", title);
    }

    @Test
    @Tag ("Chapter4")
    void proverkaLongPage() {

        driver.findElement(By.xpath("//h5[text()='Chapter 4. Browser-Agnostic Features']/../a[@href='long-page.html']")).click();
        String longPageUrl = "long-page.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + longPageUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("This is a long page", title);
    }

    @Test
    @Tag ("Chapter4")
    void proverkaInfiniteScroll() {

        driver.findElement(By.xpath("//h5[text()='Chapter 4. Browser-Agnostic Features']/../a[@href='infinite-scroll.html']")).click();
        String infiniteScrollUrl = "infinite-scroll.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + infiniteScrollUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Infinite scroll", title);
    }

    @Test
    @Tag ("Chapter4")
    void proverkaShadowDom() {

        driver.findElement(By.xpath("//h5[text()='Chapter 4. Browser-Agnostic Features']/../a[@href='shadow-dom.html']")).click();
        String shadowDomUrl = "shadow-dom.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + shadowDomUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Shadow DOM", title);
    }

    @Test
    @Tag ("Chapter4")
    void proverkaCookies() {

        driver.findElement(By.xpath("//h5[text()='Chapter 4. Browser-Agnostic Features']/../a[@href='cookies.html']")).click();
        String cookiesUrl = "cookies.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + cookiesUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Cookies", title);
    }

    @Test
    @Tag ("Chapter4")
    void proverkaFrames() {

        driver.findElement(By.xpath("//h5[text()='Chapter 4. Browser-Agnostic Features']/../a[@href='frames.html']")).click();
        String framesUrl = "frames.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + framesUrl, currentUrl);

        //String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        driver.switchTo().frame("frame-header");
        String title = driver.findElement(By.xpath("//h1[@class='display-6' and text()='Frames']")).getText();
        assertEquals("Frames", title);
    }

    @Test
    @Tag ("Chapter4")
    void proverkaIFrames() {

        driver.findElement(By.xpath("//h5[text()='Chapter 4. Browser-Agnostic Features']/../a[@href='iframes.html']")).click();
        String iFramesUrl = "iframes.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + iFramesUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("IFrame", title);
    }

    @Test
    @Tag ("Chapter4")
    void proverkaDialogBoxes() {

        driver.findElement(By.xpath("//h5[text()='Chapter 4. Browser-Agnostic Features']/../a[@href='dialog-boxes.html']")).click();
        String dialogBoxesUrl = "dialog-boxes.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + dialogBoxesUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Dialog boxes", title);
    }

    @Test
    @Tag ("Chapter4")
    void proverkaWebStorage() {

        driver.findElement(By.xpath("//h5[text()='Chapter 4. Browser-Agnostic Features']/../a[@href='web-storage.html']")).click();
        String webStorageUrl = "web-storage.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + webStorageUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Web storage", title);
    }

    @Test
    @Tag ("Chapter5")
    void proverkaGeolocation() {

        driver.findElement(By.xpath("//h5[text()='Chapter 5. Browser-Specific Manipulation']/../a[@href='geolocation.html']")).click();
        String geolocationUrl = "geolocation.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + geolocationUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Geolocation", title);
    }

    @Test
    @Tag ("Chapter5")
    void proverkaNotifications() {

        driver.findElement(By.xpath("//h5[text()='Chapter 5. Browser-Specific Manipulation']/../a[@href='notifications.html']")).click();
        String notificationsUrl = "notifications.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + notificationsUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Notifications", title);
    }

    @Test
    @Tag ("Chapter5")
    void proverkaGetUserMedia() {

        driver.findElement(By.xpath("//h5[text()='Chapter 5. Browser-Specific Manipulation']/../a[@href='get-user-media.html']")).click();
        String getUserMediaUrl = "get-user-media.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + getUserMediaUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Get user media", title);
    }

    @Test
    @Tag ("Chapter5")
    void proverkaMultilanguage() {

        driver.findElement(By.xpath("//h5[text()='Chapter 5. Browser-Specific Manipulation']/../a[@href='multilanguage.html']")).click();
        String multiLanguageUrl  = "multilanguage.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + multiLanguageUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6 lang']")).getText();
        assertEquals("", title);
    }

    @Test
    @Tag ("Chapter5")
    void proverkaConsoleLogs() {

        driver.findElement(By.xpath("//h5[text()='Chapter 5. Browser-Specific Manipulation']/../a[@href='console-logs.html']")).click();
        String consoleLogsUrl = "console-logs.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + consoleLogsUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Console logs", title);
    }

    @Test
    @Tag ("Chapter7")
    void proverkaLoginForm() {

        driver.findElement(By.xpath("//h5[text()='Chapter 7. The Page Object Model (POM)']/../a[@href='login-form.html']")).click();
        String loginFormUrl = "login-form.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + loginFormUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Login form", title);
    }

    @Test
    @Tag ("Chapter7")
    void proverkaSlowLogin() {

        driver.findElement(By.xpath("//h5[text()='Chapter 7. The Page Object Model (POM)']/../a[@href='login-slow.html']")).click();
        String slowLoginUrl = "login-slow.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + slowLoginUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Slow login form", title);
    }

    @Test
    @Tag ("Chapter8")
    void proverkaRandomCalculator() {

        driver.findElement(By.xpath("//h5[text()='Chapter 8. Testing Framework Specifics']/../a[@href='random-calculator.html']")).click();
        String randomCalculatorUrl = "random-calculator.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + randomCalculatorUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Random calculator", title);
    }

    @Test
    @Tag ("Chapter9")
    void proverkaDownloadFiles() {

        driver.findElement(By.xpath("//h5[text()='Chapter 9. Third-Party Integrations']/../a[@href='download.html']")).click();
        String downloadUrl = "download.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + downloadUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Download files", title);
    }

    @Test
    @Tag ("Chapter9")
    void proverkaABTesting() {

        driver.findElement(By.xpath("//h5[text()='Chapter 9. Third-Party Integrations']/../a[@href='ab-testing.html']")).click();
        String abTestingUrl = "ab-testing.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + abTestingUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("A/B Testing", title);
    }

    @Test
    @Tag ("Chapter9")
    void proverkaDataTypes() {

        driver.findElement(By.xpath("//h5[text()='Chapter 9. Third-Party Integrations']/../a[@href='data-types.html']")).click();
        String dataTypesUrl = "data-types.html";

        String currentUrl = driver.getCurrentUrl();
        assertEquals(BASE_URL + dataTypesUrl, currentUrl);

        String title = driver.findElement(By.xpath("//h1[@class='display-6']")).getText();
        assertEquals("Data types", title);
    }
}

