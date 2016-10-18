/***
 * Developer:   carlos.loya
 * Date:        3/09/2016
 * Purpose:     Tests the Beacon Tech REST service adapter Account Service using TestNG
 * ad majorem Dei gloriam
 ***/

package x64;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class testNGAccountService
{
    long startTime;

    @BeforeClass
    public void setUp()
    {
        // code that will be invoked when this test is instantiated
        startTime = System.currentTimeMillis();
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP01() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        testAccountService testAccountServiceGAP01 = new testAccountService("8046909808485230");
        //System.out.println("testAccountService.respAccountId: " +  testAccountServiceGAP01.respAccountId);
        //System.out.println("testAccountService.respCustomerId: " +  testAccountServiceGAP01.respCustomerId);
        //System.out.println("testAccountService.respPrimaryStatus: " +  testAccountServiceGAP01.respPrimaryStatus);
        //System.out.println("testAccountService.respEmailAddr: " +  testAccountServiceGAP01.respEmailAddr);
        //System.out.println("testAccountService.respCard_payment_allowed: " +  testAccountServiceGAP01.respCard_payment_allowed);
        //System.out.println("testAccountService.respEFT_payment_allowed: " +  testAccountServiceGAP01.respEFT_payment_allowed);

        //System.out.println("testAccountService.dbAccountId: " +  testAccountServiceGAP01.dbAccountId);
        //System.out.println("testAccountService.dbCustomerId: " +  testAccountServiceGAP01.dbCustomerId);
        //System.out.println("testAccountService.dbPrimaryStatus: " +  testAccountServiceGAP01.dbPrimaryStatus);
        //System.out.println("testAccountService.dbEmail: " +  testAccountServiceGAP01.dbEmail);

        assert testAccountServiceGAP01.respAccountId.equals(testAccountServiceGAP01.dbAccountId) : "Expected";
        assert testAccountServiceGAP01.respCustomerId.equals(testAccountServiceGAP01.dbCustomerId) : "Expected";
        assert testAccountServiceGAP01.respPrimaryStatus.equals(testAccountServiceGAP01.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP01.respEmailAddr.equals(testAccountServiceGAP01.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP02() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP02 = new testAccountService("8046909078203057");

        assert testAccountServiceGAP02.respAccountId.equals(testAccountServiceGAP02.dbAccountId) : "Expected";
        assert testAccountServiceGAP02.respCustomerId.equals(testAccountServiceGAP02.dbCustomerId) : "Expected";
        assert testAccountServiceGAP02.respPrimaryStatus.equals(testAccountServiceGAP02.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP02.respEmailAddr.equals(testAccountServiceGAP02.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP03() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP03 = new testAccountService("8046919110077609");

        assert testAccountServiceGAP03.respAccountId.equals(testAccountServiceGAP03.dbAccountId) : "Expected";
        assert testAccountServiceGAP03.respCustomerId.equals(testAccountServiceGAP03.dbCustomerId) : "Expected";
        assert testAccountServiceGAP03.respPrimaryStatus.equals(testAccountServiceGAP03.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP03.respEmailAddr.equals(testAccountServiceGAP03.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP04() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP04 = new testAccountService("8046909900054850");

        assert testAccountServiceGAP04.respAccountId.equals(testAccountServiceGAP04.dbAccountId) : "Expected";
        assert testAccountServiceGAP04.respCustomerId.equals(testAccountServiceGAP04.dbCustomerId) : "Expected";
        assert testAccountServiceGAP04.respPrimaryStatus.equals(testAccountServiceGAP04.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP04.respEmailAddr.equals(testAccountServiceGAP04.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP05() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP05 = new testAccountService("8072100539815005");

        assert testAccountServiceGAP05.respAccountId.equals(testAccountServiceGAP05.dbAccountId) : "Expected";
        assert testAccountServiceGAP05.respCustomerId.equals(testAccountServiceGAP05.dbCustomerId) : "Expected";
        assert testAccountServiceGAP05.respPrimaryStatus.equals(testAccountServiceGAP05.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP05.respEmailAddr.equals(testAccountServiceGAP05.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP06() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        testAccountService testAccountServiceGAP06 = new testAccountService("8072400060355748");

        assert testAccountServiceGAP06.respAccountId.equals(testAccountServiceGAP06.dbAccountId) : "Expected";
        assert testAccountServiceGAP06.respCustomerId.equals(testAccountServiceGAP06.dbCustomerId) : "Expected";
        assert testAccountServiceGAP06.respPrimaryStatus.equals(testAccountServiceGAP06.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP06.respEmailAddr.equals(testAccountServiceGAP06.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP07() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP07 = new testAccountService("8072200010051722");

        assert testAccountServiceGAP07.respAccountId.equals(testAccountServiceGAP07.dbAccountId) : "Expected";
        assert testAccountServiceGAP07.respCustomerId.equals(testAccountServiceGAP07.dbCustomerId) : "Expected";
        assert testAccountServiceGAP07.respPrimaryStatus.equals(testAccountServiceGAP07.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP07.respEmailAddr.equals(testAccountServiceGAP07.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP08() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP08 = new testAccountService("8072500012833757");

        assert testAccountServiceGAP08.respAccountId.equals(testAccountServiceGAP08.dbAccountId) : "Expected";
        assert testAccountServiceGAP08.respCustomerId.equals(testAccountServiceGAP08.dbCustomerId) : "Expected";
        assert testAccountServiceGAP08.respPrimaryStatus.equals(testAccountServiceGAP08.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP08.respEmailAddr.equals(testAccountServiceGAP08.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP09() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP09 = new testAccountService("8072600550501103");

        assert testAccountServiceGAP09.respAccountId.equals(testAccountServiceGAP09.dbAccountId) : "Expected";
        assert testAccountServiceGAP09.respCustomerId.equals(testAccountServiceGAP09.dbCustomerId) : "Expected";
        assert testAccountServiceGAP09.respPrimaryStatus.equals(testAccountServiceGAP09.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP09.respEmailAddr.equals(testAccountServiceGAP09.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP10() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP10 = new testAccountService("8072800329025034");

        assert testAccountServiceGAP10.respAccountId.equals(testAccountServiceGAP10.dbAccountId) : "Expected";
        assert testAccountServiceGAP10.respCustomerId.equals(testAccountServiceGAP10.dbCustomerId) : "Expected";
        assert testAccountServiceGAP10.respPrimaryStatus.equals(testAccountServiceGAP10.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP10.respEmailAddr.equals(testAccountServiceGAP10.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP11() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP11 = new testAccountService("8046707010980190");

        assert testAccountServiceGAP11.respAccountId.equals(testAccountServiceGAP11.dbAccountId) : "Expected";
        assert testAccountServiceGAP11.respCustomerId.equals(testAccountServiceGAP11.dbCustomerId) : "Expected";
        assert testAccountServiceGAP11.respPrimaryStatus.equals(testAccountServiceGAP11.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP11.respEmailAddr.equals(testAccountServiceGAP11.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP12() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP12 = new testAccountService("8046470010222879");

        assert testAccountServiceGAP12.respAccountId.equals(testAccountServiceGAP12.dbAccountId) : "Expected";
        assert testAccountServiceGAP12.respCustomerId.equals(testAccountServiceGAP12.dbCustomerId) : "Expected";
        assert testAccountServiceGAP12.respPrimaryStatus.equals(testAccountServiceGAP12.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP12.respEmailAddr.equals(testAccountServiceGAP12.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP13() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,InterruptedException
    {
        testAccountService testAccountServiceGAP13 = new testAccountService("8046707080253643");

        assert testAccountServiceGAP13.respAccountId.equals(testAccountServiceGAP13.dbAccountId) : "Expected";
        assert testAccountServiceGAP13.respCustomerId.equals(testAccountServiceGAP13.dbCustomerId) : "Expected";
        assert testAccountServiceGAP13.respPrimaryStatus.equals(testAccountServiceGAP13.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP13.respEmailAddr.equals(testAccountServiceGAP13.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP14() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP14 = new testAccountService("8046490010613661");

        assert testAccountServiceGAP14.respAccountId.equals(testAccountServiceGAP14.dbAccountId) : "Expected";
        assert testAccountServiceGAP14.respCustomerId.equals(testAccountServiceGAP14.dbCustomerId) : "Expected";
        assert testAccountServiceGAP14.respPrimaryStatus.equals(testAccountServiceGAP14.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP14.respEmailAddr.equals(testAccountServiceGAP14.dbEmail) : "Expected";
    }

    @Test(groups = { "AccountService" } )
    public void accountServiceGAP15() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException
    {
        testAccountService testAccountServiceGAP15 = new testAccountService("8046160016008360");

        assert testAccountServiceGAP15.respAccountId.equals(testAccountServiceGAP15.dbAccountId) : "Expected";
        assert testAccountServiceGAP15.respCustomerId.equals(testAccountServiceGAP15.dbCustomerId) : "Expected";
        assert testAccountServiceGAP15.respPrimaryStatus.equals(testAccountServiceGAP15.dbPrimaryStatus) : "Expected";
        assert testAccountServiceGAP15.respEmailAddr.equals(testAccountServiceGAP15.dbEmail) : "Expected";

    }

    @AfterClass
    public void totalTime()
    {
        // code that will be invoked when this test is instantiated
        System.out.println("\ntotal time: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
    }
} // class
