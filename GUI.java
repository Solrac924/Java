/***
 * Developer:       Carlos A Loya
 * Create date:     2/17/2016
 * Last modified:   3/07/2016
 * Purpose:         To automate the end-to-end flow of the Beacon Tech UI with JUnit
 * AMDG
 ***/

package x64;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.fail;

public class GUI
{
    public WebDriver driver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    public String BTechInternal = "https://apps-test.global.dish.com/beacon-ui/products?agentId=carlos.loya&workOrderId=";
    public String BTechDMZ = "https://apps-test.dish.com/beacon-ui/products?agentId=carlos.loya&workOrderId=1000021764893016";

    public long startTime;

    @Before public void setUp() throws Exception
    {
        // set up driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\carlos.loya\\workspace\\chromedriver.exe");

        // turn off notifications
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void openPage() throws Exception
    {
        String pageOFSC = "https://dn.test.etadirect.com/";                                        // URL for OFSC/ETAD page
        System.out.println("About to open page: \n" + BTechDMZ);
        //driver.get(pageOFSC);                                                                      // open page
        driver.get(BTechDMZ);                                                                      // open page
        driver.manage().window().setSize(new Dimension(1100, 800));                                // resize window

        Thread.sleep(2000);
        System.out.println("Current page: \n" + driver.getCurrentUrl() );

        if (driver.getCurrentUrl().contains("oktapreview") )
        {
            loginOkta();
            Thread.sleep(1000);
        }
        if (driver.getCurrentUrl().contains("dns-test.dish.com") )
        {
            loginDNS();
            Thread.sleep(1000);
        }
    }

    public void loginOkta() throws Exception
    {
        // log into Okta
        System.out.println("current URL: " + driver.getCurrentUrl().toString() );
        driver.findElement(By.xpath("//input[@id='user-signin']")).sendKeys("carlos.loya");
        driver.findElement(By.xpath("//input[@id='pass-signin']")).sendKeys("12qw!@QW");
        driver.findElement(By.xpath("//input[@id='signin-button']")).click();
    }

    public void loginDNS() throws Exception
    {
        // log into DNS-test
        System.out.println("current URL: " + driver.getCurrentUrl().toString() );
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("arshad.zaheer@echostar-test.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("D1sh0009");
        driver.findElement(By.xpath("//input[@value='Sign In']")).click();
        Thread.sleep(1000);
    }

    public void openWO() throws Exception
    {
        driver.findElement(By.xpath("//button[@id='settings']")).click();                          // click menu with 4 squares
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'ETAdirect - Mobile')]")).click();        // click ETAdirect - Mobile

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

        try
        {
            driver.findElement(By.className("online-action")).click();                                // click Beacon Tech via className
            //driver.findElement(By.linkText("Beacon") ).click();                                      // click Beacon Tech via linkText
        }
        catch (NoSuchElementException e)
        {
            System.out.println("No Beacon Tech link");
            System.out.println("no such element: ???");
        }

        Thread.sleep(1000);

        driver.manage().window().setSize(new Dimension(1100, 800));                                // set browser size
        for(String winHandle : driver.getWindowHandles())
        {
            driver.switchTo().window(winHandle);
        }

        Thread.sleep(5000);
    }

    @Test public void orderItems() throws Exception
    {
        startTime = System.currentTimeMillis();
        System.out.println("\ntimer started");
        openPage();
        Integer delayTime = 5000;
        System.out.println("\ntime to catalog page: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
        Thread.sleep(delayTime);
        driver.findElement(By.xpath("//div[1]/div[2]/div/div/button[2]")).click();                    // + Bluetooth Headphones Bears
        Thread.sleep(delayTime);
        driver.findElement(By.xpath("//div[@id='175740241']/div/div[2]/div[2]/div/button[1]")).click(); // - Bluetooth Headphones Bears
        Thread.sleep(delayTime);
        driver.findElement(By.xpath("//a[contains(text(),'Bluetooth Headphones Black')]")).click();   // details of Bluetooth Headphones Black
        Thread.sleep(delayTime);
        driver.findElement(By.xpath("//button[2]")).click();                                          // + Bluetooth Headphones Black (in details)
        Thread.sleep(delayTime);
        driver.findElement(By.xpath("//div[3]/div/ul/li/a")).click();                                 // Store (Product Catalog page)
        Thread.sleep(delayTime);
        new Select(driver.findElement(By.xpath("//div[@id='175740241']/div/div[6]/div[2]/div/select")))
                .selectByVisibleText("3");                                                            // select 3 "Bluetooth Headphones Patriots" in dropdown
        Thread.sleep(delayTime);
        driver.findElement(By.xpath("//*[@id=\"175740521\"]/div/div[14]/div[2]/div/button[2]")).click();         // + Rmt Cntl 52.0 (Hopper)
        Thread.sleep(delayTime);
        driver.findElement(By.xpath("//span[2]/button")).click();                                     // Review -> Shopping Cart Items
        Thread.sleep(delayTime);
        driver.findElement(By.xpath("//div[2]/div/div/a")).click();					                  // Continue -> Order Summary

        Thread.sleep(4000);
        driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();     	              // Continue -> Payment Form
        Thread.sleep(2000);

        try
        {
            driver.findElement(By.xpath("//input[@id='creditDebitCardNumberEntry-number']")).sendKeys("4111111111111111");      // enter card #
            new Select(driver.findElement(By.xpath("//select[@id='expirationDateEntry-month']"))).selectByVisibleText("11");    // select month from dropdown
            new Select(driver.findElement(By.xpath("//select[@name='creditDebitCardYear']"))).selectByVisibleText("2017");      // select year from dropdown
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Exception: " + e);
        }
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();     	            // Continue -> Signature Capture
        Thread.sleep(2000);

        // draw signature
        Actions thisAction = new Actions(driver);
        WebElement signaturePad = driver.findElement(By.cssSelector("canvas"));
        thisAction.moveToElement(signaturePad);
        thisAction.click(signaturePad);
        thisAction.dragAndDropBy(signaturePad,20, 30);
        thisAction.clickAndHold(signaturePad);
        thisAction.moveToElement(signaturePad, 20, 20);
        thisAction.release();
        thisAction.perform();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[contains(text(),'Submit')]")).click();	     	            // Submit -> Payment

        // loop until payment confirmation container exists or until 20 seconds
        Long processingStarted = System.currentTimeMillis();
        while (((System.currentTimeMillis() - processingStarted) < 20 *1000.0) && (! driver.findElement(By.xpath("//*[@id=\"confirmationContainer\"]" )).isDisplayed() ) )
        {
            // do nothing while waiting
        }
        System.out.println("\npayment processing time: " + (System.currentTimeMillis() - processingStarted)/1000.0 + "s");

        System.out.println("\nfinal message: \n" + driver.findElement(By.xpath("//*[@id=\"confirmationContainer\"]" )).getText() );

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"confirmationContainer\"]" )).getText().contains("Success") );
        Thread.sleep(2000);

    } // end @Test

    @After public void tearDown() throws Exception
    {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString))
        {
            fail(verificationErrorString);

        }
    }

    private boolean isElementPresent(By by)
    {
        try
        {
            driver.findElement(by);
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }

    private boolean isAlertPresent()
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException e)
        {
            return false;
        }
    }

    private String closeAlertAndGetItsText()
    {
        try
        {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert)
            {
                alert.accept();
            }
            else
            {
                alert.dismiss();
            }
            return alertText;
        } // end try
        finally
        {
            acceptNextAlert = true;
        } // finally

    } // end method: closeAlertAndGetItsText

} // end class: GUI