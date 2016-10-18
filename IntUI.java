/***
 * Developer:       Carlos A Loya
 * Create date:     2/17/2016
 * Last modified:   4/25/2016
 * Purpose:         To automate the end-to-end flow of the Beacon Tech UI with testNG
 * ad majorem Dei gloriam
 ***/

package x64;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class IntUI
{
    public static WebDriver driver;
    private boolean acceptNextAlert = true;
    private static StringBuffer verificationErrors = new StringBuffer();
    public static String BTechInternal = "https://apps-int.global.dish.com/beacon-ui/products?agentId=carlos.loya&workOrderId=";
    public static String BTechDMZ = "https://apps-int.dish.com/beacon-ui/products?agentId=carlos.loya&workOrderId=";

    public static long startTime;
    public static String acctNumber = "";

    @BeforeClass
    public static void setUp() throws Exception
    {
        // set up driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\carlos.loya\\workspace\\chromedriver.exe");

        // turn off notifications
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void openPage() throws Exception
    {
        String openTC = getOpenWOs.getIntOpenTC();
        System.out.println("openTC: " + openTC );

        System.out.println("\nopening:");
        System.out.println(BTechInternal + openTC);
        // BTechDMZ BTechInternal
        driver.get(BTechDMZ + openTC);                                                        // open page
        driver.manage().window().setSize(new Dimension(1100, 800));                           // resize window
        Thread.sleep(2000);
        System.out.println("current page:");
        System.out.println(driver.getCurrentUrl() );
    }

    @Test(groups = { "Happy Flow" } )
    public static void orderItems() throws Exception
    {
        startTime = System.currentTimeMillis();
        openPage();
        System.out.println("current URL: \n" + driver.getCurrentUrl().toString() );

        System.out.println("\ntime to catalog page: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
        Thread.sleep(2000);
        try
        {
            driver.findElement(By.xpath("//*[@id=\"175740241\"]/div/div[2]/div[2]/div/button[2]")).click();  // + Bluetooth Headphones Bears
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Product Catalog down");
        }
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"175740241\"]/div/div[2]/div[2]/div/button[1]")).click();      // - Bluetooth Headphones Bears
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Joey Bluetooth Adapter')]")).click();              // details of Joey Bluetooth Adapter
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[2]")).click();                                                 // + Joey Bluetooth Adapter (in details)
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[3]/div/ul/li/a")).click();                                        // Store (Product Catalog page)
        Thread.sleep(1000);

        new Select(driver.findElement(By.xpath("//*[@id=\"175740241\"]/div/div[6]/div[2]/div/select")))
                .selectByVisibleText("3");                                                                   // select 3 "Bluetooth Headphones Patriots" in dropdown
        Thread.sleep(1000);

        Select comboBox = new Select(driver.findElement(By.xpath("//*[@id=\"175740241\"]/div/div[6]/div[2]/div/select") ) );
        String selectedComboValue = comboBox.getFirstSelectedOption().getText();
        Assert.assertTrue(comboBox.getFirstSelectedOption().getText().contains("3") );

        driver.findElement(By.xpath("//*[@id=\"175740521\"]/div/div[9]/div[2]/div/button[2]")).click();      // + Rmt Cntl 52.0 (Hopper)
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[2]/button")).click();                                            // Review -> Shopping Cart Items
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[2]/div/div/a")).click();					                         // Continue -> Order Summary

        Thread.sleep(4000);
        driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();     	                     // Continue -> Payment Form
        Thread.sleep(2000);

        try
        {
            driver.findElement(By.xpath("//input[@id='creditDebitCardNumberEntry-number']")).sendKeys("4111111111111111");      // enter card #
            new Select(driver.findElement(By.xpath("//select[@id='expirationDateEntry-month']"))).selectByVisibleText("11");    // select month from dropdown
            new Select(driver.findElement(By.xpath("//select[@name='creditDebitCardYear']"))).selectByVisibleText("2017");      // select year from dropdown
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();     	            // Continue -> Signature Capture
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Cannot see payment options!");
        }

        try
        {
            // draw signature
            Actions thisAction = new Actions(driver);
            WebElement signaturePad = driver.findElement(By.cssSelector("canvas"));
            thisAction.moveToElement(signaturePad);
            thisAction.click(signaturePad);
            thisAction.dragAndDropBy(signaturePad, 20, 30);
            thisAction.clickAndHold(signaturePad);
            thisAction.moveToElement(signaturePad, 20, 20);
            thisAction.release();
            thisAction.perform();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//a[contains(text(),'Submit')]")).click();                        // Submit -> Payment
        }
        catch (NoSuchElementException e)
        {
            System.out.println("No signature canvas!");
        }

        // loop until paymentContainer exists or until 30 seconds
        Long processingStarted = System.currentTimeMillis();
        while (((System.currentTimeMillis() - processingStarted) < 30 *1000.0) && (driver.findElement(By.xpath("//*[@id=\"paymentContainer\"]" )).getText().contains("Processing") ) )
        {
            // do nothing while waiting
        }
        System.out.println("\npayment processing time: " + (System.currentTimeMillis() - processingStarted)/1000.0 + "s");

        //Thread.sleep(4000);

        System.out.println("\nfinal message: \n" + driver.findElement(By.xpath("//*[@id=\"paymentContainer\"]" )).getText() );
        if (driver.findElement(By.xpath("//*[@id=\"paymentContainer\"]" ) ).getText().isEmpty() )
        // //*[@id="paymentContainer"]/div/div/div[1]
        {
            System.out.println("<none>");
        }

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"paymentContainer\"]/div/div" )).getText().contains("Success") );
        Thread.sleep(2000);

    } // end @Test

    @Test(groups = { "Happy Flow" } )
    public void validateDOMO()  throws SQLException,IOException,InterruptedException
    {
        // capture acct# via selenium
        acctNumber = driver.findElement(By.className("headerUserInfo-accountId") ).getText();
        System.out.println("\nacctNumber: " + acctNumber);

        // from acct# get latest WO#
        String custOrdID = DOMO.intAcctNumToCustOrdID(acctNumber);
        System.out.println("custOrdID: " + custOrdID);

        String products = DOMO.intGetProducts(custOrdID);

        // from  CUSTOMER_ORDER_ID perform REST GET & validate productIds from orderLines
        Assert.assertTrue(products.contains("Bluetooth Headphones Black") );
        Assert.assertTrue(products.contains("4:") );
        Assert.assertTrue(products.contains("32") );
        Assert.assertTrue(products.contains("Bluetooth Headphones Patriots") );
        Assert.assertTrue(products.contains("EG4") );
        Assert.assertTrue(products.contains("EA3") );
        Assert.assertTrue(products.contains("Rmt Cntl 52.0 (Hopper)") );
        Assert.assertTrue(products.contains("DF5") );
    }

    @AfterClass
    public static void tearDown() throws Exception
    {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString))
        {
            //fail(verificationErrorString);

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