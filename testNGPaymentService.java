/***
 * Developer:   carlos.loya
 * Date:        3/21/2016
 * Purpose:     Tests the Beacon Tech REST service adapter Payment Service using TestNG
 * ad majorem Dei gloriam
 ***/

package x64;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class testNGPaymentService
{
    long startTime;

    @BeforeClass
    public void setUp()
    {
        // code that will be invoked when this test is instantiated
        startTime = System.currentTimeMillis();
    }

    @Test(groups = { "PaymentService" } )
    public void PymtSvc01PostByVisa() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        String testPaymentService01 = "" + testPaymentService.postCCPymt("4005550000000019", "12", "2020", 200);
        assert testPaymentService01.contains("200") : "Expected";
    }

    @Test(groups = { "PaymentService" } )
    public void PymtSvc02PostByMastercard() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        String testPaymentService02 = "" + testPaymentService.postCCPymt("5442981111111114", "11", "2019", 200);
        assert testPaymentService02.contains("200") : "Expected";
    }

    @Test(groups = { "PaymentService" } )
    public void PymtSvc03PostByDiscover() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        String testPaymentService03 = "" + testPaymentService.postCCPymt("6011000991300009", "10", "2018", 200);
        assert testPaymentService03.contains("200") : "Expected";
    }

    @Test(groups = { "PaymentService" } )
    public void PymtSvc04PostByAmex() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        String testPaymentService04 = "" + testPaymentService.postCCPymt("373235387881007", "09", "2017", 200);
        assert testPaymentService04.contains("200") : "Expected";
    }

    @Test(groups = { "PaymentService" } )
    public void PymtSvc05PostByEFT1() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        String testPaymentService05 = "" + testPaymentService.postEFTPymt("8046909529861370", "5425512449", "102000076");
        assert testPaymentService05.contains("200") : "Expected";
    }

    @Test(groups = { "PaymentService" } )
    public void PymtSvc06PostByEFT2() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        String testPaymentService06 = "" + testPaymentService.postEFTPymt("8046909455439555", "6648722121",  "071000013");
        assert testPaymentService06.contains("200") : "Expected";
    }

    @Test(groups = { "PaymentService" } )
    public void PymtSvc07PostByEFT3() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        String testPaymentService07 = "" + testPaymentService.postEFTPymt("8046919110629714", "0012345689", "302070149");
        assert testPaymentService07.contains("200") : "Expected";
    }

    @Test(groups = { "PaymentService" } )
    public void PymtSvc08PostByEFT4() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        String testPaymentService08 = "" + testPaymentService.postEFTPymt("8046909789194447", "1234567890",  "322271627");
        assert testPaymentService08.contains("200") : "Expected";
    }

    @Test(groups = { "PaymentService" } )
    public void PymtSvc09LookupLastSuccessful1() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        Assert.assertEquals(testPaymentService.lookupLastSuccessful("8046909529861370"), true);
    }

    @Test(groups = { "PaymentService" } )
    public void PymtSvc10LookupLastSuccessful2() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        Assert.assertEquals(testPaymentService.lookupLastSuccessful("8046909807658498"), true);
    }

    @AfterClass
    public void totalTime()
    {
        // code that will be invoked when this test is instantiated
        System.out.println("\ntotal time: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
    }
} // class
