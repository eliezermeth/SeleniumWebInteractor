package pluralsight;

import Utilities.DriverOperations;
import Utilities.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class GetNewEmail
{
    private static WebDriver driver;
    private static String URL_10minuteemail = "https://10minutemail.net/";
    private static String email = null;

    public static void main(String[] args)
    {
        getNewEmail();
    }

    public static String getNewEmail()
    {
        if (driver == null)
            driver = DriverOperations.getFirefoxDriver();

        DriverOperations.goToTab(URL_10minuteemail);

        if (email != null) // email was invalid
            driver.findElement(By.linkText(" Get another e-mail address.(Current E-mail will expire.)")).click();

        WebElement weEmail = driver.findElement(By.id("fe_text"));
        email = weEmail.getAttribute("value");

        return getEmail();
    }

    public static String getEmail()
    {
        if (email == null)
            return getNewEmail();

        System.out.println(email);
        return email;
    }
}
