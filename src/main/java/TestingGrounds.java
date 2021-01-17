import Utilities.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.*;

public class TestingGrounds
{
    public static void main(String[] args)
    {
        String config = "about:config";
        String stackoverflow = "https://stackoverflow.com";
        Map<String, String> map = new HashMap<>();

        WebDriver driver = DriverUtils.getDriver();
        DriverUtils.enablePropertyAccess();
        DriverUtils.disablePropertyAccess();
    }
}
