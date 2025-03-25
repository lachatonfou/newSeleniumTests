package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class SeleniumActionTests {

    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

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
    void submitForm() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        WebElement webFormButton = driver.findElement(By.xpath("//a[@href = 'web-form.html']"));
        Thread.sleep(1000);
        webFormButton.click();

        //driver.findElement(By.xpath("//form")).submit();
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(3000);
    }

    @Test
    void dropdownSelect() throws InterruptedException {
        WebElement dropdownSelect = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdownSelect);
        String actualText = select.getFirstSelectedOption().getText();
        assertEquals("Open this select menu", actualText);

        //select.selectByIndex(2);
        //assertEquals("Two", select.getFirstSelectedOption().getText());
        //Thread.sleep(3000);

        select.selectByValue("2");
        assertEquals("Two", select.getFirstSelectedOption().getText());
        Thread.sleep(3000);

        select.selectByIndex(0);
        assertEquals("Open this select menu", select.getFirstSelectedOption().getText());
        Thread.sleep(3000);

        List<WebElement> selected = select.getAllSelectedOptions();
        assertEquals(1, selected.size());

        List<WebElement> options = select.getOptions();
        assertEquals(4, options.size());
    }

    @Test
    void disabledInput() throws InterruptedException {
        WebElement disabledInput = driver.findElement(By.name("my-disabled"));

        Assertions.assertFalse(disabledInput.isEnabled());
        Assertions.assertThrows(ElementNotInteractableException.class, () -> disabledInput.sendKeys("test"));
        System.out.println(disabledInput.getCssValue("background-color"));
        System.out.println(disabledInput.getCssValue("color"));

        System.out.println("disabledInput.getText()");
        System.out.println(disabledInput.getText());
        System.out.println("disabledInput.findElement(By.xpath(\"..\")).getText()");
        //от чайлд элемента поднимаемся к родительскому и берем его текст
        System.out.println(disabledInput.findElement(By.xpath("..")).getText());
        System.out.println(disabledInput.findElement(By.xpath("..")).getAttribute("innerText"));
        System.out.println(disabledInput.findElement(By.xpath("..")).getAttribute("textContent"));
        assertEquals("Disabled input", disabledInput.findElement(By.xpath("..")).getText());
        assertEquals("Disabled input", disabledInput.getAttribute("placeholder"));
        assertEquals("Disabled input", disabledInput.getDomAttribute("placeholder"));
    }

    @Test
    void fileInput() throws InterruptedException, IOException {
        String filePatch = "src/test/resources/test.txt";
        //читаем содержимое
        String content = new String(Files.readAllBytes(Paths.get(filePatch)));
        System.out.println("Содержимое файла: " + content);
        //получаем урл
        URL url = SeleniumActionTests.class.getClassLoader().getResource("test.txt");
        String absolutePath = new File(url.getPath()).getAbsolutePath();

        WebElement fileInput = driver.findElement(By.name("my-file"));
        fileInput.sendKeys(absolutePath);
        Thread.sleep(3000);
        WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));
        submit.click();
        Thread.sleep(3000);
        assertThat(driver.getCurrentUrl()).contains("test.txt");
    }

    @Test
    void actionAPIMouse() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");
        Thread.sleep(2000);

        List<WebElement> images = driver.findElements(By.className("img-fluid"));
        for (WebElement image : images) {
            new Actions(driver)
                    .moveToElement(image)
                    .perform();
            //Actions actions = new Actions(driver);
            //actions.moveToElement(image).perform();;
            Thread.sleep(1000);
        }
    }

    @Test
    void actionAPIMouseClick() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        Thread.sleep(2000);

        WebElement dropdown1 = driver.findElement(By.id("my-dropdown-1"));
        new Actions(driver)
                .click(dropdown1)
                .perform();
        Thread.sleep(2000);

        WebElement dropdown2 = driver.findElement(By.id("my-dropdown-2"));
        new Actions(driver)
                .contextClick(dropdown2)
                .perform();
        Thread.sleep(2000);

        WebElement dropdown3 = driver.findElement(By.id("my-dropdown-3"));
        new Actions(driver)
                .doubleClick(dropdown3)
                .perform();
        Thread.sleep(2000);
    }

    @Test
    void actionDragAndDrop() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        Thread.sleep(2000);

        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("target"));
        new Actions(driver)
                .scrollToElement(draggable)
                .scrollByAmount(0, 300)
                .dragAndDrop(draggable, droppable)
                .perform();
        Thread.sleep(2000);
    }

    @Test
    void colorpickerTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        String initColor = colorPicker.getAttribute("value");
        System.out.println("The initial color is " + initColor);

        Color red = new Color(255,0,0,1);
        String script = String.format("arguments[0].setAttribute('value', '%s');", red.asHex());
        Thread.sleep(2000);
        js.executeScript(script, colorPicker);

        String finalColor = colorPicker.getAttribute("value");
        System.out.println("The final color is " + finalColor);
        assertThat(finalColor).isNotEqualTo(initColor);
        assertThat(Color.fromString(finalColor)).isEqualTo(red);
        Thread.sleep(2000);
    }

    @Test
    void actionAPIKeyboard() throws InterruptedException {
        WebElement input = driver.findElement(By.id("my-text-id"));
        input.click();
        new Actions(driver)
                .keyDown(Keys.SHIFT)
                .sendKeys("upper-case")
                .keyUp(Keys.SHIFT)
                .sendKeys("lower-case")
                .perform();
        Thread.sleep(2000);
        assertEquals("UPPER_CASElower-case", input.getAttribute("value"));

        WebElement password = driver.findElement(By.name("my-password"));
        password.click();
        new Actions(driver)
                .sendKeys("admin123")
                .perform();
        Thread.sleep(2000);
        assertEquals("admin123", password.getAttribute("value"));
    }

    @Test
    void spaceTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        Thread.sleep(2000);

        new Actions(driver)
                .keyDown(Keys.SPACE)
                .perform();
        Thread.sleep(2000);
    }

}