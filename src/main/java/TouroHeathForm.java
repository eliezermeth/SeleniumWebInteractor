import Utilities.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author Eliezer Meth
 * Start Date: 2020-11-02
 */
public class TouroHeathForm
{
    private WebDriver driver;
    private String URL = "https://tourocollege.az1.qualtrics.com/jfe/form/SV_aWrTvcnfZxCbkgd";
    private String filename = "src/main/java/TouroHealthFormData.txt";

    private String qrDefault = "QR~QID%d~%d";
    private String qidDefault = "QID%d-%d-label";
    private int yes = 1;
    private int no = 2;

    public TouroHeathForm()
    {
        setupDriver(); // set up system

        getPage(); // open web form

        // Go through pages and enter data
        introPage();
        occupationPage();
        personalInfoPage();
        locationPage();
        // COVID-specific pages
        covidExposedPage();
        covidSymptomsPage();
        covidCloseContactPage();

//        driver.close(); // close browser
    }

    public static void main(String[] args)
    {
        new TouroHeathForm();
    }

    /**
     * Set path and make connection to Firefox driver.  Set timeout time and maximize window.
     */
    public void setupDriver()
    {
        System.setProperty("webdriver.gecko.driver", "C:\\GeckoDriver\\geckodriver.exe"); // setting the driver executable
        driver = new FirefoxDriver(); // initializing the Firefox driver

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // applied wait time
        driver.manage().window().maximize(); // maximize window
    }

    /**
     * Open page.
     */
    public void getPage()
    {
        driver.get(URL);
    }

    /**
     * Intro page, just contains information (text) and next button.
     */
    public void introPage()
    {
        next();
    }

    /**
     * Page asks whether student, staff, etc.
     */
    public void occupationPage()
    {
        int pageID = 11;
        int studentLabel = 2;

        driver.findElement(By.id(String.format(qidDefault, pageID, studentLabel))).click();
        next();
    }

    /**
     * Page to enter personal information
     */
    public void personalInfoPage()
    {
        // personal information
        String[] data = getPersonalDataFromFile();

        // to construct IDs
        int pageID = 6;
        int firstName = 1;
        int lastName = 2;
        int email = 4;
        int phoneNumber = 7;

        // enter information
        driver.findElement(By.id(String.format(qrDefault, pageID, firstName))).sendKeys(data[0]); // first name
        driver.findElement(By.id(String.format(qrDefault, pageID, lastName))).sendKeys(data[1]); // last name
        driver.findElement(By.id(String.format(qrDefault, pageID, email))).sendKeys(data[2]); // email
        driver.findElement(By.id(String.format(qrDefault, pageID, phoneNumber))).sendKeys(data[3]); // phone number
        next();
    }

    /**
     * Get personal data from file and return in string array.
     * Data is set up in format:
     *
     * FirstName
     * LastName
     * email
     * phoneNumber
     *
     * @return String array of data.
     */
    public String[] getPersonalDataFromFile()
    {
        return FileUtils.readFile(filename);
    }

    /**
     * Page to input location wished to enter.
     */
    public void locationPage()
    {
        int pageID = 12;

        // locations
        int LCM = 11; // 75-31 150th St, Flushing

        driver.findElement(By.id(String.format(qrDefault, pageID, LCM))).click();
        next();
    }

    /**
     * Page if was exposed to COVID in the past 14 days.
     */
    public void covidExposedPage()
    {
        int pageID = 8;
        driver.findElement(By.id(String.format(qidDefault, pageID, no))).click(); // not exposed
        next();
    }

    /**
     * Page if showing symptoms.
     */
    public void covidSymptomsPage()
    {
        int pageID = 3;
        driver.findElement(By.id(String.format(qidDefault, pageID, no))).click(); // no symptoms
        next();
    }

    /**
     * Page if had close contact with a person showing symptoms in the past 14 days.
     */
    public void covidCloseContactPage()
    {
        int pageID = 5;
        driver.findElement(By.id(String.format(qidDefault, pageID, no))).click(); // no contact
//        next(); // finishes entry
    }

    /**
     * Click next button.
     */
    public void next()
    {
        driver.findElement(By.id("NextButton")).click();
    }
}
