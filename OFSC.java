/***
 * Developer:   Carlos A Loya
 * Date:        3/04/2016
 * Purpose:     Functions in Oracle Field Service Cloud (OFSC)
 * AMDG
 ***/

package x64;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class OFSC
{
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    public String pageOFSC = "https://dn.test.etadirect.com/";

    @Before public void setUp() throws Exception
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\carlos.loya\\workspace\\chromedriver.exe");
        //driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test public void openBeaconTech() throws Exception
    {
        openPage();
        openWO();

        Thread.sleep(2000);
    }

    public void openPage() throws Exception
    {
        driver.get(pageOFSC);
        System.out.println(pageOFSC);
        Thread.sleep(2000);

        // open Beacon page
        if (getCurrUrl().contains("dns-test.dish.com") )
        {
            login();
            Thread.sleep(1000);
        }
    }

    public String getCurrUrl() throws Exception
    {
        // find out whether the Okta page populated
        return driver.getCurrentUrl();
    }

    public void login() throws Exception
    {
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("arshad.zaheer@echostar-test.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("D1sh0009");
        driver.findElement(By.xpath("//input[@value='Sign In']")).click();
        Thread.sleep(1000);
    }

    public void openWO() throws Exception
    {
        driver.findElement(By.xpath("//button[@id='settings']")).click();                          // click menu with 4 squares
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'ETAdirect - Mobile')]")).click();

        for(String winHandle : driver.getWindowHandles())                                          // prepare for new tab
        {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(7000);

        try
        {
            driver.findElement(By.className("main-field") ).click();                               // enter the work order
        }
        catch (NoSuchElementException e)
        {
            System.out.println("No work orders are assigned");
            System.out.println("no such element: Unable to locate element: {\"method\":\"class name\",\"selector\":\"main-field\"}" );
        }

        Thread.sleep(1000);

        driver.findElement(By.className("online-action") ).click();                                // click Beacon Tech
        Thread.sleep(1000);
        //driver.findElement(By.linkText("Beacon") ).click();                                      // click Beacon Tech

        driver.manage().window().setSize(new Dimension(1100, 800));                                // set browser size
        for(String winHandle : driver.getWindowHandles())
        {
            driver.switchTo().window(winHandle);
        }

        Thread.sleep(5000);
    }

    @After public void tearDown() throws Exception
    {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString))
        {
            fail(verificationErrorString);

        }
    }
} // OFSC
