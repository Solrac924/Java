/***
 * Developer:   carlos.loya
 * Date:        4/19/2016
 * Purpose:     Tests the Beacon Tech REST service adapter
 * Note:        this class overrides Self-Signed Certificate
 * ad majorem Dei gloriam
 ***/

package x64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class IntStoreEquipmentOrderService
{
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // override Self-Signed Certificate
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy()
        {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        // set up endpoint
        HttpPost post = new HttpPost("https://store-equipment-order-service.integration.apps.int.dev.mer.cfdish.io:443/store-equipment-order?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=test.test");

        // set up JSON request
        StringEntity request = new StringEntity(
                "{\n" +
                        "    \"originalTruckRollOrderId\": \"1000005044423017\",\n" +
                        "    \"newOrderId\": \"1000005044423014\",\n" +
                        "    \"newOrderCreateDate\": \"2015-12-07\",\n" +
                        "    \"offeringList\": [\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744331\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Network\",\n" +
                        "        \"totalOfferingAmount\": 149.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744411\",\n" +
                        "        \"offeringName\": \"WiFi Premium Router (Accessory)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741801\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Network (Labor)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 3,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741781\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Networkr\",\n" +
                        "        \"totalOfferingAmount\": 199.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744441\",\n" +
                        "        \"offeringName\": \"Install Prem Router DNS (credit)\",\n" +
                        "        \"totalOfferingAmount\": -50,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"AdjustmentProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742431\",\n" +
                        "        \"offeringName\": \"Sound Bar with Subwoofer\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"MultiComponentOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742421\",\n" +
                        "        \"offeringName\": \"Install Sound Bar w/ Sub (Labor)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 3,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175742431\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742411\",\n" +
                        "        \"offeringName\": \"Install Sound Bar w/ Sub\",\n" +
                        "        \"totalOfferingAmount\": 299.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175742431\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741381\",\n" +
                        "        \"offeringName\": \"Install TV On Stand\",\n" +
                        "        \"totalOfferingAmount\": 99.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175741391\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741391\",\n" +
                        "        \"offeringName\": \"Install TV On Stand\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"MultiComponentOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 6,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175740571\",\n" +
                        "        \"offeringName\": \"Rmt Cntl 21.1 (622/722)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        // show response
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null)
        {
            System.out.println(line);
        }

        // show status code
        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );

        // show the service adapter version
        System.out.println("version: " + showVersion() );
    } // end main

    static void showStatusCode() throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // override Self-Signed Certificate
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy()
        {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        // set up endpoint
        HttpPost post = new HttpPost("https://store-equipment-order-service.integration.apps.int.dev.mer.cfdish.io:443/store-equipment-order?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=test.test");

        // set up request
        StringEntity request = new StringEntity(
                "{\n" +
                        "    \"originalTruckRollOrderId\": \"1000005044423017\",\n" +
                        "    \"newOrderId\": \"1000005044423014\",\n" +
                        "    \"newOrderCreateDate\": \"2015-12-07\",\n" +
                        "    \"offeringList\": [\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744331\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Network\",\n" +
                        "        \"totalOfferingAmount\": 149.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744411\",\n" +
                        "        \"offeringName\": \"WiFi Premium Router (Accessory)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741801\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Network (Labor)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 3,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741781\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Networkr\",\n" +
                        "        \"totalOfferingAmount\": 199.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744441\",\n" +
                        "        \"offeringName\": \"Install Prem Router DNS (credit)\",\n" +
                        "        \"totalOfferingAmount\": -50,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"AdjustmentProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742431\",\n" +
                        "        \"offeringName\": \"Sound Bar with Subwoofer\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"MultiComponentOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742421\",\n" +
                        "        \"offeringName\": \"Install Sound Bar w/ Sub (Labor)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 3,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175742431\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742411\",\n" +
                        "        \"offeringName\": \"Install Sound Bar w/ Sub\",\n" +
                        "        \"totalOfferingAmount\": 299.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175742431\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741381\",\n" +
                        "        \"offeringName\": \"Install TV On Stand\",\n" +
                        "        \"totalOfferingAmount\": 99.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175741391\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741391\",\n" +
                        "        \"offeringName\": \"Install TV On Stand\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"MultiComponentOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 6,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175740571\",\n" +
                        "        \"offeringName\": \"Rmt Cntl 21.1 (622/722)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }");
        request.setContentType("application/json");
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );
    }

    static int getStatusCode() throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // override Self-Signed Certificate
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy()
        {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();


        // setup endpoint
        HttpPost post = new HttpPost("https://store-equipment-order-service.integration.apps.int.dev.mer.cfdish.io:443/store-equipment-order?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=test.test");

        // send request
        StringEntity request = new StringEntity(
                "{\n" +
                        "    \"originalTruckRollOrderId\": \"1000005044423017\",\n" +
                        "    \"newOrderId\": \"1000005044423014\",\n" +
                        "    \"newOrderCreateDate\": \"2015-12-07\",\n" +
                        "    \"offeringList\": [\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744331\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Network\",\n" +
                        "        \"totalOfferingAmount\": 149.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744411\",\n" +
                        "        \"offeringName\": \"WiFi Premium Router (Accessory)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741801\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Network (Labor)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 3,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741781\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Networkr\",\n" +
                        "        \"totalOfferingAmount\": 199.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744441\",\n" +
                        "        \"offeringName\": \"Install Prem Router DNS (credit)\",\n" +
                        "        \"totalOfferingAmount\": -50,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"AdjustmentProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742431\",\n" +
                        "        \"offeringName\": \"Sound Bar with Subwoofer\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"MultiComponentOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742421\",\n" +
                        "        \"offeringName\": \"Install Sound Bar w/ Sub (Labor)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 3,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175742431\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742411\",\n" +
                        "        \"offeringName\": \"Install Sound Bar w/ Sub\",\n" +
                        "        \"totalOfferingAmount\": 299.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175742431\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741381\",\n" +
                        "        \"offeringName\": \"Install TV On Stand\",\n" +
                        "        \"totalOfferingAmount\": 99.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175741391\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741391\",\n" +
                        "        \"offeringName\": \"Install TV On Stand\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"MultiComponentOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 6,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175740571\",\n" +
                        "        \"offeringName\": \"Rmt Cntl 21.1 (622/722)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        return response.getStatusLine().getStatusCode();
    }

    static String showVersion() throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // override Self-Signed Certificate
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy()
        {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        // set up endpoint
        HttpPost post = new HttpPost("https://store-equipment-order-service.integration.apps.int.dev.mer.cfdish.io:443/store-equipment-order?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=test.test");

        // set up JSON request
        StringEntity request = new StringEntity(
                "{\n" +
                        "    \"originalTruckRollOrderId\": \"1000005044423017\",\n" +
                        "    \"newOrderId\": \"1000005044423014\",\n" +
                        "    \"newOrderCreateDate\": \"2015-12-07\",\n" +
                        "    \"offeringList\": [\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744331\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Network\",\n" +
                        "        \"totalOfferingAmount\": 149.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744411\",\n" +
                        "        \"offeringName\": \"WiFi Premium Router (Accessory)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741801\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Network (Labor)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 3,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741781\",\n" +
                        "        \"offeringName\": \"Set Up Premium Wireless Networkr\",\n" +
                        "        \"totalOfferingAmount\": 199.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175744441\",\n" +
                        "        \"offeringName\": \"Install Prem Router DNS (credit)\",\n" +
                        "        \"totalOfferingAmount\": -50,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"AdjustmentProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175744331\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742431\",\n" +
                        "        \"offeringName\": \"Sound Bar with Subwoofer\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"MultiComponentOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742421\",\n" +
                        "        \"offeringName\": \"Install Sound Bar w/ Sub (Labor)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 3,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175742431\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175742411\",\n" +
                        "        \"offeringName\": \"Install Sound Bar w/ Sub\",\n" +
                        "        \"totalOfferingAmount\": 299.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175742431\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741381\",\n" +
                        "        \"offeringName\": \"Install TV On Stand\",\n" +
                        "        \"totalOfferingAmount\": 99.99,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"GenericProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"175741391\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175741391\",\n" +
                        "        \"offeringName\": \"Install TV On Stand\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"MultiComponentOffering\",\n" +
                        "        \"productTypeQualifier\": \"LaborProduct\",\n" +
                        "        \"installationPoints\": 6,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"offeringId\": \"175740571\",\n" +
                        "        \"offeringName\": \"Rmt Cntl 21.1 (622/722)\",\n" +
                        "        \"totalOfferingAmount\": 0,\n" +
                        "        \"offeringTypeQualifier\": \"OFMProductOffering\",\n" +
                        "        \"productTypeQualifier\": \"HardwareProduct\",\n" +
                        "        \"installationPoints\": 0,\n" +
                        "        \"quantity\": 1,\n" +
                        "        \"parentOfferingId\": \"\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        String version = "";
        org.apache.http.Header[] headers = response.getAllHeaders();
        for (org.apache.http.Header header : headers)
        {
            if (header.getName().contains("X-Application-Context") )
            {
                version = header.getValue();
            }
        }
        return version;
    } // showVersion
} // end class