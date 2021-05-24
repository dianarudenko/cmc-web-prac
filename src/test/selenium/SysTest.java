package selenium;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SysTest {
    private final String pathToDriver = "./chromedriver";
    private WebDriver driver;

    @BeforeSuite
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        driver = new ChromeDriver();
    }
    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 0)
    void deleteClientTest() {
        driver.get("http://localhost:9090/webapp/");
        driver.findElement(By.id("clients")).click();
        {
            List<WebElement> elements = driver.findElements(By.tagName("td"));
            assert(elements.size() > 0);
        }
        WebElement tr = driver.findElement(By.cssSelector("tbody > tr:nth-child(1)"));
        int id = Integer.parseInt(tr.findElement(By.tagName("td")).getText());
        driver.findElement(By.cssSelector("tbody > tr:nth-child(1) td:nth-child(5)")).click();
        int newId = Integer.parseInt(driver.findElement(
            By.cssSelector("tbody > tr:nth-child(1) > td")).getText());
        Assert.assertNotEquals(id, newId);
    }

    @Test(priority = 1)
    public void addClientTest() {
        driver.get("http://localhost:9090/webapp/");
        driver.findElement(By.id("clients")).click();
        {
            List<WebElement> elements = driver.findElements(By.tagName("td"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.id("add_client")).click();
        driver.findElement(By.cssSelector("div > input")).click();
        Assert.assertEquals(driver.getTitle(), "Ошибка");
        driver.findElement(By.id("clients-page")).click();
        driver.findElement(By.id("add_client")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("test_name");
        driver.findElement(By.id("surname")).clear();
        driver.findElement(By.id("surname")).sendKeys("test_surname");
        driver.findElement(By.id("middle_name")).clear();
        driver.findElement(By.id("middle_name")).sendKeys("test_middle_name");
        driver.findElement(By.id("phone_number")).clear();
        driver.findElement(By.id("phone_number")).sendKeys("+7(777)777-77-77");
        driver.findElement(By.cssSelector("div > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("h2"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(),
                            "test_surname test_name test_middle_name");
        Assert.assertEquals(driver.findElement(By.cssSelector("div > p")).getText(),
                            "+7(777)777-77-77");
        Assert.assertNotNull(driver.findElement(By.cssSelector("div:nth-of-type(2) > div > a")).getText());
        driver.findElement(By.id("clients-page")).click();
        Assert.assertNotNull(driver.findElement(
            By.linkText("test_surname test_name test_middle_name")));
        Assert.assertNotNull(driver.findElement(By.linkText("+7(777)777-77-77")));
        driver.findElement(By.id("clients-page")).click();
        driver.findElement(By.id("add_client")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("test_name_2");
        driver.findElement(By.id("surname")).clear();
        driver.findElement(By.id("surname")).sendKeys("test_surname_2");
        driver.findElement(By.id("middle_name")).clear();
        driver.findElement(By.id("middle_name")).sendKeys("test_middle_name_2");
        driver.findElement(By.id("phone_number")).clear();
        driver.findElement(By.id("phone_number")).sendKeys("+8(888)888-88-88");
        {
            WebElement dropdown = driver.findElement(By.id("active-service"));
            dropdown.findElement(By.cssSelector("option:nth-child(2)")).click();
        }
        driver.findElement(By.cssSelector("div > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("h2"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(),
                            "test_surname_2 test_name_2 test_middle_name_2");
        Assert.assertEquals(driver.findElement(By.cssSelector("div > p")).getText(),
                            "+8(888)888-88-88");
        Assert.assertNotNull(driver.findElement(By.cssSelector("tr:nth-child(2)")));
        Assert.assertNotNull(driver.findElement(By.cssSelector("div:nth-of-type(3) tr:nth-of-type(2)")));
        driver.findElement(By.id("clients-page")).click();
        driver.findElement(By.cssSelector("tbody > tr:nth-child(5) td:nth-child(5)")).click();
        driver.findElement(By.cssSelector("tbody > tr:nth-child(5) td:nth-child(5)")).click();
    }

    @Test(priority = 2)
    void editClientTest() {
        driver.get("http://localhost:9090/webapp/");
        driver.findElement(By.id("clients")).click();
        {
            List<WebElement> elements = driver.findElements(By.tagName("td"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.cssSelector("tbody td:nth-child(6)")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("surname")).clear();
        driver.findElement(By.id("middle_name")).clear();
        driver.findElement(By.id("phone_number")).clear();
        driver.findElement(By.cssSelector("div > input")).click();
        Assert.assertEquals(driver.getTitle(), "Ошибка");
        driver.findElement(By.id("clients-page")).click();
        driver.findElement(By.cssSelector("tbody td:nth-child(6)")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("test_name");
        driver.findElement(By.id("surname")).clear();
        driver.findElement(By.id("surname")).sendKeys("test_surname");
        driver.findElement(By.id("middle_name")).clear();
        driver.findElement(By.id("middle_name")).sendKeys("test_middle_name");
        driver.findElement(By.id("phone_number")).clear();
        driver.findElement(By.id("phone_number")).sendKeys("+7(777)777-77-77");
        driver.findElement(By.cssSelector("div > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("h2"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(),
                            "test_surname test_name test_middle_name");
        Assert.assertEquals(driver.findElement(By.cssSelector("div > p")).getText(),
                            "+7(777)777-77-77");
        Assert.assertNotNull(driver.findElement(By.cssSelector("div:nth-of-type(2) > div > a")).getText());
        driver.findElement(By.id("clients-page")).click();
        Assert.assertNotNull(driver.findElement(
            By.linkText("test_surname test_name test_middle_name")));
        Assert.assertNotNull(driver.findElement(By.linkText("+7(777)777-77-77")));
        driver.findElement(By.linkText("+7(777)777-77-77")).click();
        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("test_name_2");
        driver.findElement(By.id("surname")).clear();
        driver.findElement(By.id("surname")).sendKeys("test_surname_2");
        driver.findElement(By.id("middle_name")).clear();
        driver.findElement(By.id("middle_name")).sendKeys("test_middle_name_2");
        driver.findElement(By.id("phone_number")).clear();
        driver.findElement(By.id("phone_number")).sendKeys("+8(888)888-88-88");
        {
            WebElement dropdown = driver.findElement(By.id("active-service"));
            dropdown.findElement(By.cssSelector("option:nth-child(2)")).click();
        }
        driver.findElement(By.cssSelector("div > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("h2"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(),
                            "test_surname_2 test_name_2 test_middle_name_2");
        Assert.assertEquals(driver.findElement(By.cssSelector("div > p")).getText(),
                            "+8(888)888-88-88");
        Assert.assertNotNull(driver.findElement(By.cssSelector("tr:nth-child(2)")));
        Assert.assertNotNull(driver.findElement(By.cssSelector("div:nth-of-type(3) tr:nth-of-type(2)")));
    }

    @Test(priority = 3)
    void disableServiceForClientTest() {
        driver.get("http://localhost:9090/webapp/");
        driver.findElement(By.id("clients")).click();
        {
            List<WebElement> elements = driver.findElements(By.tagName("td"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.linkText("+8(888)888-88-88")).click();
        driver.findElement(By.id("disable")).click();
        Assert.assertEquals(driver.findElements(By.cssSelector("#active-service tr")).size(), 1);
    }

    @Test(priority = 4)
    void deleteServiceTest() {
        driver.get("http://localhost:9090/webapp/");
        driver.findElement(By.id("services")).click();
        {
            List<WebElement> elements = driver.findElements(By.tagName("td"));
            assert(elements.size() > 0);
        }
        int id = Integer.parseInt(driver.findElement(By.tagName("td")).getText());
        driver.findElement(By.cssSelector("tbody > tr td:nth-child(5)")).click();
        int newId = Integer.parseInt(driver.findElement(
            By.cssSelector("tbody > tr td")).getText());
        Assert.assertNotEquals(id, newId);
    }

    @Test(priority = 5)
    void addServiceTest() {
        driver.get("http://localhost:9090/webapp/");
        driver.findElement(By.id("services")).click();
        {
            List<WebElement> elements = driver.findElements(By.tagName("td"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.id("add_service")).click();
        driver.findElement(By.cssSelector("div > input")).click();
        Assert.assertEquals(driver.getTitle(), "Ошибка");
        driver.findElement(By.id("services-page")).click();
        driver.findElement(By.id("add_service")).click();
        driver.findElement(By.id("name")).sendKeys("test_name");
        driver.findElement(By.id("description")).sendKeys("test_description");
        driver.findElement(By.id("calls_min")).clear();
        driver.findElement(By.id("calls_min")).sendKeys("30");
        driver.findElement(By.id("sms_number")).clear();
        driver.findElement(By.id("sms_number")).sendKeys("20");
        driver.findElement(By.id("internet_gb")).clear();
        driver.findElement(By.id("internet_gb")).sendKeys("10");
        driver.findElement(By.id("cost")).clear();
        driver.findElement(By.id("cost")).sendKeys("100");
        driver.findElement(By.cssSelector("div > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("h2"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "test_name");
        Assert.assertEquals(driver.findElement(By.id("description")).getText(), "test_description");
        Assert.assertEquals(driver.findElement(By.id("calls-sms")).getText(), "30 / 20");
        Assert.assertEquals(driver.findElement(By.id("internet-gb")).getText(), "10");
        Assert.assertEquals(driver.findElement(By.id("cost")).getText(), "100.0");
        driver.findElement(By.id("services-page")).click();
        Assert.assertNotNull(driver.findElement(By.linkText("test_name")));
        driver.findElement(By.cssSelector("tbody tr:nth-child(3) td:nth-child(5)")).click();
    }

    @Test(priority = 6)
    void editServiceTest() {
        driver.get("http://localhost:9090/webapp/");
        driver.findElement(By.id("services")).click();
        {
            List<WebElement> elements = driver.findElements(By.tagName("td"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.cssSelector("tbody tr td:nth-child(6)")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("calls_min")).clear();
        driver.findElement(By.id("sms_number")).clear();
        driver.findElement(By.id("internet_gb")).clear();
        driver.findElement(By.id("cost")).clear();
        driver.findElement(By.cssSelector("div > input")).click();
        Assert.assertEquals(driver.getTitle(), "Ошибка");
        driver.findElement(By.id("services-page")).click();
        driver.findElement(By.cssSelector("tbody tr td:nth-child(6)")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("test_name");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("test_description");
        driver.findElement(By.id("calls_min")).clear();
        driver.findElement(By.id("calls_min")).sendKeys("30");
        driver.findElement(By.id("sms_number")).clear();
        driver.findElement(By.id("sms_number")).sendKeys("20");
        driver.findElement(By.id("internet_gb")).clear();
        driver.findElement(By.id("internet_gb")).sendKeys("10");
        driver.findElement(By.id("cost")).clear();
        driver.findElement(By.id("cost")).sendKeys("100");
        driver.findElement(By.cssSelector("div > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("h2"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "test_name");
        Assert.assertEquals(driver.findElement(By.id("description")).getText(), "test_description");
        Assert.assertEquals(driver.findElement(By.id("calls-sms")).getText(), "30 / 20");
        Assert.assertEquals(driver.findElement(By.id("internet-gb")).getText(), "10");
        Assert.assertEquals(driver.findElement(By.id("cost")).getText(), "100.0");
        driver.findElement(By.id("services-page")).click();
        Assert.assertNotNull(driver.findElement(By.linkText("test_name")));
        driver.findElement(By.linkText("test_name")).click();
        driver.findElement(By.id("edit")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("test_name_2");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("test_description_2");
        driver.findElement(By.id("calls_min")).clear();
        driver.findElement(By.id("calls_min")).sendKeys("300");
        driver.findElement(By.id("sms_number")).clear();
        driver.findElement(By.id("sms_number")).sendKeys("200");
        driver.findElement(By.id("internet_gb")).clear();
        driver.findElement(By.id("internet_gb")).sendKeys("100");
        driver.findElement(By.id("cost")).clear();
        driver.findElement(By.id("cost")).sendKeys("1000");
        driver.findElement(By.cssSelector("div > input")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("h2"));
            assert(elements.size() > 0);
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("h2")).getText(), "test_name_2");
        Assert.assertEquals(driver.findElement(By.id("description")).getText(), "test_description_2");
        Assert.assertEquals(driver.findElement(By.id("calls-sms")).getText(), "300 / 200");
        Assert.assertEquals(driver.findElement(By.id("internet-gb")).getText(), "100");
        Assert.assertEquals(driver.findElement(By.id("cost")).getText(), "1000.0");
    }

    @Test(priority = 7)
    void billTest() {
        driver.get("http://localhost:9090/webapp/");
        driver.findElement(By.id("clients")).click();
        {
            List<WebElement> elements = driver.findElements(By.tagName("td"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.id("add_client")).click();
        driver.findElement(By.id("name")).sendKeys("test");
        driver.findElement(By.id("surname")).sendKeys("test");
        driver.findElement(By.id("middle_name")).sendKeys("test");
        driver.findElement(By.id("phone_number")).sendKeys("+9(999)999-99-99");
        driver.findElement(By.id("bill")).click();
        driver.findElement(By.cssSelector("div > input")).click();
        Assert.assertEquals(driver.findElement(By.id("bill")).getText(), "");
        driver.findElement(By.id("edit")).click();
        driver.findElement(By.cssSelector("div > input")).click();
        Assert.assertNotNull(driver.findElement(By.id("bill")).getText());
        String number = driver.findElement(By.id("bill")).getText();
        driver.findElement(By.id("bill")).click();
        Assert.assertEquals(driver.getTitle(), "Счет " + number);
    }
}