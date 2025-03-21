package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class seleniumDZ5 {

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
    void proverkaTextInput() throws InterruptedException {

        WebElement textInput = driver.findElement(By.id("my-text-id"));
        textInput.sendKeys("Halo");
        String enteredValue = textInput.getAttribute("value");
        Thread.sleep(1000);
        assertEquals("Halo", enteredValue);
    }

    @Test
    void proverkaPassword() throws InterruptedException {

        WebElement password = driver.findElement(By.name("my-password"));
        password.sendKeys("ololo");
        String enteredValue = password.getAttribute("value");
        Thread.sleep(1000);
        assertEquals("ololo", enteredValue);
    }

    @Test
    void proverkaTextArea() throws InterruptedException {

        WebElement textArea = driver.findElement(By.cssSelector("[name='my-textarea']"));
        textArea.sendKeys("uwuwu");
        String enteredValue = textArea.getAttribute("value");
        Thread.sleep(1000);
        assertEquals("uwuwu", enteredValue);
    }

    @Test
    void proverkaDisabledInput() throws InterruptedException {

        WebElement disabledInput = driver.findElement(By.xpath("//input[@class='form-control' and @placeholder='Disabled input']"));
        String placeholderText = disabledInput.getAttribute("placeholder");
        Thread.sleep(1000);
        assertEquals("Disabled input", placeholderText);
    }

    @Test
    void proverkaReadonlyInput() throws InterruptedException {

        WebElement readonlyInput = driver.findElement(By.xpath("//input[@class='form-control' and @value='Readonly input']"));
        String placeholderText = readonlyInput.getAttribute("value");
        Thread.sleep(1000);
        assertEquals("Readonly input", placeholderText);
    }

    @Test
    void proverkaDropdownSelect() throws InterruptedException {

        WebElement dropdownSelect = driver.findElement(By.className("form-select"));
        Select dropdown = new Select(dropdownSelect);
        dropdown.selectByVisibleText("Two");
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        String selectedValue = selectedOption.getText();
        Thread.sleep(1000);
        assertEquals("Two", selectedValue);
    }

    @Test
    @Tag("доделать")
    void proverkaDropdownDatalist() throws InterruptedException {

       WebElement dropdownDatalist = driver.findElement(By.xpath("//input[@list='my-options']"));
       dropdownDatalist.sendKeys("New York");
       Thread.sleep(3000);
        // проверка из серии - ввели текст и рады. нужна нормальная
    }

    @Test
    @Tag("доделать")
    void proverkaFileInput() throws InterruptedException {

        WebElement fileInput = driver.findElement(By.name("my-file"));
        Thread.sleep(3000);
        //тут вообще нет проверки. хз как оно работает
    }

    @Test
    void proverkaCheckedCheckbox() throws InterruptedException {

        WebElement checkedCheckbox = driver.findElement(By.id("my-check-1"));
        checkedCheckbox.click();
        Thread.sleep(1000);
        boolean isChecked = checkedCheckbox.isSelected();
        WebElement checkedCheckboxLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Checked checkbox']"));
        String checkedCheckboxText = checkedCheckboxLabel.getText();
        assertEquals("Checked checkbox", checkedCheckboxText);
        assertEquals(false, isChecked);
    }

    @Test
    void proverkaDefaultCheckbox() throws InterruptedException {

        WebElement defaultCheckbox = driver.findElement(By.id("my-check-2"));
        defaultCheckbox.click();
        Thread.sleep(1000);
        boolean isChecked = defaultCheckbox.isSelected();
        WebElement checkedCheckboxLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Default checkbox']"));
        String checkedCheckboxText = checkedCheckboxLabel.getText();
        assertEquals("Default checkbox", checkedCheckboxText);
        assertEquals(true, isChecked);
    }

    @Test
    void proverkaCheckedRadio() throws InterruptedException {

        WebElement checkedRadio = driver.findElement(By.id("my-radio-1"));
        Thread.sleep(1000);
        boolean isChecked = checkedRadio.isSelected();
        WebElement checkedCheckboxLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Checked radio']"));
        String checkedCheckboxText = checkedCheckboxLabel.getText();
        assertEquals("Checked radio", checkedCheckboxText);
        assertEquals(true, isChecked);
    }

    @Test
    void proverkaDefaultRadio() throws InterruptedException {

        WebElement defaultRadio = driver.findElement(By.id("my-radio-2"));
        defaultRadio.click();
        Thread.sleep(1000);
        boolean isSelected = defaultRadio.isSelected();
        WebElement checkedCheckboxLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Default radio']"));
        String checkedCheckboxText = checkedCheckboxLabel.getText();
        assertEquals("Default radio", checkedCheckboxText);
        assertEquals(true, isSelected);
    }
}
