package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverOperations
{
    private static WebDriver driver;
    private static Map<String, String> map = new HashMap<>();

    // Driver creation -----------------------------------------------------------------------
    /**
     * Set path and make connection to Firefox driver.  Set timeout time and maximize window.
     */
    public static WebDriver getFirefoxDriver()
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

    // Tab operations ------------------------------------------------------------------------
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

    // Scrolling operations --------------------------------------------------------------------------
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

    // WebElement operations --------------------------------------------------------------------------
    /**
     * Find an element in the driver by the id.
     * @param id String of id.
     * @return WebElement.
     */
    public static WebElement findById(String id)
    {
        return driver.findElement(By.id(id));
    }

    /**
     * Find an element in the driver by the class name.
     * @param className String of class name.
     * @return WebElement.
     */
    public static WebElement findByClassName(String className)
    {
        return driver.findElement(By.className(className));
    }

    /**
     * Find an element in the driver by the name.
     * @param name String of the name.
     * @return WebElement.
     */
    public static WebElement findByName(String name)
    {
        return driver.findElement(By.name(name));
    }

    /**
     * Find an element in the driver by the text.
     * @param text String of the text to link.
     * @return WebElement.
     */
    public static WebElement findByLinkText(String text)
    {
        return driver.findElement(By.linkText(text));
    }

    // Property access operations ---------------------------------------------------------------------
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
