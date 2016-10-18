/***
 * Developer:       Carlos A Loya
 * Date created:    3/24/2016
 * Date modified:   3/28/2016
 * Purpose:         Tests the Beacon Tech REST service adapter Pricer Service using TestNG
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

public class testNGPricerService
{
    long startTime;

    @BeforeClass
    public void setUp()
    {
        // code that will be invoked when this test is instantiated
        startTime = System.currentTimeMillis();
    }

    @Test(groups = { "PricerService" } )
    public void test01add1Product() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        testPricerService testPricerService01 = new testPricerService(
                                        "{\n" +
                                        "    \"accountAttributes\": {\n" +
                                        "        \"attributes\": {\n" +
                                        "            \"MANAGEMENT_GROUP\": \"600\"\n" +
                                        "        }\n" +
                                        "    },\n" +
                                        "    \"accountId\": \"8046909627635064\",\n" +
                                        "    \"billingType\": \"1\",\n" +
                                        "    \"cycleCode\": 0,\n" +
                                        "    \"offeringsToAdd\": [\n" +
                                        "      {\"offeringId\": \"175741011\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                                        "      ],\n" +
                                        "    \"offeringsToRemove\": [],\n" +
                                        "    \"currentOfferings\": [],\n" +
                                        "    \"connectDate\": \"\",\n" +
                                        "    \"currentBalance\": 0,\n" +
                                        "    \"pendingPayment\": 0,\n" +
                                        "    \"geoCode\": \"330472010\",\n" +
                                        "    \"billingFromDate\": \"\",\n" +
                                        "    \"billingToDate\": \"\"\n" +
                                        "  }");
        assert testPricerService01.respTotal.contentEquals("32.65");
    }

    @Test(groups = { "PricerService" } )
    public void test02add2Products() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        testPricerService testPricerService02 = new testPricerService(
                                        "{\n" +
                                        "    \"accountAttributes\": {\n" +
                                        "        \"attributes\": {\n" +
                                        "            \"MANAGEMENT_GROUP\": \"600\"\n" +
                                        "        }\n" +
                                        "    },\n" +
                                        "    \"accountId\": \"8046909627635064\",\n" +
                                        "    \"billingType\": \"1\",\n" +
                                        "    \"cycleCode\": 0,\n" +
                                        "    \"offeringsToAdd\": [\n" +
                                        "      {\"offeringId\": \"175741011\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                                        "     ,{\"offeringId\": \"175742361\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                                        "    ],\n" +
                                        "    \"offeringsToRemove\": [],\n" +
                                        "    \"currentOfferings\": [],\n" +
                                        "    \"connectDate\": \"\",\n" +
                                        "    \"currentBalance\": 0,\n" +
                                        "    \"pendingPayment\": 0,\n" +
                                        "    \"geoCode\": \"330472010\",\n" +
                                        "    \"billingFromDate\": \"\",\n" +
                                        "    \"billingToDate\": \"\"\n" +
                                        "  }");
        assert testPricerService02.respTotal.contentEquals("250.39");
    }

    @Test(groups = { "PricerService" } )
    public void test03add3Products() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        testPricerService testPricerService03 = new testPricerService(
                                "{\n" +
                                "    \"accountAttributes\": {\n" +
                                "        \"attributes\": {\n" +
                                "            \"MANAGEMENT_GROUP\": \"600\"\n" +
                                "        }\n" +
                                "    },\n" +
                                "    \"accountId\": \"8046909627635064\",\n" +
                                "    \"billingType\": \"1\",\n" +
                                "    \"cycleCode\": 0,\n" +
                                "    \"offeringsToAdd\": [\n" +
                                "      {\"offeringId\": \"175741011\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                                "     ,{\"offeringId\": \"175742361\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                                "     ,{\"offeringId\": \"175741981\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                                "    ],\n" +
                                "    \"offeringsToRemove\": [],\n" +
                                "    \"currentOfferings\": [],\n" +
                                "    \"connectDate\": \"\",\n" +
                                "    \"currentBalance\": 0,\n" +
                                "    \"pendingPayment\": 0,\n" +
                                "    \"geoCode\": \"330472010\",\n" +
                                "    \"billingFromDate\": \"\",\n" +
                                "    \"billingToDate\": \"\"\n" +
                                "  }");
        assert testPricerService03.respTotal.contentEquals("315.71");
    }

    @Test(groups = { "PricerService" } )
    public void test04add4Products() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        testPricerService testPricerService04 = new testPricerService(
                        "{\n" +
                        "    \"accountAttributes\": {\n" +
                        "        \"attributes\": {\n" +
                        "            \"MANAGEMENT_GROUP\": \"003\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"accountId\": \"8046909627635064\",\n" +
                        "    \"billingType\": \"1\",\n" +
                        "    \"cycleCode\": 0,\n" +
                        "    \"offeringsToAdd\": [\n" +
                        "      {\"offeringId\": \"175741011\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175742361\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175742101\"},\n" +
                        "      {\"offeringId\": \"175741981\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175740881\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                        "    ],\n" +
                        "    \"offeringsToRemove\": [],\n" +
                        "    \"currentOfferings\": [],\n" +
                        "    \"connectDate\": \"\",\n" +
                        "    \"currentBalance\": 0,\n" +
                        "    \"pendingPayment\": 0,\n" +
                        "    \"geoCode\": \"330472010\",\n" +
                        "    \"billingFromDate\": \"\",\n" +
                        "    \"billingToDate\": \"\"\n" +
                        "  }");
        assert testPricerService04.respTotal.contentEquals("348.36");
    }

    @Test(groups = { "PricerService" } )
    public void test05orderProductWithInstall() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        testPricerService testPricerService05 = new testPricerService(
                        "{\n" +
                        "    \"accountAttributes\": {\n" +
                        "        \"attributes\": {\n" +
                        "            \"MANAGEMENT_GROUP\": \"003\"\n" +
                        "        }\n" +
                        "    }," +
                        "   \"accountId\": \"8046909627635064\",\n" +
                        "   \"billingType\": \"1\",\n" +
                        "   \"cycleCode\": 1,\n" +
                        "   \"offeringsToAdd\": [\n" +
                        "      {\"offeringId\": \"175741121\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                        "     ,{\"offeringId\": \"175744961\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                        "     ,{\"offeringId\": \"175741411\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                        "     ,{\"offeringId\": \"175741861\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                        "      ],\n" +
                        "   \"offeringsToRemove\": [],\n" +
                        "   \"currentOfferings\": [\n" +
                        "      {\"connectDate\": \"2015-09-30T12:12:12\",\"quantity\": 1,\"offeringId\": \"46438422\"}\n" +
                        "      ],\n" +
                        "   \"connectDate\": \"2015-09-30T12:12:12\",\n" +
                        "   \"currentBalance\": 0,\n" +
                        "   \"pendingPayment\": 0,\n" +
                        "   \"geoCode\": \"330472010\",\n" +
                        "   \"billingFromDate\": \"2015-10-27\",\n" +
                        "   \"billingToDate\": \"2015-11-26\"\n" +
                        "}");
        assert testPricerService05.respTotal.contentEquals("304.83");
    }

    @Test(groups = { "PricerService" } )
    public void test06pendingPymt() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        testPricerService testPricerService06 = new testPricerService(
                        "{\n" +
                        "    \"accountAttributes\": {\n" +
                        "        \"attributes\": {\n" +
                        "            \"MANAGEMENT_GROUP\": \"003\"\n" +
                        "        }\n" +
                        "    }," +
                        "   \"accountId\": \"8046909627635064\",\n" +
                        "   \"billingType\": \"1\",\n" +
                        "   \"cycleCode\": 1,\n" +
                        "   \"offeringsToAdd\": [\n" +
                        "      {\"offeringId\": \"175741011\",\"quantity\": 1,\"connectDate\": \"\"}\n" +
                        "      ],\n" +
                        "   \"offeringsToRemove\": [\n" +
                        "      ],\n" +
                        "   \"currentOfferings\": [\n" +
                        "      {\"connectDate\": \"2015-09-30T12:12:12\",\"quantity\": 1,\"offeringId\": \"46438422\"}\n" +
                        "      ],\n" +
                        "   \"connectDate\": \"2015-09-30T12:12:12\",\n" +
                        "   \"currentBalance\": 0,\n" +
                        "   \"pendingPayment\": 200.00,\n" +
                        "   \"geoCode\": \"330472010\",\n" +
                        "   \"managementGroup\": \"003\",\n" +
                        "   \"billingFromDate\": \"2015-10-27\",\n" +
                        "   \"billingToDate\": \"2015-11-26\"\n" +
                        "}");
        assert testPricerService06.respTotal.contentEquals("32.65");
    }

    @Test(groups = { "PricerService" } )
    public void test07currentBalance() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        testPricerService testPricerService07 = new testPricerService(
                        "{\n" +
                        "    \"accountAttributes\": {\n" +
                        "        \"attributes\": {\n" +
                        "            \"MANAGEMENT_GROUP\": \"003\"\n" +
                        "        }\n" +
                        "    }," +
                        "   \"accountId\": \"8046909627635064\",\n" +
                        "   \"billingType\": \"1\",\n" +
                        "   \"cycleCode\": 1,\n" +
                        "   \"offeringsToAdd\": [\n" +
                        "      {\"offeringId\": \"175742411\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"}\n" +
                        "      ],\n" +
                        "   \"offeringsToRemove\": [\n" +
                        "      ],\n" +
                        "   \"currentOfferings\": [\n" +
                        "      {\"connectDate\": \"2015-09-30T12:12:12\",\"quantity\": 1,\"offeringId\": \"46438422\"}\n" +
                        "      ],\n" +
                        "   \"connectDate\": \"2015-09-30T12:12:12\",\n" +
                        "   \"currentBalance\": 210.00,\n" +
                        "   \"pendingPayment\": 0,\n" +
                        "   \"geoCode\": \"330472010\",\n" +
                        "   \"billingFromDate\": \"2015-10-27\",\n" +
                        "   \"billingToDate\": \"2015-11-26\"\n" +
                        "}");
        assert testPricerService07.respTotal.contentEquals("326.61");
    }

    @Test(groups = { "PricerService" } )
    public void test08quantityMoreThan1() throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,AssertionError
    {
        testPricerService testPricerService08 = new testPricerService(
                        "{\n" +
                        "    \"accountAttributes\": {\n" +
                        "        \"attributes\": {\n" +
                        "            \"MANAGEMENT_GROUP\": \"003\"\n" +
                        "        }\n" +
                        "    }," +
                        "   \"accountId\": \"8046909627635064\",\n" +
                        "   \"billingType\": \"1\",\n" +
                        "   \"cycleCode\": 1,\n" +
                        "   \"offeringsToAdd\": [\n" +
                        "      {\"offeringId\": \"175742271\",\"quantity\": 4,\"connectDate\": \"\"}\n" +
                        "     ,{\"offeringId\": \"175740991\",\"quantity\": 5,\"connectDate\": \"\"}\n" +
                        "     ,{\"offeringId\": \"175742191\",\"quantity\": 6,\"connectDate\": \"\"}\n" +
                        "      ],\n" +
                        "   \"offeringsToRemove\": [\n" +
                        "      ],\n" +
                        "   \"currentOfferings\": [\n" +
                        "      {\"connectDate\": \"2015-09-30T12:12:12\",\"quantity\": 1,\"offeringId\": \"46438422\"}\n" +
                        "      ],\n" +
                        "   \"connectDate\": \"2015-09-30T12:12:12\",\n" +
                        "   \"currentBalance\": 0,\n" +
                        "   \"pendingPayment\": 0,\n" +
                        "   \"geoCode\": \"330472010\",\n" +
                        "   \"managementGroup\": \"003\",\n" +
                        "   \"billingFromDate\": \"2015-10-27\",\n" +
                        "   \"billingToDate\": \"2015-11-26\"\n" +
                        "}");
        assert testPricerService08.respTotal.contentEquals("300.32");
    }

    @AfterClass
    public void totalTime()
    {
        // code that will be invoked when this test is instantiated
        System.out.println("\ntotal time: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
    }

} // class