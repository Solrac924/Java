/***
 * Developer:       Carlos A Loya
 * Modified Date:   4/19/2016
 * Purpose:         Tests the Beacon Tech REST service adapters from TestNG
 * ad majorem Dei gloriam
 ***/

package x64;
import org.testng.annotations.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class apiIntNG
{
    long startTime;
    String pad = "%-50s %s\n";
    String response = "";

    @BeforeClass
    public void setUp()
    {
        // code that will be invoked when this test is instantiated
        startTime = System.currentTimeMillis();
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntAccountAttributes() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, "Service Adapter", "Status Code");
        System.out.format(pad, IntAccountAttributes.showVersion(), IntAccountAttributes.getStatusCode());
        assert (IntAccountAttributes.getStatusCode() +"" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntAccountService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, AssertionError
    {
        System.out.format(pad, IntAccountService.showVersion(), IntAccountService.getStatusCode());
        assert (IntAccountService.getStatusCode() +"" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntAgentService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, IntAgentService.showVersion(), IntAgentService.getStatusCode());
        assert (IntAgentService.getStatusCode() +"" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntAgreementService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, IntAgreementService.showVersion(), IntAgreementService.getStatusCode());
        assert (IntAgreementService.getStatusCode() +"" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntOrderService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, IntOrderService.showVersion(), IntOrderService.getStatusCode());
        assert (IntOrderService.getStatusCode() +"" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntPaymentService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, IntPaymentService.showVersion(), IntPaymentService.getStatusCode() );
        assert (IntPaymentService.getStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntPricerService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, IntPricerService.showVersion(), IntPricerService.getStatusCode());
        assert (IntPricerService.getStatusCode() +"" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntStoreEquipmentOrderService() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, IntStoreEquipmentOrderService.showVersion(), IntStoreEquipmentOrderService.getStatusCode());
        assert (IntStoreEquipmentOrderService.getStatusCode() +"" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntGemfireAPI() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        //System.out.println("service adapter: GemfireAPI                     " + testGemfireAPI.getStatusCode() );
        System.out.format(pad, "Gemfire Review API", testGemfireAPI.getStatusCode());
        assert (IntGemfireAPI.getStatusCode() +"" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntTranslatorAPI() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, IntTranslatorAPI.showVersion(), IntTranslatorAPI.getStatusCode() );
        assert (IntTranslatorAPI.getStatusCode() +"" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntEvaluatorAPI() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, "Evaluator API", IntEvaluatorAPI.getStatusCode() );
        //assert (IntEvaluatorAPI.getStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntEvalPaymentMethods() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, IntEvaluator.showPaymentsVersion(), IntEvaluator.getPaymentsStatusCode() );
        assert (IntEvaluator.getPaymentsStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntEvalSegments() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, IntEvaluator.showSegmentsVersion(), IntEvaluator.getSegmentsStatusCode() );
        assert (IntEvaluator.getSegmentsStatusCode() + "" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void EvalPayment01() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        response = IntEvaluator.EvalPayments("114.99");
        assert (response).contains("BANK_ACCOUNT") : "Expected";
        assert (response).contains("BILL_TO_ACCOUNT") : "Expected";
        assert (response).contains("CREDIT_DEBIT_CARD") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void EvalPayment02() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        response = IntEvaluator.EvalPayments("0.00");
        assert (response).contains("BANK_ACCOUNT") : "Expected EFT";
        assert (response).contains("BILL_TO_ACCOUNT") : "Expected Bill to account";
        assert (response).contains("CREDIT_DEBIT_CARD") : "Expected Card";
    }

    @Test(groups = { "serviceAdapters" } )
    public void EvalPayment03() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        assert (IntEvaluator.EvalPayments("0") ).contains("BANK_ACCOUNT") : "Expected";
        assert (IntEvaluator.EvalPayments("0") ).contains("BILL_TO_ACCOUNT") : "Expected";
        assert (IntEvaluator.EvalPayments("0") ).contains("CREDIT_DEBIT_CARD") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void EvalPayment04() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        assert (IntEvaluator.EvalPayments("115.00") ).contains("BANK_ACCOUNT") : "Expected";
        assert (IntEvaluator.EvalPayments("115.00") ).contains("CREDIT_DEBIT_CARD") : "Expected";
        assert !(IntEvaluator.EvalPayments("115.00") ).contains("BILL_TO_ACCOUNT") : "Negative";

    }

    @Test(groups = { "serviceAdapters" } )
    public void IntDES() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException
    {
        System.out.format(pad, IntDES.showVersion(), IntDES.getStatusCode() );
        assert (IntDES.getStatusCode() +"" ).contains("200") : "Expected";
    }

    @Test(groups = { "serviceAdapters" } )
    public void IntUI() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, Exception
    {
        IntUI.setUp();
        IntUI.orderItems();
        IntUI.tearDown();
    }

    @AfterClass
    public void totalTime()
    {
        // code that will be invoked when this test is instantiated
        System.out.println("\ntotal time: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");
    }
}