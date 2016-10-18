/***
 * Developer:   carlos.loya
 * Date:        4/25/2016
 * Purpose:     Tests the Beacon Tech REST service adapter
 * Note:        this class overrides Self-Signed Certificate
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

public class testDES
{
    public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
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

        // send REST request
        String endPoint = "https://des-service.test.apps.int.test.mer.cfdish.io/des-event?sessionId=5dda2ad5-62fa-4f7f-ad1f-221148f826ab&agentId=carlos.loya";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                "{\n" +
                        "       \"context\": {\n" +
                        "         \"customerFacingTool\": \"BEACON\",\n" +
                        "         \"interactionId\": \"5dda2ad5-62fa-4f7f-ad1f-221148f826ab\",\n" +
                        "         \"sourceIpAddress\": \"0.0.0.0\",\n" +
                        "         \"operatorId\": \"123\"\n" +
                        "       },\n" +
                        "       \"request\": [\n" +
                        "         {\n" +
                        "           \"businessEventName\": \"external-order-event\",\n" +
                        "           \"eventDateTime\": \"2015-11-24T15:22:38.577\",\n" +
                        "           \"payload\": {\n" +
                        "             \"action\": \"create\",\n" +
                        "             \"attributes\": [\n" +
                        "               {\n" +
                        "                 \"name\": \"billingAccountNumber\",\n" +
                        "                 \"value\": \"80469000121209871\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"name\": \"additionalPassThroughInformationJSON\",\n" +
                        "                 \"value\": \"{\\\"agentID\\\":\\\"David.Eastridge\\\",\\\"workOrderID\\\":\\\"100001112232345\\\",\\\"emailAddress\\\":\\\"ROBERT.GREGORY@DISH.COM\\\",\\\"cart\\\":[{\\\"offeringId\\\":\\\"175741391\\\",\\\"offeringName\\\":\\\"Install TV On Stand\\\",\\\"totalOfferingAmount\\\":0.00,\\\"offeringTypeQualifier\\\":\\\"MultiComponentOffering\\\",\\\"productTypeQualifier\\\":\\\"LaborProduct\\\",\\\"installationPoints\\\":6,\\\"parentOfferingId\\\":null,\\\"quantity\\\":2,\\\"autoAdd\\\":false},{\\\"offeringId\\\":\\\"175741381\\\",\\\"offeringName\\\":\\\"Install TV On Stand\\\",\\\"totalOfferingAmount\\\":198.98,\\\"offeringTypeQualifier\\\":\\\"OFMProductOffering\\\",\\\"productTypeQualifier\\\":\\\"GenericProduct\\\",\\\"installationPoints\\\":0,\\\"parentOfferingId\\\":\\\"175741391\\\",\\\"quantity\\\":2,\\\"autoAdd\\\":false}]}\"\n" +
                        "               }\n" +
                        "             ],\n" +
                        "             \"customer\": {\n" +
                        "               \"name\": {\n" +
                        "                 \"firstName\": \"KING\",\n" +
                        "                 \"lastName\": \"DYKES\"\n" +
                        "               },\n" +
                        "               \"addresses\": [\n" +
                        "                 {\n" +
                        "                   \"addressType\": \"SERVICE\",\n" +
                        "                   \"addressLine1\": \"609 OKOBOJI AVE\",\n" +
                        "                   \"addressLine2\": \"\",\n" +
                        "                   \"city\": \"MILFORD\",\n" +
                        "                   \"state\": \"IA\",\n" +
                        "                   \"zip\": \"51351\",\n" +
                        "                   \"zipPlus4\": \"1755\"\n" +
                        "                 }\n" +
                        "               ],\n" +
                        "               \"phones\": [\n" +
                        "                 {\n" +
                        "                   \"phoneType\": \"HOME\",\n" +
                        "                   \"phoneNumber\": \"3052564751\"\n" +
                        "                 }\n" +
                        "               ]\n" +
                        "             },\n" +
                        "             \"orderLines\": [\n" +
                        "               {\n" +
                        "                 \"id\": \"1\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741391\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"2\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741391\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"3\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741381\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"4\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741381\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               }\n" +
                        "             ]\n" +
                        "           }\n" +
                        "         }\n" +
                        "       ]\n" +
                        "     }");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        // show status code
        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );

        // show response
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent() ) );
        String line = "";
        while ((line = rd.readLine() ) != null)
        {
            System.out.println(line);
        }

        // show the service adapter version
        System.out.println("version: " + showVersion() );

    } // end main

    static void showStatusCode() throws ClientProtocolException, IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException
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

        // send REST request
        String endPoint = "https://des-service.test.apps.int.test.mer.cfdish.io/des-event?sessionId=5dda2ad5-62fa-4f7f-ad1f-221148f826ab&agentId=carlos.loya";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                        "{\n" +
                        "       \"context\": {\n" +
                        "         \"customerFacingTool\": \"BEACON\",\n" +
                        "         \"interactionId\": \"5dda2ad5-62fa-4f7f-ad1f-221148f826ab\",\n" +
                        "         \"sourceIpAddress\": \"0.0.0.0\",\n" +
                        "         \"operatorId\": \"123\"\n" +
                        "       },\n" +
                        "       \"request\": [\n" +
                        "         {\n" +
                        "           \"businessEventName\": \"external-order-event\",\n" +
                        "           \"eventDateTime\": \"2015-11-24T15:22:38.577\",\n" +
                        "           \"payload\": {\n" +
                        "             \"action\": \"create\",\n" +
                        "             \"attributes\": [\n" +
                        "               {\n" +
                        "                 \"name\": \"billingAccountNumber\",\n" +
                        "                 \"value\": \"80469000121209871\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"name\": \"additionalPassThroughInformationJSON\",\n" +
                        "                 \"value\": \"{\\\"agentID\\\":\\\"David.Eastridge\\\",\\\"workOrderID\\\":\\\"100001112232345\\\",\\\"emailAddress\\\":\\\"ROBERT.GREGORY@DISH.COM\\\",\\\"cart\\\":[{\\\"offeringId\\\":\\\"175741391\\\",\\\"offeringName\\\":\\\"Install TV On Stand\\\",\\\"totalOfferingAmount\\\":0.00,\\\"offeringTypeQualifier\\\":\\\"MultiComponentOffering\\\",\\\"productTypeQualifier\\\":\\\"LaborProduct\\\",\\\"installationPoints\\\":6,\\\"parentOfferingId\\\":null,\\\"quantity\\\":2,\\\"autoAdd\\\":false},{\\\"offeringId\\\":\\\"175741381\\\",\\\"offeringName\\\":\\\"Install TV On Stand\\\",\\\"totalOfferingAmount\\\":198.98,\\\"offeringTypeQualifier\\\":\\\"OFMProductOffering\\\",\\\"productTypeQualifier\\\":\\\"GenericProduct\\\",\\\"installationPoints\\\":0,\\\"parentOfferingId\\\":\\\"175741391\\\",\\\"quantity\\\":2,\\\"autoAdd\\\":false}]}\"\n" +
                        "               }\n" +
                        "             ],\n" +
                        "             \"customer\": {\n" +
                        "               \"name\": {\n" +
                        "                 \"firstName\": \"KING\",\n" +
                        "                 \"lastName\": \"DYKES\"\n" +
                        "               },\n" +
                        "               \"addresses\": [\n" +
                        "                 {\n" +
                        "                   \"addressType\": \"SERVICE\",\n" +
                        "                   \"addressLine1\": \"609 OKOBOJI AVE\",\n" +
                        "                   \"addressLine2\": \"\",\n" +
                        "                   \"city\": \"MILFORD\",\n" +
                        "                   \"state\": \"IA\",\n" +
                        "                   \"zip\": \"51351\",\n" +
                        "                   \"zipPlus4\": \"1755\"\n" +
                        "                 }\n" +
                        "               ],\n" +
                        "               \"phones\": [\n" +
                        "                 {\n" +
                        "                   \"phoneType\": \"HOME\",\n" +
                        "                   \"phoneNumber\": \"3052564751\"\n" +
                        "                 }\n" +
                        "               ]\n" +
                        "             },\n" +
                        "             \"orderLines\": [\n" +
                        "               {\n" +
                        "                 \"id\": \"1\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741391\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"2\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741391\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"3\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741381\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"4\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741381\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               }\n" +
                        "             ]\n" +
                        "           }\n" +
                        "         }\n" +
                        "       ]\n" +
                        "     }");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );
    }

    static int getStatusCode() throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
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
        String endPoint = "https://des-service.test.apps.int.test.mer.cfdish.io/des-event?sessionId=5dda2ad5-62fa-4f7f-ad1f-221148f826ab&agentId=carlos.loya";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                        "{\n" +
                        "       \"context\": {\n" +
                        "         \"customerFacingTool\": \"BEACON\",\n" +
                        "         \"interactionId\": \"5dda2ad5-62fa-4f7f-ad1f-221148f826ab\",\n" +
                        "         \"sourceIpAddress\": \"0.0.0.0\",\n" +
                        "         \"operatorId\": \"123\"\n" +
                        "       },\n" +
                        "       \"request\": [\n" +
                        "         {\n" +
                        "           \"businessEventName\": \"external-order-event\",\n" +
                        "           \"eventDateTime\": \"2015-11-24T15:22:38.577\",\n" +
                        "           \"payload\": {\n" +
                        "             \"action\": \"create\",\n" +
                        "             \"attributes\": [\n" +
                        "               {\n" +
                        "                 \"name\": \"billingAccountNumber\",\n" +
                        "                 \"value\": \"80469000121209871\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"name\": \"additionalPassThroughInformationJSON\",\n" +
                        "                 \"value\": \"{\\\"agentID\\\":\\\"David.Eastridge\\\",\\\"workOrderID\\\":\\\"100001112232345\\\",\\\"emailAddress\\\":\\\"ROBERT.GREGORY@DISH.COM\\\",\\\"cart\\\":[{\\\"offeringId\\\":\\\"175741391\\\",\\\"offeringName\\\":\\\"Install TV On Stand\\\",\\\"totalOfferingAmount\\\":0.00,\\\"offeringTypeQualifier\\\":\\\"MultiComponentOffering\\\",\\\"productTypeQualifier\\\":\\\"LaborProduct\\\",\\\"installationPoints\\\":6,\\\"parentOfferingId\\\":null,\\\"quantity\\\":2,\\\"autoAdd\\\":false},{\\\"offeringId\\\":\\\"175741381\\\",\\\"offeringName\\\":\\\"Install TV On Stand\\\",\\\"totalOfferingAmount\\\":198.98,\\\"offeringTypeQualifier\\\":\\\"OFMProductOffering\\\",\\\"productTypeQualifier\\\":\\\"GenericProduct\\\",\\\"installationPoints\\\":0,\\\"parentOfferingId\\\":\\\"175741391\\\",\\\"quantity\\\":2,\\\"autoAdd\\\":false}]}\"\n" +
                        "               }\n" +
                        "             ],\n" +
                        "             \"customer\": {\n" +
                        "               \"name\": {\n" +
                        "                 \"firstName\": \"KING\",\n" +
                        "                 \"lastName\": \"DYKES\"\n" +
                        "               },\n" +
                        "               \"addresses\": [\n" +
                        "                 {\n" +
                        "                   \"addressType\": \"SERVICE\",\n" +
                        "                   \"addressLine1\": \"609 OKOBOJI AVE\",\n" +
                        "                   \"addressLine2\": \"\",\n" +
                        "                   \"city\": \"MILFORD\",\n" +
                        "                   \"state\": \"IA\",\n" +
                        "                   \"zip\": \"51351\",\n" +
                        "                   \"zipPlus4\": \"1755\"\n" +
                        "                 }\n" +
                        "               ],\n" +
                        "               \"phones\": [\n" +
                        "                 {\n" +
                        "                   \"phoneType\": \"HOME\",\n" +
                        "                   \"phoneNumber\": \"3052564751\"\n" +
                        "                 }\n" +
                        "               ]\n" +
                        "             },\n" +
                        "             \"orderLines\": [\n" +
                        "               {\n" +
                        "                 \"id\": \"1\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741391\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"2\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741391\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"3\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741381\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"4\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741381\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               }\n" +
                        "             ]\n" +
                        "           }\n" +
                        "         }\n" +
                        "       ]\n" +
                        "     }");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        return response.getStatusLine().getStatusCode();
    }

    static String showVersion() throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
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
        String endPoint = "https://des-service.test.apps.int.test.mer.cfdish.io/des-event?sessionId=5dda2ad5-62fa-4f7f-ad1f-221148f826ab&agentId=carlos.loya";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                        "{\n" +
                        "       \"context\": {\n" +
                        "         \"customerFacingTool\": \"BEACON\",\n" +
                        "         \"interactionId\": \"5dda2ad5-62fa-4f7f-ad1f-221148f826ab\",\n" +
                        "         \"sourceIpAddress\": \"0.0.0.0\",\n" +
                        "         \"operatorId\": \"123\"\n" +
                        "       },\n" +
                        "       \"request\": [\n" +
                        "         {\n" +
                        "           \"businessEventName\": \"external-order-event\",\n" +
                        "           \"eventDateTime\": \"2015-11-24T15:22:38.577\",\n" +
                        "           \"payload\": {\n" +
                        "             \"action\": \"create\",\n" +
                        "             \"attributes\": [\n" +
                        "               {\n" +
                        "                 \"name\": \"billingAccountNumber\",\n" +
                        "                 \"value\": \"80469000121209871\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"name\": \"additionalPassThroughInformationJSON\",\n" +
                        "                 \"value\": \"{\\\"agentID\\\":\\\"David.Eastridge\\\",\\\"workOrderID\\\":\\\"100001112232345\\\",\\\"emailAddress\\\":\\\"ROBERT.GREGORY@DISH.COM\\\",\\\"cart\\\":[{\\\"offeringId\\\":\\\"175741391\\\",\\\"offeringName\\\":\\\"Install TV On Stand\\\",\\\"totalOfferingAmount\\\":0.00,\\\"offeringTypeQualifier\\\":\\\"MultiComponentOffering\\\",\\\"productTypeQualifier\\\":\\\"LaborProduct\\\",\\\"installationPoints\\\":6,\\\"parentOfferingId\\\":null,\\\"quantity\\\":2,\\\"autoAdd\\\":false},{\\\"offeringId\\\":\\\"175741381\\\",\\\"offeringName\\\":\\\"Install TV On Stand\\\",\\\"totalOfferingAmount\\\":198.98,\\\"offeringTypeQualifier\\\":\\\"OFMProductOffering\\\",\\\"productTypeQualifier\\\":\\\"GenericProduct\\\",\\\"installationPoints\\\":0,\\\"parentOfferingId\\\":\\\"175741391\\\",\\\"quantity\\\":2,\\\"autoAdd\\\":false}]}\"\n" +
                        "               }\n" +
                        "             ],\n" +
                        "             \"customer\": {\n" +
                        "               \"name\": {\n" +
                        "                 \"firstName\": \"KING\",\n" +
                        "                 \"lastName\": \"DYKES\"\n" +
                        "               },\n" +
                        "               \"addresses\": [\n" +
                        "                 {\n" +
                        "                   \"addressType\": \"SERVICE\",\n" +
                        "                   \"addressLine1\": \"609 OKOBOJI AVE\",\n" +
                        "                   \"addressLine2\": \"\",\n" +
                        "                   \"city\": \"MILFORD\",\n" +
                        "                   \"state\": \"IA\",\n" +
                        "                   \"zip\": \"51351\",\n" +
                        "                   \"zipPlus4\": \"1755\"\n" +
                        "                 }\n" +
                        "               ],\n" +
                        "               \"phones\": [\n" +
                        "                 {\n" +
                        "                   \"phoneType\": \"HOME\",\n" +
                        "                   \"phoneNumber\": \"3052564751\"\n" +
                        "                 }\n" +
                        "               ]\n" +
                        "             },\n" +
                        "             \"orderLines\": [\n" +
                        "               {\n" +
                        "                 \"id\": \"1\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741391\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"2\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741391\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"3\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741381\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               },\n" +
                        "               {\n" +
                        "                 \"id\": \"4\",\n" +
                        "                 \"action\": \"add\",\n" +
                        "                 \"productId\": \"175741381\",\n" +
                        "                 \"expectedCompletionDate\": \"2016-01-12\"\n" +
                        "               }\n" +
                        "             ]\n" +
                        "           }\n" +
                        "         }\n" +
                        "       ]\n" +
                        "     }");
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