/***
 * Developer:       carlos.loya
 * Date:            4/22/2016
 * modified date:   4/25/2016
 * Purpose:         Tests the Beacon Tech REST service adapter
 * Note:            this class overrides Self-Signed Certificate
 * ad majorem Dei gloriam
 ***/

package x64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class IntEvaluator
{
    static String pad = "%-50s %s\n";

    public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // show the service adapter version & status code
        System.out.format(pad, showSegmentsVersion(), getSegmentsStatusCode() );
        System.out.format(pad, showPaymentsVersion(), getPaymentsStatusCode() );

    } // end main

    static int getSegmentsStatusCode() throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // get REST response
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

        // send REST request
        String endPoint = "https://evaluator-segment.integration.apps.int.dev.mer.cfdish.io/segments/evaluate";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                        "{\n" +
                        "   \"account\": {\n" +
                        "      \"attributes\": {\n" +
                        "         \"ACCOUNT_SUB_TYPE\": \"ATT REV SHARE\",\n" +
                        "         \"BILLING_CHANNEL\": \"DISH\",\n" +
                        "         \"PARTNER\": \"SBC\",\n" +
                        "         \"ZONE\" : null,\n" +
                        "         \"ACCOUNT_TYPE\" : \"RESIDENTIAL\",\n" +
                        "         \"CARD_PAYMENT_ALLOWED\": \"true\",\n" +
                        "         \"EFT_PAYMENT_ALLOWED\": \"true\",\n" +
                        "         \"MANAGEMENT_GROUP\" : \"600\"\n" +
                        "      }\n" +
                        "   }\n" +
                        "}");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        return response.getStatusLine().getStatusCode();
    }

    static int getPaymentsStatusCode() throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // get REST response
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

        // send REST request
        String endPoint = "https://evaluator-payment.integration.apps.int.dev.mer.cfdish.io:443/paymentMethods/evaluate";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                                        "{\n" +
                                        "  \"customerSegmentCategories\": [\n" +
                                        "    {\n" +
                                        "      \"guid\": \"1b79326c-6f13-4266-b0fc-8810e0aba595\",\n" +
                                        "      \"name\": \"Account Status (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"STATUS\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"11bf1e7a-0b8a-4842-bffb-2281fff5e057\",\n" +
                                        "      \"name\": \"Install Fulfillment Party (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"INSTALL_FULFILLMENT_PARTY\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"c7c21097-cb2a-41d3-a9e3-80c6e9d607b3\",\n" +
                                        "      \"name\": \"Account Past Due (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"ACCOUNT_PAST_DUE\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"921d0cd2-b1b6-40a7-b4f0-123c3aeeaae5\",\n" +
                                        "      \"name\": \"Business Confidence Level (CSC)\",\n" +
                                        "      \"evaluated\": true,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": []\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"d0506711-f8d7-40f6-8040-2f6caae5815f\",\n" +
                                        "      \"name\": \"Formers Status (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"FORMERS_STATUS\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"75f58418-3de5-477f-8d56-ff8a8611741e\",\n" +
                                        "      \"name\": \"EFT_Payment_Allowed (CSC)\",\n" +
                                        "      \"evaluated\": true,\n" +
                                        "      \"matchedSegments\": [\n" +
                                        "        \"06f5f3c1-7c4d-449f-b143-87d1c5cbfe95\"\n" +
                                        "      ],\n" +
                                        "      \"requiredCriteria\": []\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"c2d9ce42-08e1-4447-b522-efd83dbdced7\",\n" +
                                        "      \"name\": \"Credit Qualification (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"CREDIT_BUREAU_NAME\",\n" +
                                        "        \"CREDIT_SCORE_MIN\",\n" +
                                        "        \"CREDIT_SCORE_MAX\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"d81cfb24-5b1d-41b5-b992-3a251f08c55c\",\n" +
                                        "      \"name\": \"Line of Service Status (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"LINE_OF_SERVICE_STATUS\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"99499b22-88b0-4c2e-8898-08e4e0525594\",\n" +
                                        "      \"name\": \"Credit_Card_Allowed (CSC)\",\n" +
                                        "      \"evaluated\": true,\n" +
                                        "      \"matchedSegments\": [\n" +
                                        "        \"2145fbd3-8507-4d39-9777-e87c83d83cd8\"\n" +
                                        "      ],\n" +
                                        "      \"requiredCriteria\": []\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"adf6a01b-bec3-4d05-9901-26d0f21811e5\",\n" +
                                        "      \"name\": \"Account Type (CSC)\",\n" +
                                        "      \"evaluated\": true,\n" +
                                        "      \"matchedSegments\": [\n" +
                                        "        \"c507ce45-9542-4c17-b10b-75440627f953\"\n" +
                                        "      ],\n" +
                                        "      \"requiredCriteria\": []\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"c0e323e7-01d4-4d29-943b-4c275515d376\",\n" +
                                        "      \"name\": \"Certificate (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"CERTIFICATE\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"0d546663-7398-433d-88cd-4d12ede21740\",\n" +
                                        "      \"name\": \"Equipment Change Restricted (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"EQUIPMENT_CHANGE_RESTRICTED\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"0c9ba860-9ce5-42d3-ad70-21175bf8d361\",\n" +
                                        "      \"name\": \"Claim Code (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"CLAIM_CODE\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"8aba2379-64d2-4391-b9bc-d246bd821507\",\n" +
                                        "      \"name\": \"Order Reason (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"ORDER_REASON\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"796dbef7-ed1d-4cd2-9975-28a6a15fcf60\",\n" +
                                        "      \"name\": \"Payment Type (CSC)\",\n" +
                                        "      \"evaluated\": true,\n" +
                                        "      \"matchedSegments\": [\n" +
                                        "      ],\n" +
                                        "      \"requiredCriteria\": []\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"ebff706e-7a18-4e63-a0dc-1a21e431b2c8\",\n" +
                                        "      \"name\": \"Star Tier (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"STAR_TIER_VALUE\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"72e1854f-688a-40e8-ba12-8143f8c636b1\",\n" +
                                        "      \"name\": \"Line of Sight (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"LINE_OF_SIGHT\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"dcdeecf4-0a00-4f94-93b6-4aa33ba8ab0a\",\n" +
                                        "      \"name\": \"Migration Preference (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"OPT_IN\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"ec90bca8-1fab-4bdf-b01f-5cbe2d1b400a\",\n" +
                                        "      \"name\": \"Dish Pause (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"DISH_PAUSE\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"d594f604-29b9-4abc-8ed6-b23e6a072c04\",\n" +
                                        "      \"name\": \"MQ2 Module (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"MQ2_MODULE\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"bd0e18a8-0638-496a-ad16-0784aa5ad5b0\",\n" +
                                        "      \"name\": \"SDS Property Type (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"SDS_PROPERTY_TYPE\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"8eb548e8-4506-4714-b310-4800ac886d62\",\n" +
                                        "      \"name\": \"Geographical Location (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"ZIP_CODE\",\n" +
                                        "        \"STATE\",\n" +
                                        "        \"DMA\"\n" +
                                        "      ]\n" +
                                        "    },\n" +
                                        "    {\n" +
                                        "      \"guid\": \"975cd5a5-c15a-475a-a945-5d67ccdbbba0\",\n" +
                                        "      \"name\": \"Estimated Viewing Occupancy (CSC)\",\n" +
                                        "      \"evaluated\": false,\n" +
                                        "      \"matchedSegments\": [],\n" +
                                        "      \"requiredCriteria\": [\n" +
                                        "        \"EVO_TIER\"\n" +
                                        "      ]\n" +
                                        "    }\n" +
                                        "  ],\n" +
                                        "  \"cart\": {\n" +
                                        "    \"totalAmount\": 100.00\n" +
                                        "  }\n" +
                                        "}");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        return response.getStatusLine().getStatusCode();
    }

    static String showSegmentsVersion() throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // get REST response
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

        // send REST request
        String endPoint = "https://evaluator-segment.integration.apps.int.dev.mer.cfdish.io/segments/evaluate";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                                "{\n" +
                                "   \"account\": {\n" +
                                "      \"attributes\": {\n" +
                                "         \"ACCOUNT_SUB_TYPE\": \"ATT REV SHARE\",\n" +
                                "         \"BILLING_CHANNEL\": \"DISH\",\n" +
                                "         \"PARTNER\": \"SBC\",\n" +
                                "         \"ZONE\" : null,\n" +
                                "         \"ACCOUNT_TYPE\" : \"RESIDENTIAL\",\n" +
                                "         \"CARD_PAYMENT_ALLOWED\": \"true\",\n" +
                                "         \"EFT_PAYMENT_ALLOWED\": \"true\",\n" +
                                "         \"MANAGEMENT_GROUP\" : \"600\"\n" +
                                "      }\n" +
                                "   }\n" +
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
    } // showSegmentsVersion

    static String showPaymentsVersion() throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // get REST response
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

        // send REST request
        String endPoint = "https://evaluator-payment.integration.apps.int.dev.mer.cfdish.io:443/paymentMethods/evaluate";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                                "{\n" +
                                "  \"customerSegmentCategories\": [\n" +
                                "    {\n" +
                                "      \"guid\": \"1b79326c-6f13-4266-b0fc-8810e0aba595\",\n" +
                                "      \"name\": \"Account Status (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"STATUS\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"11bf1e7a-0b8a-4842-bffb-2281fff5e057\",\n" +
                                "      \"name\": \"Install Fulfillment Party (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"INSTALL_FULFILLMENT_PARTY\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"c7c21097-cb2a-41d3-a9e3-80c6e9d607b3\",\n" +
                                "      \"name\": \"Account Past Due (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"ACCOUNT_PAST_DUE\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"921d0cd2-b1b6-40a7-b4f0-123c3aeeaae5\",\n" +
                                "      \"name\": \"Business Confidence Level (CSC)\",\n" +
                                "      \"evaluated\": true,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": []\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"d0506711-f8d7-40f6-8040-2f6caae5815f\",\n" +
                                "      \"name\": \"Formers Status (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"FORMERS_STATUS\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"75f58418-3de5-477f-8d56-ff8a8611741e\",\n" +
                                "      \"name\": \"EFT_Payment_Allowed (CSC)\",\n" +
                                "      \"evaluated\": true,\n" +
                                "      \"matchedSegments\": [\n" +
                                "        \"06f5f3c1-7c4d-449f-b143-87d1c5cbfe95\"\n" +
                                "      ],\n" +
                                "      \"requiredCriteria\": []\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"c2d9ce42-08e1-4447-b522-efd83dbdced7\",\n" +
                                "      \"name\": \"Credit Qualification (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"CREDIT_BUREAU_NAME\",\n" +
                                "        \"CREDIT_SCORE_MIN\",\n" +
                                "        \"CREDIT_SCORE_MAX\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"d81cfb24-5b1d-41b5-b992-3a251f08c55c\",\n" +
                                "      \"name\": \"Line of Service Status (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"LINE_OF_SERVICE_STATUS\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"99499b22-88b0-4c2e-8898-08e4e0525594\",\n" +
                                "      \"name\": \"Credit_Card_Allowed (CSC)\",\n" +
                                "      \"evaluated\": true,\n" +
                                "      \"matchedSegments\": [\n" +
                                "        \"2145fbd3-8507-4d39-9777-e87c83d83cd8\"\n" +
                                "      ],\n" +
                                "      \"requiredCriteria\": []\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"adf6a01b-bec3-4d05-9901-26d0f21811e5\",\n" +
                                "      \"name\": \"Account Type (CSC)\",\n" +
                                "      \"evaluated\": true,\n" +
                                "      \"matchedSegments\": [\n" +
                                "        \"c507ce45-9542-4c17-b10b-75440627f953\"\n" +
                                "      ],\n" +
                                "      \"requiredCriteria\": []\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"c0e323e7-01d4-4d29-943b-4c275515d376\",\n" +
                                "      \"name\": \"Certificate (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"CERTIFICATE\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"0d546663-7398-433d-88cd-4d12ede21740\",\n" +
                                "      \"name\": \"Equipment Change Restricted (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"EQUIPMENT_CHANGE_RESTRICTED\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"0c9ba860-9ce5-42d3-ad70-21175bf8d361\",\n" +
                                "      \"name\": \"Claim Code (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"CLAIM_CODE\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"8aba2379-64d2-4391-b9bc-d246bd821507\",\n" +
                                "      \"name\": \"Order Reason (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"ORDER_REASON\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"796dbef7-ed1d-4cd2-9975-28a6a15fcf60\",\n" +
                                "      \"name\": \"Payment Type (CSC)\",\n" +
                                "      \"evaluated\": true,\n" +
                                "      \"matchedSegments\": [\n" +
                                "      ],\n" +
                                "      \"requiredCriteria\": []\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"ebff706e-7a18-4e63-a0dc-1a21e431b2c8\",\n" +
                                "      \"name\": \"Star Tier (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"STAR_TIER_VALUE\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"72e1854f-688a-40e8-ba12-8143f8c636b1\",\n" +
                                "      \"name\": \"Line of Sight (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"LINE_OF_SIGHT\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"dcdeecf4-0a00-4f94-93b6-4aa33ba8ab0a\",\n" +
                                "      \"name\": \"Migration Preference (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"OPT_IN\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"ec90bca8-1fab-4bdf-b01f-5cbe2d1b400a\",\n" +
                                "      \"name\": \"Dish Pause (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"DISH_PAUSE\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"d594f604-29b9-4abc-8ed6-b23e6a072c04\",\n" +
                                "      \"name\": \"MQ2 Module (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"MQ2_MODULE\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"bd0e18a8-0638-496a-ad16-0784aa5ad5b0\",\n" +
                                "      \"name\": \"SDS Property Type (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"SDS_PROPERTY_TYPE\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"8eb548e8-4506-4714-b310-4800ac886d62\",\n" +
                                "      \"name\": \"Geographical Location (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"ZIP_CODE\",\n" +
                                "        \"STATE\",\n" +
                                "        \"DMA\"\n" +
                                "      ]\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"guid\": \"975cd5a5-c15a-475a-a945-5d67ccdbbba0\",\n" +
                                "      \"name\": \"Estimated Viewing Occupancy (CSC)\",\n" +
                                "      \"evaluated\": false,\n" +
                                "      \"matchedSegments\": [],\n" +
                                "      \"requiredCriteria\": [\n" +
                                "        \"EVO_TIER\"\n" +
                                "      ]\n" +
                                "    }\n" +
                                "  ],\n" +
                                "  \"cart\": {\n" +
                                "    \"totalAmount\": 100.00\n" +
                                "  }\n" +
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
    } // showSegmentsVersion

    static String EvalPayments(String inAmount) throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // get REST response
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

        // send REST request
        String endPoint = "https://evaluator-payment.integration.apps.int.dev.mer.cfdish.io:443/paymentMethods/evaluate";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                "{\n" +
                        "  \"customerSegmentCategories\": [\n" +
                        "    {\n" +
                        "      \"guid\": \"1b79326c-6f13-4266-b0fc-8810e0aba595\",\n" +
                        "      \"name\": \"Account Status (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"STATUS\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"11bf1e7a-0b8a-4842-bffb-2281fff5e057\",\n" +
                        "      \"name\": \"Install Fulfillment Party (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"INSTALL_FULFILLMENT_PARTY\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"c7c21097-cb2a-41d3-a9e3-80c6e9d607b3\",\n" +
                        "      \"name\": \"Account Past Due (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"ACCOUNT_PAST_DUE\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"921d0cd2-b1b6-40a7-b4f0-123c3aeeaae5\",\n" +
                        "      \"name\": \"Business Confidence Level (CSC)\",\n" +
                        "      \"evaluated\": true,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": []\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"d0506711-f8d7-40f6-8040-2f6caae5815f\",\n" +
                        "      \"name\": \"Formers Status (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"FORMERS_STATUS\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"75f58418-3de5-477f-8d56-ff8a8611741e\",\n" +
                        "      \"name\": \"EFT_Payment_Allowed (CSC)\",\n" +
                        "      \"evaluated\": true,\n" +
                        "      \"matchedSegments\": [\n" +
                        "        \"06f5f3c1-7c4d-449f-b143-87d1c5cbfe95\"\n" +
                        "      ],\n" +
                        "      \"requiredCriteria\": []\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"c2d9ce42-08e1-4447-b522-efd83dbdced7\",\n" +
                        "      \"name\": \"Credit Qualification (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"CREDIT_BUREAU_NAME\",\n" +
                        "        \"CREDIT_SCORE_MIN\",\n" +
                        "        \"CREDIT_SCORE_MAX\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"d81cfb24-5b1d-41b5-b992-3a251f08c55c\",\n" +
                        "      \"name\": \"Line of Service Status (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"LINE_OF_SERVICE_STATUS\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"99499b22-88b0-4c2e-8898-08e4e0525594\",\n" +
                        "      \"name\": \"Credit_Card_Allowed (CSC)\",\n" +
                        "      \"evaluated\": true,\n" +
                        "      \"matchedSegments\": [\n" +
                        "        \"2145fbd3-8507-4d39-9777-e87c83d83cd8\"\n" +
                        "      ],\n" +
                        "      \"requiredCriteria\": []\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"adf6a01b-bec3-4d05-9901-26d0f21811e5\",\n" +
                        "      \"name\": \"Account Type (CSC)\",\n" +
                        "      \"evaluated\": true,\n" +
                        "      \"matchedSegments\": [\n" +
                        "        \"c507ce45-9542-4c17-b10b-75440627f953\"\n" +
                        "      ],\n" +
                        "      \"requiredCriteria\": []\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"c0e323e7-01d4-4d29-943b-4c275515d376\",\n" +
                        "      \"name\": \"Certificate (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"CERTIFICATE\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"0d546663-7398-433d-88cd-4d12ede21740\",\n" +
                        "      \"name\": \"Equipment Change Restricted (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"EQUIPMENT_CHANGE_RESTRICTED\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"0c9ba860-9ce5-42d3-ad70-21175bf8d361\",\n" +
                        "      \"name\": \"Claim Code (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"CLAIM_CODE\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"8aba2379-64d2-4391-b9bc-d246bd821507\",\n" +
                        "      \"name\": \"Order Reason (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"ORDER_REASON\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"796dbef7-ed1d-4cd2-9975-28a6a15fcf60\",\n" +
                        "      \"name\": \"Payment Type (CSC)\",\n" +
                        "      \"evaluated\": true,\n" +
                        "      \"matchedSegments\": [\n" +
                        "      ],\n" +
                        "      \"requiredCriteria\": []\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"ebff706e-7a18-4e63-a0dc-1a21e431b2c8\",\n" +
                        "      \"name\": \"Star Tier (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"STAR_TIER_VALUE\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"72e1854f-688a-40e8-ba12-8143f8c636b1\",\n" +
                        "      \"name\": \"Line of Sight (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"LINE_OF_SIGHT\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"dcdeecf4-0a00-4f94-93b6-4aa33ba8ab0a\",\n" +
                        "      \"name\": \"Migration Preference (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"OPT_IN\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"ec90bca8-1fab-4bdf-b01f-5cbe2d1b400a\",\n" +
                        "      \"name\": \"Dish Pause (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"DISH_PAUSE\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"d594f604-29b9-4abc-8ed6-b23e6a072c04\",\n" +
                        "      \"name\": \"MQ2 Module (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"MQ2_MODULE\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"bd0e18a8-0638-496a-ad16-0784aa5ad5b0\",\n" +
                        "      \"name\": \"SDS Property Type (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"SDS_PROPERTY_TYPE\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"8eb548e8-4506-4714-b310-4800ac886d62\",\n" +
                        "      \"name\": \"Geographical Location (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"ZIP_CODE\",\n" +
                        "        \"STATE\",\n" +
                        "        \"DMA\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"guid\": \"975cd5a5-c15a-475a-a945-5d67ccdbbba0\",\n" +
                        "      \"name\": \"Estimated Viewing Occupancy (CSC)\",\n" +
                        "      \"evaluated\": false,\n" +
                        "      \"matchedSegments\": [],\n" +
                        "      \"requiredCriteria\": [\n" +
                        "        \"EVO_TIER\"\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"cart\": {\n" +
                        "    \"totalAmount\": " + inAmount + "\n" +
                        "  }\n" +
                        "}");
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

        // show the input parameter & status code
        //System.out.format(pad, inAmount, response.getStatusLine().getStatusCode() );
        //System.out.println("response: " + fullResponse);

        // return the fullResponse
        return fullResponse;
    }

} // end class