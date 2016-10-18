/***
 * Developer:       Carlos A Loya
 * Date created:    3/24/2016
 * Date modified:   3/28/2016
 * Purpose:         Tests the Beacon Tech REST service adapter Order Service using TestNG
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

public class testNGOrderService
{
    long startTime;
    testOrderService testOrderService01;
    testOrderService testOrderService02;
    testOrderService testOrderService03;
    String thisOrder = "";

    @BeforeClass
    public void setUp()
    {
        // code that will be invoked when this test is instantiated
        startTime = System.currentTimeMillis();
    }

    @Test(groups = { "OrderService" } )
    public void NC01OrderID() throws ClassNotFoundException,SQLException,IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        //System.out.println("getOpenTC: " + getOpenWOs.getOpenNC() );
        //System.out.println("getOpenTC: " + getOpenWOs.getOpenTC() );
        //System.out.println("getOpenSC: " + getOpenWOs.getOpenSC() );
        thisOrder = getOpenWOs.getOpenNC();
        System.out.println("thisOrder: " + thisOrder);
        testOrderService testOrderService01 = new testOrderService(thisOrder);

        assert testOrderService01.respOrderId.contentEquals(testOrderService01.dbOrderId );
        //assert testOrderService01.respAccountId.contentEquals(testOrderService01.dbAccountId );
        //assert testOrderService01.respScheduleDate.contentEquals(testOrderService01.dbScheduleDate );
        //assert testOrderService01.respType.substring(0,3).equalsIgnoreCase(testOrderService01.dbType.substring(0,3) );
        //assert testOrderService01.respStatus.substring(0,1).contentEquals(testOrderService01.dbStatus.substring(0,1) );
    }

    @Test(groups = { "OrderService" } )
    public void NC02AccountID() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService01.respOrderId.contentEquals(testOrderService01.dbOrderId );
        assert testOrderService01.respAccountId.contentEquals(testOrderService01.dbAccountId );
        //assert testOrderService01.respScheduleDate.contentEquals(testOrderService01.dbScheduleDate );
        //assert testOrderService01.respType.substring(0,3).equalsIgnoreCase(testOrderService01.dbType.substring(0,3) );
        //assert testOrderService01.respStatus.substring(0,1).contentEquals(testOrderService01.dbStatus.substring(0,1) );
    }

    @Test(groups = { "OrderService" } )
    public void NC03ScheduleDate() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService01.respOrderId.contentEquals(testOrderService01.dbOrderId );
        //assert testOrderService01.respAccountId.contentEquals(testOrderService01.dbAccountId );
        assert testOrderService01.respScheduleDate.contentEquals(testOrderService01.dbScheduleDate );
        //assert testOrderService01.respType.substring(0,3).equalsIgnoreCase(testOrderService01.dbType.substring(0,3) );
        //assert testOrderService01.respStatus.substring(0,1).contentEquals(testOrderService01.dbStatus.substring(0,1) );
    }

    @Test(groups = { "OrderService" } )
    public void NC04Type() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService01.respOrderId.contentEquals(testOrderService01.dbOrderId );
        //assert testOrderService01.respAccountId.contentEquals(testOrderService01.dbAccountId );
        //assert testOrderService01.respScheduleDate.contentEquals(testOrderService01.dbScheduleDate );
        assert testOrderService01.respType.substring(0,3).equalsIgnoreCase(testOrderService01.dbType.substring(0,3) );
        //assert testOrderService01.respStatus.substring(0,1).contentEquals(testOrderService01.dbStatus.substring(0,1) );
    }

    @Test(groups = { "OrderService" } )
    public void NC05Status() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService01.respOrderId.contentEquals(testOrderService01.dbOrderId );
        //assert testOrderService01.respAccountId.contentEquals(testOrderService01.dbAccountId );
        //assert testOrderService01.respScheduleDate.contentEquals(testOrderService01.dbScheduleDate );
        //assert testOrderService01.respType.substring(0,3).equalsIgnoreCase(testOrderService01.dbType.substring(0,3) );
        assert testOrderService01.respStatus.substring(0,1).contentEquals(testOrderService01.dbStatus.substring(0,1) );
    }

    @Test(groups = { "OrderService" } )
    public void TC06OrderID() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        thisOrder = getOpenWOs.getOpenTC();
        System.out.println("thisOrder: " + thisOrder);
        testOrderService02 = new testOrderService(thisOrder);

        assert testOrderService02.respOrderId.contentEquals(testOrderService02.dbOrderId );
        //assert testOrderService02.respAccountId.contentEquals(testOrderService02.dbAccountId );
        //assert testOrderService02.respScheduleDate.contentEquals(testOrderService02.dbScheduleDate );
        //assert testOrderService02.respType.substring(0,7).equalsIgnoreCase(testOrderService02.dbType.substring(0,7) );
        //assert testOrderService02.respStatus.substring(0,1).contentEquals(testOrderService02.dbStatus.substring(0,1) );
    }

    @Test(groups = { "OrderService" } )
    public void TC07AccountID() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService02.respOrderId.contentEquals(testOrderService02.dbOrderId );
        assert testOrderService02.respAccountId.contentEquals(testOrderService02.dbAccountId );
        //assert testOrderService02.respScheduleDate.contentEquals(testOrderService02.dbScheduleDate );
        //assert testOrderService02.respType.substring(0,7).equalsIgnoreCase(testOrderService02.dbType.substring(0,7) );
        //assert testOrderService02.respStatus.substring(0,1).contentEquals(testOrderService02.dbStatus.substring(0,1) );
    }

    @Test(groups = { "OrderService" } )
    public void TC08ScheduleDat() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService02.respOrderId.contentEquals(testOrderService02.dbOrderId );
        //assert testOrderService02.respAccountId.contentEquals(testOrderService02.dbAccountId );
        assert testOrderService02.respScheduleDate.contentEquals(testOrderService02.dbScheduleDate );
        //assert testOrderService02.respType.substring(0,7).equalsIgnoreCase(testOrderService02.dbType.substring(0,7) );
        //assert testOrderService02.respStatus.substring(0,1).contentEquals(testOrderService02.dbStatus.substring(0,1) );
    }

    @Test(groups = { "OrderService" } )
    public void TC09Type() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService02.respOrderId.contentEquals(testOrderService02.dbOrderId );
        //assert testOrderService02.respAccountId.contentEquals(testOrderService02.dbAccountId );
        //assert testOrderService02.respScheduleDate.contentEquals(testOrderService02.dbScheduleDate );
        assert testOrderService02.respType.substring(0,7).equalsIgnoreCase(testOrderService02.dbType.substring(0,7) );
        //assert testOrderService02.respStatus.substring(0,1).contentEquals(testOrderService02.dbStatus.substring(0,1) );
    }

    @Test(groups = { "OrderService" } )
    public void TC10Status() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService02.respOrderId.contentEquals(testOrderService02.dbOrderId );
        //assert testOrderService02.respAccountId.contentEquals(testOrderService02.dbAccountId );
        //assert testOrderService02.respScheduleDate.contentEquals(testOrderService02.dbScheduleDate );
        //assert testOrderService02.respType.substring(0,7).equalsIgnoreCase(testOrderService02.dbType.substring(0,7) );
        assert testOrderService02.respStatus.substring(0,1).contentEquals(testOrderService02.dbStatus.substring(0,1) );
    }

    @Test(groups = { "OrderService" } )
    public void SC11OrderID() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        thisOrder = getOpenWOs.getOpenSC();
        System.out.println("thisOrder: " + thisOrder);
        testOrderService03 = new testOrderService(thisOrder);

        assert testOrderService03.respOrderId.contentEquals(testOrderService03.dbOrderId);
        //assert testOrderService03.respAccountId.contentEquals(testOrderService03.dbAccountId);
        //assert testOrderService03.respScheduleDate.contentEquals(testOrderService03.dbScheduleDate);
        //assert testOrderService03.respType.substring(0, 6).equalsIgnoreCase(testOrderService03.dbType.substring(8, 14));
        //assert testOrderService03.respStatus.substring(0, 1).contentEquals(testOrderService03.dbStatus.substring(0, 1));
    }

    @Test(groups = { "OrderService" } )
    public void SC12AccountID() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService03.respOrderId.contentEquals(testOrderService03.dbOrderId);
        assert testOrderService03.respAccountId.contentEquals(testOrderService03.dbAccountId);
        //assert testOrderService03.respScheduleDate.contentEquals(testOrderService03.dbScheduleDate);
        //assert testOrderService03.respType.substring(0, 6).equalsIgnoreCase(testOrderService03.dbType.substring(8, 14));
        //assert testOrderService03.respStatus.substring(0, 1).contentEquals(testOrderService03.dbStatus.substring(0, 1));
    }

    @Test(groups = { "OrderService" } )
    public void SC13ScheduleDate() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService03.respOrderId.contentEquals(testOrderService03.dbOrderId);
        //assert testOrderService03.respAccountId.contentEquals(testOrderService03.dbAccountId);
        assert testOrderService03.respScheduleDate.contentEquals(testOrderService03.dbScheduleDate);
        //assert testOrderService03.respType.substring(0, 6).equalsIgnoreCase(testOrderService03.dbType.substring(8, 14));
        //assert testOrderService03.respStatus.substring(0, 1).contentEquals(testOrderService03.dbStatus.substring(0, 1));
    }

    @Test(groups = { "OrderService" } )
    public void SC14Type() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService03.respOrderId.contentEquals(testOrderService03.dbOrderId);
        //assert testOrderService03.respAccountId.contentEquals(testOrderService03.dbAccountId);
        //assert testOrderService03.respScheduleDate.contentEquals(testOrderService03.dbScheduleDate);
        assert testOrderService03.respType.substring(0, 6).equalsIgnoreCase(testOrderService03.dbType.substring(8, 14));
        //assert testOrderService03.respStatus.substring(0, 1).contentEquals(testOrderService03.dbStatus.substring(0, 1));
    }

    @Test(groups = { "OrderService" } )
    public void SC15Status() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        //assert testOrderService03.respOrderId.contentEquals(testOrderService03.dbOrderId);
        //assert testOrderService03.respAccountId.contentEquals(testOrderService03.dbAccountId);
        //assert testOrderService03.respScheduleDate.contentEquals(testOrderService03.dbScheduleDate);
        //assert testOrderService03.respType.substring(0, 6).equalsIgnoreCase(testOrderService03.dbType.substring(8, 14));
        assert testOrderService03.respStatus.substring(0, 1).contentEquals(testOrderService03.dbStatus.substring(0, 1));
    }

    @AfterClass
    public void totalTime()
    {
        // code that will be invoked when this test is instantiated
        System.out.println("\ntotal time: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
    }

} // class