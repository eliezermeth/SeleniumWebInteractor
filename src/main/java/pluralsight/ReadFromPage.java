package pluralsight;

import Utilities.DriverOperations;
import Utilities.DriverOperations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Utilities.DriverOperations.*;
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
        driver = getFirefoxDriver();
        //DriverOperations.enablePropertyAccess();
        email = GetNewEmail.getNewEmail();
    }

    private void doPluralsightTest()
    {
        // open web page
        openNewTab(p1URL);

        /*
        Error at this point:

        JavaScript error: https://beacon.sftoaa.com/v2/sendem.js, line 1: SecurityError:
        Permission denied to access property "document" on cross-origin object
         */

        // click for free iq test
        findByClassName("ps_skill--btn").click();

        registrationForm();
    }

    private void registrationForm()
    {
        // input personal information
        findByName("email").sendKeys(email);
        findByName("firstname").sendKeys("Anon");
        findByName("lastname").sendKeys("Ymous");

        Select selectDropdown = new Select(findByName("country"));
        selectDropdown.selectByVisibleText("United States");

        // scroll down and click checkbox
        WebElement el = findByName("optInBox");
        DriverOperations.scrollToElement(el);
        el.click();

        // reinput email if failed
        while(findByName("email").getAttribute("style").equals("border: 2px solid red;"))
        {
            findByName("email").sendKeys(GetNewEmail.getEmail());
        }
        /*
        // scroll down and click button to get started
        el = driver.findElement(By.xpath("//a[@class='button' and text()='Get started']"));
        DriverOperations.scrollToElement(el);
        el.click();
         */

        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='button' and text()='Get started']"))).click();
    }
}
