/***
 * Developer:   carlos.loya
 * Date:        3/03/2016
 * Purpose:     Tests the Beacon Tech REST service adapters from TestNG
 * AMDG
 ***/

package x64;
import org.testng.annotations.*;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class apiTestNG
{
    long startTime;
    String pad = "%-64s %s\n";

    @BeforeClass
    public void setUp()
    {
        // code that will be invoked when this test is instantiated
        startTime = System.currentTimeMillis();
    }

    @Test(groups = { "serviceAdapters" } )
    public void testAccountAttributes() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, "Service Adapter", "Status Code");
        System.out.format(pad, testAccountAttributes.showVersion(), testAccountAttributes.getStatusCode());
        assert (testAccountAttributes.getStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void testAccountService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, testAccountService.showVersion(), testAccountService.getStatusCode());
        assert (testAccountService.getStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void testAgentService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, testAgentService.showVersion(), testAgentService.getStatusCode());
        assert (testAgentService.getStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void testAgreementService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, testAgreementService.showVersion(), testAgreementService.getStatusCode());
        assert (testAgreementService.getStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void testOrderService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, testOrderService.showVersion(), testOrderService.getStatusCode());
        assert (testOrderService.getStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void testPaymentService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, testPaymentService.showVersion(), testPaymentService.getStatusCode());
        assert (testPaymentService.getStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void testPricerService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, testPricerService.showVersion(), testPricerService.getStatusCode());
        assert (testPricerService.getStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void testStoreEquipmentOrderService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, testStoreEquipmentOrderService.showVersion(), testStoreEquipmentOrderService.getStatusCode());
        assert (testStoreEquipmentOrderService.getStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void testGemfireAPI() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, "service adapter: GemfireAPI", testGemfireAPI.getStatusCode());
        assert (testGemfireAPI.getStatusCode() + "" ).contains("200") : "Expected";
    }

    @AfterClass
    public void totalTime()
    {
        // code that will be invoked when this test is instantiated
        System.out.println("\ntotal time: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
    }
}
