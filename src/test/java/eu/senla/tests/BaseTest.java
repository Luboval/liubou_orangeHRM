package eu.senla.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setup(){
       driver = new ChromeDriver();
       driver.manage().window().maximize();
       System.out.println("Starting");
    }



    @AfterSuite(alwaysRun = true)
    public void tearDown() {

        System.out.println("Stopping");
        driver.quit();

    }
}
