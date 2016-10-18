/***
 * Developer:       carlos.loya
 * Create date:     2/19/2016
 * Modified date:   3/28/2016
 * Purpose:         Tests the Beacon Tech REST service adapter
 * ad majorem Dei gloriam
 ***/

package x64;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class testServiceAdapter
{
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        long startTime = System.currentTimeMillis();
        showStates();
        //showVersions();
        //loadTest();
        System.out.println("time elapsed: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
        System.out.println();

    } // main

    public static void showStates() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.println("Account Attributes:"
                + "\t\t\t\t" + testAccountAttributes.showVersion()
                + "\t\t\t" + testAccountAttributes.getStatusCode() );
        System.out.println("Account Service:"
                + "\t\t\t\t" + testAccountService.showVersion()
                + "\t\t\t\t" + testAccountService.getStatusCode() );
        System.out.println("Agent Service:"
                + "\t\t\t\t\t" + testAgentService.showVersion()
                + "\t\t\t\t\t" + testAgentService.getStatusCode() );
        System.out.println("Agreement Service:"
                + "\t\t\t\t" + testAgreementService.showVersion()
                + "\t\t\t" + testAgreementService.getStatusCode() );
        System.out.println("Order Service:"
                + "\t\t\t\t\t" + testOrderService.showVersion()
                + "\t\t\t\t\t" + testOrderService.getStatusCode() );
        System.out.println("Payment Service:"
                + "\t\t\t\t" + testPaymentService.showVersion()
                + "\t\t\t\t" + testPaymentService.getStatusCode() );
        System.out.println("Pricer Service:"
                + "\t\t\t\t\t" + testPricerService.showVersion()
                + "\t\t\t\t" + testPricerService.getStatusCode() );
        System.out.println("Store Equipment Order Service:"
                + "\t" + testStoreEquipmentOrderService.showVersion()
                + "\t" + testStoreEquipmentOrderService.getStatusCode() );
        String padded = String.format("%-68s", "");
        System.out.println("Gemfire API:" + padded + testGemfireAPI.getStatusCode() );
        System.out.println();
    }

    public static void showVersions() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.println(testAccountAttributes.showVersion() );
        System.out.println(testAccountService.showVersion() );
        System.out.println(testAgentService.showVersion() );
        System.out.println(testAgreementService.showVersion() );
        System.out.println(testOrderService.showVersion() );
        System.out.println(testPaymentService.showVersion() );
        System.out.println(testPricerService.showVersion() );
        System.out.println(testStoreEquipmentOrderService.showVersion() );
        System.out.println();
    }

    public static void loadTest() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        for (int a=1; a < 25; a++)
        {
            showVersions();
            showStates();
        }
    }
}