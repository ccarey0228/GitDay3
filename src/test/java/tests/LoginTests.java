package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
//Login tests are maintained here.
// //without Page object approach
public class LoginTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void loginTest1() {

        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password") ).sendKeys("test" + Keys.ENTER);

        Assert.assertEquals(driver.getTitle(), "Web Orders");

    }

    @Test
    public void logOutTest() {

        loginTest1();

        driver.findElement(By.id("ctl00_logout")).click();

    }

    @Test
    public void negativeLoginTest1() {

        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Testers");
        driver.findElement(By.id("ctl00_MainContent_password") ).sendKeys("test" + Keys.ENTER);

        String errorMessage = driver.findElement(By.id("ctl00_MainContent_status")).getText();

        Assert.assertEquals(errorMessage, "Invalid Login or Password.");

    }

   @AfterMethod
    public void cleanUp() {

        driver.quit();

   }



}
