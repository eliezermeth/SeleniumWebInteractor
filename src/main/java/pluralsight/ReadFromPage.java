package pluralsight;

import Utilities.DriverUtils;
import Utilities.DriverOperations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.commons.net.telnet.TelnetCommand.EC;

public class ReadFromPage
{
    private WebDriver driver;
    private String email;

    private String p1URL = "https://app.pluralsight.com/paths/skill/csharp"; // starting url

    public static void main(String[] args)
    {
        new ReadFromPage();
    }

    public ReadFromPage()
    {
        setupVariables();
        doPluralsightTest();
    }

    private void setupVariables()
    {
        driver = DriverUtils.getDriver();
        //DriverUtils.enablePropertyAccess();
        email = GetNewEmail.getNewEmail();

//        DriverUtils.enablePropertyAccess();


    }

    private void doPluralsightTest()
    {
        // open web page
        DriverUtils.openNewTab(p1URL);

        // click for free iq test
        driver.findElement(By.className("ps_skill--btn")).click();

        registrationForm();
    }

    private void registrationForm()
    {
        // input personal information
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("firstname")).sendKeys("Anon");
        driver.findElement(By.name("lastname")).sendKeys("Ymous");

        Select selectDropdown = new Select(driver.findElement(By.name("country")));
        selectDropdown.selectByVisibleText("United States");

        // scroll down and click checkbox
        WebElement el = driver.findElement(By.name("optInBox"));
        DriverUtils.scrollToElement(el);
        el.click();

        // reinput email if failed
        while(driver.findElement(By.name("email")).getAttribute("style").equals("border: 2px solid red;"))
        {
            driver.findElement(By.name("email")).sendKeys(GetNewEmail.getEmail());
        }
        /*
        // scroll down and click button to get started
        el = driver.findElement(By.xpath("//a[@class='button' and text()='Get started']"));
        DriverUtils.scrollToElement(el);
        el.click();
         */

        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='button' and text()='Get started']"))).click();
    }
}
