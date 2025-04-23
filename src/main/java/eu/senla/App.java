package eu.senla;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {

    public static void main(String[] args){

        WebDriver driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println("Browser is opened");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
