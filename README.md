**Selenium tutorials:**
* [Selenium documentation][1]
* [Javatpoint][2]
* [Tools QA][4]

**Easy explanations:**
* [Stack Abuse][3]

**Most recent GeckoDriver release**<p>
This program requires GeckoDriver.  All programs are currently made to run with Mozilla Firefox.
  The most recent GeckoDriver release for Mozilla can be found by searching
"github mozilla geckodriver releases" or by clicking [here][5].<p>
The file should be unzipped into the main directory of the desktop, so the file path
to the executable is "C:\\GeckoDriver\\geckodriver.exe".

**For use of TouroHealthForm**<p>
The Java class is currently programmed to input information into the [Touro COVID health form][6].  It assumes the
person is a student who wishes to enter the Lander College for Men building at 75-31 150th St, Flushing, and has not had
symptoms within the past 14 days, nor contact with a person showing symptoms in the past 14 days.<p>
The only actions required are:
 * The student must input the relevant information into TouroHealthFormData.txt, in the format shown there.
* In TouroHealthForm.java, the last line can be un-commented out in the method `covidCloseContactPage()` to allow the 
program to auto-complete the file.  The last line can also be un-commented out in the constructor to auto-close the 
browser after the form is completed.

[1]: https://www.selenium.dev/documentation/en/
[2]: https://www.javatpoint.com/selenium-tutorial
[3]: https://stackabuse.com/web-browser-automation-with-selenium-and-java/
[4]: https://www.toolsqa.com/selenium-tutorial/
[5]: https://github.com/mozilla/geckodriver/releases
[6]: https://tourocollege.az1.qualtrics.com/jfe/form/SV_aWrTvcnfZxCbkgd