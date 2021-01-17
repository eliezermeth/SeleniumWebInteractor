package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Singleton.
 */
public class DriverUtils
{
    private static WebDriver driver;
    private static Map<String, String> map = new HashMap<>();

    /**
     * Set path and make connection to Firefox driver.  Set timeout time and maximize window.
     */
    public static WebDriver getDriver()
    {
        if (driver == null)
        {
            System.setProperty("webdriver.gecko.driver", "C:\\GeckoDriver\\geckodriver.exe"); // setting the driver executable
            driver = new FirefoxDriver(); // initializing the Firefox driver

            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // applied wait time
            driver.manage().window().maximize(); // maximize window
        }

        return driver;
    }

    /**
     * Open a new tab with the driver.
     * @param url New tab to open.
     */
    public static void openNewTab(String url)
    {
        if (map.size() == 0) // if completely blank
            driver.get(url);
        else
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.open('" + url + "')");
        }

        // add to map
        String[] ar = driver.getWindowHandles().toArray(new String[0]);
        map.put(url, ar[ar.length - 1]);

        goToTab(url);
    }

    /**
     * Go to a specific tab.  If it is not there, it will be created.
     * @param url String.
     */
    public static void goToTab(String url)
    {
        if (map.containsKey(url))
            driver.switchTo().window(map.get(url));
        else
            openNewTab(url);
    }

    /**
     * Close all tabs on the driver.
     */
    public static void closeAllTabs()
    {
        String[] ar = driver.getWindowHandles().toArray(new String[0]);

        for (String a : ar)
        {
            driver.switchTo().window(a);
            driver.close();
        }
    }

    /**
     * Scroll the web page to the bottom.
     */
    public static void scrollPageToBottom()
    {
        JavascriptExecutor js =(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public static void scrollToElement(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void enablePropertyAccess()
    {
        String config = "about:config";

        goToTab(config);

        // accept risk and continue
        driver.findElement(By.id("warningButton")).click();

        // parameter search
        driver.findElement(By.id("about-config-search")).sendKeys("security.fileuri.strict_origin_policy");

        // check status
        if (((driver.findElement(By.xpath("//span[@data-l10n-id='about-config-pref-accessible-value-custom']")).
                findElement(By.xpath(".//span"))).getText()).equals("false"))
            driver.findElement(By.className("button-toggle")).click();
    }

    public static void disablePropertyAccess()
    {
        String config = "about:config";

        goToTab(config);
/*
        // parameter search
        WebElement searchBar = driver.findElement(By.id("about-config-search"));
        searchBar.clear();
        searchBar.sendKeys("security.fileuri.strict_origin_policy");

        // check status
        if (((driver.findElement(By.xpath("//span[@data-l10n-id='about-config-pref-accessible-value-default']")).
                findElement(By.xpath(".//span"))).getText()).equals("true"))
            driver.findElement(By.className("button-toggle")).click();
         */
        driver.findElement(By.className("button-toggle")).click();
        // TODO make way to reliably navigate back
    }
}
