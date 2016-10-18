/***
 * Developer:       carlos.loya
 * Date created:    2/19/2016
 * Date modified:   3/28/2016
 * Purpose:         Tests the Pricer REST service adapter
 * Note:            this class overrides Self-Signed Certificate
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
import java.util.StringTokenizer;

public class IntPricerService
{
    String respTotal = "";
    String respAmtDueNow = "";
    String respName = "";
    String respAmount = "";

    public IntPricerService(String inRequest) throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
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

        String endPoint = "https://pricer-service.integration.apps.int.dev.mer.cfdish.io:443/price-quote?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(inRequest);
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        // show response
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        String fullResponse = "";
        while ((line = rd.readLine()) != null)
        {
            fullResponse += line;
        }

        StringTokenizer st = new StringTokenizer(fullResponse, "\"");
        String currToken = "";
        String scrap = "";

        while (st.hasMoreTokens() )
        {
            currToken = st.nextToken();

            if (currToken.contains("name") )
            {
                scrap = st.nextToken();
                respName = st.nextToken();
                System.out.println("respName: " + respName);
            }
            if (currToken.contentEquals("amount") )
            {
                respAmount = st.nextToken();
                int begin = respAmount.indexOf(':') + 2;
                int end = respAmount.indexOf(',');
                respAmount = respAmount.substring(begin,end);
                System.out.println("respAmount: " + respAmount);
            }
            if (currToken.contains("total") )
            {
                respTotal = st.nextToken();
                System.out.println("respTotal before: '" + respTotal + "'");
                int begin = respTotal.indexOf(':') + 1;
                int end = respTotal.indexOf(',');
                respTotal = respTotal.substring(begin,end);
                System.out.println("respTotal after: '" + respTotal + "'");
            }
            if (currToken.contains("amountDueNow") )
            {
                respAmtDueNow = st.nextToken();
                int begin = respAmtDueNow.indexOf(':') + 2;
                int end = respAmtDueNow.indexOf(',');
                respAmtDueNow = respAmtDueNow.substring(begin,end);
            }
        } // while
        //System.out.println("respName: " + respName);
        System.out.println("respTotal: " + respTotal);
        System.out.println("respAmtDueNow: " + respAmtDueNow );
        System.out.println("--------------------------------------");
    }

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
        HttpPost post = new HttpPost("https://pricer-service.integration.apps.int.dev.mer.cfdish.io:443/price-quote?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

        // set up JSON request
        StringEntity request = new StringEntity(
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
                        "      {\"offeringId\": \"175742101\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175742091\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175742101\"},\n" +
                        "      {\"offeringId\": \"175740851\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175740841\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175740851\"},\n" +
                        "      {\"offeringId\": \"175744331\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175744411\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175741801\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175741781\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175744441\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"}\n" +
                        "    ],\n" +
                        "    \"offeringsToRemove\": [],\n" +
                        "    \"currentOfferings\": [],\n" +
                        "    \"connectDate\": \"\",\n" +
                        "    \"currentBalance\": 0,\n" +
                        "    \"pendingPayment\": 0,\n" +
                        "    \"geoCode\": \"330472010\",\n" +
                        "    \"billingFromDate\": \"\",\n" +
                        "    \"billingToDate\": \"\"\n" +
                        "}");
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


        HttpPost post = new HttpPost("https://pricer-service.integration.apps.int.dev.mer.cfdish.io:443/price-quote?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");
        StringEntity request = new StringEntity(
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
                        "      {\"offeringId\": \"175742101\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175742091\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175742101\"},\n" +
                        "      {\"offeringId\": \"175740851\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175740841\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175740851\"},\n" +
                        "      {\"offeringId\": \"175744331\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175744411\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175741801\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175741781\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175744441\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"}\n" +
                        "    ],\n" +
                        "    \"offeringsToRemove\": [],\n" +
                        "    \"currentOfferings\": [],\n" +
                        "    \"connectDate\": \"\",\n" +
                        "    \"currentBalance\": 0,\n" +
                        "    \"pendingPayment\": 0,\n" +
                        "    \"geoCode\": \"330472010\",\n" +
                        "    \"billingFromDate\": \"\",\n" +
                        "    \"billingToDate\": \"\"\n" +
                        "}");
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
        HttpPost post = new HttpPost("https://pricer-service.integration.apps.int.dev.mer.cfdish.io:443/price-quote?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

        // send request
        StringEntity request = new StringEntity(
                        "{\n" +
                        "    \"accountAttributes\": {\n" +
                        "        \"attributes\": {\n" +
                        "            \"MANAGEMENT_GROUP\": \"600\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"accountId\": \"8046909808169289\",\n" +
                        "    \"billingType\": \"1\",\n" +
                        "    \"cycleCode\": 0,\n" +
                        "    \"offeringsToAdd\": [\n" +
                        "      {\"offeringId\": \"175742101\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175742091\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175742101\"},\n" +
                        "      {\"offeringId\": \"175740851\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175740841\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175740851\"},\n" +
                        "      {\"offeringId\": \"175744331\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175744411\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175741801\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175741781\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175744441\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"}\n" +
                        "    ],\n" +
                        "    \"offeringsToRemove\": [],\n" +
                        "    \"currentOfferings\": [],\n" +
                        "    \"connectDate\": \"\",\n" +
                        "    \"currentBalance\": 0,\n" +
                        "    \"pendingPayment\": 0,\n" +
                        "    \"geoCode\": \"330472010\",\n" +
                        "    \"billingFromDate\": \"\",\n" +
                        "    \"billingToDate\": \"\"\n" +
                        "}");
        request.setContentType("application/json");
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
        HttpPost post = new HttpPost("https://pricer-service.integration.apps.int.dev.mer.cfdish.io:443/price-quote?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

        // set up JSON request
        StringEntity request = new StringEntity(
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
                        "      {\"offeringId\": \"175742101\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175742091\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175742101\"},\n" +
                        "      {\"offeringId\": \"175740851\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175740841\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175740851\"},\n" +
                        "      {\"offeringId\": \"175744331\",\"quantity\": 1,\"autoAdd\": false,\"parentOfferingId\": \"\"},\n" +
                        "      {\"offeringId\": \"175744411\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175741801\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175741781\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"},\n" +
                        "      {\"offeringId\": \"175744441\",\"quantity\": 1,\"autoAdd\": true,\"parentOfferingId\": \"175744331\"}\n" +
                        "    ],\n" +
                        "    \"offeringsToRemove\": [],\n" +
                        "    \"currentOfferings\": [],\n" +
                        "    \"connectDate\": \"\",\n" +
                        "    \"currentBalance\": 0,\n" +
                        "    \"pendingPayment\": 0,\n" +
                        "    \"geoCode\": \"330472010\",\n" +
                        "    \"billingFromDate\": \"\",\n" +
                        "    \"billingToDate\": \"\"\n" +
                        "}");
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