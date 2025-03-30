package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class seleniumDZ6 {

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

        WebElement textInputLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Text input']"));
        String textInputText = textInputLabel.getText();
        assertEquals("Text input", textInputText);
    }

    @Test
    void proverkaPassword() throws InterruptedException {

        WebElement password = driver.findElement(By.name("my-password"));
        password.sendKeys("ololo");
        String enteredValue = password.getAttribute("value");
        Thread.sleep(1000);
        assertEquals("ololo", enteredValue);

        WebElement passwordLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Password']"));
        String passwordText = passwordLabel.getText();
        assertEquals("Password", passwordText);
    }

    @Test
    void proverkaTextArea() throws InterruptedException {

        WebElement textArea = driver.findElement(By.name("my-textarea"));
        textArea.sendKeys("uwuwu");
        String enteredValue = textArea.getAttribute("value");
        Thread.sleep(1000);
        assertEquals("uwuwu", enteredValue);

        WebElement textAreaLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Textarea']"));
        String textAreaText = textAreaLabel.getText();
        assertEquals("Textarea", textAreaText);
    }

    @Test
    void proverkaDisabledInput() throws InterruptedException {

        WebElement disabledInput = driver.findElement(By.name("my-disabled"));
        Assertions.assertFalse(disabledInput.isEnabled());
        Assertions.assertThrows(ElementNotInteractableException.class, () -> disabledInput.sendKeys("test"));

        String placeholderText = disabledInput.getAttribute("placeholder");
        Thread.sleep(1000);
        assertEquals("Disabled input", placeholderText);

        WebElement disabledInputLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Disabled input']"));
        String disabledInputText = disabledInputLabel.getText();
        assertEquals("Disabled input", disabledInputText);

    }

    @Test
    void proverkaReadonlyInput() throws InterruptedException {

        WebElement readonlyInput = driver.findElement(By.name("my-readonly"));
        Assertions.assertTrue(readonlyInput.isEnabled());

        String placeholderText = readonlyInput.getAttribute("value");
        Thread.sleep(1000);
        assertEquals("Readonly input", placeholderText);

        WebElement readonlyInputLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Readonly input']"));
        String readonlyInputText = readonlyInputLabel.getText();
        assertEquals("Readonly input", readonlyInputText);

    }

    @Test
    void proverkaDropdownSelect() throws InterruptedException {

        WebElement dropdownSelect = driver.findElement(By.name("my-select"));
        Select dropdown = new Select(dropdownSelect);
        dropdown.selectByVisibleText("Two");
        WebElement selectedOption1 = dropdown.getFirstSelectedOption();
        String selectedValue = selectedOption1.getText();
        Thread.sleep(3000);
        assertEquals("Two", selectedValue);

        dropdown.selectByValue("3");
        WebElement selectedOption2 = dropdown.getFirstSelectedOption();
        String selectedValue2 = selectedOption2.getText();
        assertEquals("Three", selectedValue2);
        Thread.sleep(3000);
    }

    @Test
    @Tag("доделать")
    void proverkaDropdownDatalist() throws InterruptedException {

        WebElement dropdownDatalist = driver.findElement(By.xpath("//input[@list='my-options']"));
        Thread.sleep(2000);
        dropdownDatalist.click();

        //driver.findElement(By.linkText("New York")).click();

        //Select dropdown = new Select(dropdownDatalist);
        //dropdown.selectByVisibleText("New York");
        Thread.sleep(3000);
        //WebElement selectedOption2 = dropdownDatalist.getFirstSelectedOption();
        //String selectedValue2 = selectedOption2.getText();
       // WebElement roditel = driver.findElement(By.id("my-options"));
        //WebElement child = roditel.findElement(By.xpath("//option[@value='New York']"));
        //WebElement child = roditel.findElement(By.linkText("New York"));
       // child.click();
        //String text = child.getText();
        //System.out.println("nuzhniy tekst " + text);

        //JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript("arguments[2].click();", child);

        Thread.sleep(3000);
        //child.click();
        //dropdownDatalist.sendKeys("New York");
        Thread.sleep(3000);
        // проверка из серии - ввели текст и рады. нужна нормальная
    }

    @Test
    void proverkaFileInput() throws InterruptedException, IOException {

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

    @Test
    void proverkaColorPicker() throws InterruptedException {

        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        colorPicker.sendKeys("#ff0099");
        Thread.sleep(1000);
        String colorPickerValue = colorPicker.getAttribute("value");
        WebElement colorPickerLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Color picker']"));
        String colorPickerText = colorPickerLabel.getText();
        assertEquals("Color picker", colorPickerText);
        assertEquals("#ff0099", colorPickerValue);
    }

    @Test
    void proverkaDatePicker() throws InterruptedException {

        WebElement datePicker = driver.findElement(By.xpath("//input[@class='form-control' and @name='my-date']"));
        datePicker.sendKeys("13/04/2025");
        Thread.sleep(1000);
        String datePickerValue = datePicker.getAttribute("value");
        WebElement datePickerLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Date picker']"));
        String datePickerText = datePickerLabel.getText();
        assertEquals("Date picker", datePickerText);
        assertEquals("13/04/2025", datePickerValue);
    }

    @Test
    void proverkaExampleRange() throws InterruptedException {

        WebElement exampleRange = driver.findElement(By.xpath("//input[@class='form-range' and @name='my-range']"));
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.clickAndHold(exampleRange)
                .moveByOffset(60, 0)
                .release()
                .perform();
        Thread.sleep(1000);
        String exampleRangeValue = exampleRange.getAttribute("value");
        WebElement exampleRangeLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Example range']"));
        String exampleRangeText = exampleRangeLabel.getText();
        assertEquals("Example range", exampleRangeText);
        assertEquals("7", exampleRangeValue);
    }

    @Test
    void proverkaNavigation() throws InterruptedException {

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html");
        WebElement secondButton = driver.findElement(By.xpath("//li[normalize-space(.)='2']"));
        secondButton.click();
        assertThat(driver.getCurrentUrl()).contains("navigation2.html");
        WebElement textSecondPage = driver.findElement(By.xpath("//p[@class='lead']"));
        assertTrue(textSecondPage.getText().contains("Ut enim ad minim veniam"));
        Thread.sleep(200);

        WebElement nextButton = driver.findElement(By.xpath("//li[normalize-space(.)='Next']"));
        nextButton.click();
        assertThat(driver.getCurrentUrl()).contains("navigation3.html");
        WebElement textThirdPage = driver.findElement(By.xpath("//p[@class='lead']"));
        assertTrue(textThirdPage.getText().contains("Excepteur sint occaecat"));
        Thread.sleep(2000);

        WebElement previousButton = driver.findElement(By.xpath("//li[normalize-space(.)='Previous']"));
        previousButton.click();
        assertThat(driver.getCurrentUrl()).contains("navigation2.html");
        WebElement textPreviousPage = driver.findElement(By.xpath("//p[@class='lead']"));
        assertTrue(textPreviousPage.getText().contains("Ut enim ad minim veniam"));
        Thread.sleep(2000);

    }

    @Test
    void proverkaDropdownMenu() throws InterruptedException {

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        WebElement leftclick = driver.findElement(By.id("my-dropdown-1"));
        Actions actions = new Actions(driver);
        actions.click(leftclick).perform();
        Thread.sleep(2000);
        List<WebElement> menuItems1 = driver.findElements(By.xpath("//ul[@class='dropdown-menu show']"));
        WebElement firstItem1 = menuItems1.get(0);
        String[] firstItemText1 = firstItem1.getText().split("\n");
        assertEquals("Action", firstItemText1[0]);

        WebElement rightclick = driver.findElement(By.id("my-dropdown-2"));
        actions.contextClick(rightclick).perform();
        Thread.sleep(2000);
        List<WebElement> menuItems2 = driver.findElements(By.id("context-menu-2"));
        WebElement firstItem2 = menuItems2.get(0);
        String[] firstItemText2 = firstItem2.getText().split("\n");
        assertEquals("Action", firstItemText2[0]);

        WebElement doubleclick = driver.findElement(By.id("my-dropdown-3"));
        actions.doubleClick(doubleclick).perform();
        Thread.sleep(2000);
        List<WebElement> menuItems3 = driver.findElements(By.id("context-menu-3"));
        WebElement firstItem3 = menuItems3.get(0);
        String[] firstItemText3 = firstItem3.getText().split("\n");
        assertEquals("Action", firstItemText3[0]);

    }

    @Test
    void proverkaDragAndDrop() throws InterruptedException {

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("target"));
        Point locationTarget = target.getLocation();

        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        Point locationSourseNew = source.getLocation();
        //int xN = locationSourseNew.getX();
        //int yN = locationSourseNew.getY();
        //System.out.println("x = " + xN + "y = " + yN);

        Thread.sleep(2000);
        assertEquals(locationTarget, locationSourseNew);
    }
}
