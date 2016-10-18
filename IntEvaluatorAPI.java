/***
 * Developer:   carlos.loya
 * Date:        4/20/2016
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

public class IntEvaluatorAPI
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
        String endPoint = "https://apps-int.global.dish.com/offering-evaluator/catalog";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                                        "{\n" +
                                        "   \"accountData\": {\n" +
                                        "      \"accountAttributes\": {\n" +
                                        "         \"attributes\": {\n" +
                                        "            \"MANAGEMENT_GROUP\": \"003\",\n" +
                                        "            \"PARTNER\": \"DISH\",\n" +
                                        "            \"ACCOUNT_TYPE\": \"RESIDENTIAL\",\n" +
                                        "            \"BILLING_CHANNEL\": \"DISH\",\n" +
                                        "            \"ACCOUNT_SUB_TYPE\": \"NONE\",\n" +
                                        "            \"ZONE\": null,\n" +
                                        "            \"CARD_PAYMENT_ALLOWED\": \"true\",\n" +
                                        "            \"EFT_PAYMENT_ALLOWED\": \"true\"\n" +
                                        "            }\n" +
                                        "        },\n" +
                                        "        \"accountInfo\": {\n" +
                                        "            \"customerName\": {\n" +
                                        "                \"givenName\": \"LOYA\",\n" +
                                        "                \"familyName\": \"CARLOS\"\n" +
                                        "            },\n" +
                                        "            \"accountId\": \"8046909802540808\",\n" +
                                        "            \"serviceAddress\": {\n" +
                                        "                \"line1\": \"7883 E PRINCETON AVE\",\n" +
                                        "                \"line2\": \"\",\n" +
                                        "                \"city\": \"DENVER\",\n" +
                                        "                \"state\": \"CO\",\n" +
                                        "                \"zipCode\": \"80237\",\n" +
                                        "                \"zipCodeExtension\": \"2404\"\n" +
                                        "            },\n" +
                                        "            \"billingAddress\": {\n" +
                                        "                \"line1\": \"7883 E PRINCETON AVE\",\n" +
                                        "                \"line2\": null,\n" +
                                        "                \"city\": \"DENVER\",\n" +
                                        "                \"state\": \"CO\",\n" +
                                        "                \"zipCode\": \"80237\",\n" +
                                        "                \"zipCodeExtension\": null\n" +
                                        "            },\n" +
                                        "            \"phone1\": {\n" +
                                        "                \"phone\": \"3608203984\",\n" +
                                        "                \"type\": \"HOME\"\n" +
                                        "            },\n" +
                                        "            \"phone2\": {\n" +
                                        "                \"phone\": \"3608203984\",\n" +
                                        "                \"type\": \"HOME\"\n" +
                                        "            },\n" +
                                        "            \"emailAddress\": \"noemaila@dish.com\",\n" +
                                        "            \"managementGroup\": null,\n" +
                                        "            \"cycleCode\": 14,\n" +
                                        "            \"geoCode\": \"060310140\",\n" +
                                        "            \"billingType\": \"1\",\n" +
                                        "            \"connectDate\": \"2016-04-14\",\n" +
                                        "            \"pendingPayment\": 0.0,\n" +
                                        "            \"currentBalance\": -252.61,\n" +
                                        "            \"billingFromDate\": \"2016-04-14\",\n" +
                                        "            \"billingToDate\": \"2016-05-13\",\n" +
                                        "            \"primaryStatus\": \"ACTIVE\"\n" +
                                        "        },\n" +
                                        "        \"offeringList\": [\n" +
                                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                                        "           ]\n" +
                                        "    },\n" +
                                        "    \"agentData\": {\n" +
                                        "        \"agentOperatorId\": \"6BH\",\n" +
                                        "        \"privilegesList\": null,\n" +
                                        "        \"id\": \"carlos.loya@dish.com\",\n" +
                                        "        \"agentRoles\": [\"Tech\"]\n" +
                                        "    }\n" +
                                        "}");
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
        String endPoint = "https://apps-int.global.dish.com/offering-evaluator/catalog";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                        "{\n" +
                        "   \"accountData\": {\n" +
                        "      \"accountAttributes\": {\n" +
                        "         \"attributes\": {\n" +
                        "            \"MANAGEMENT_GROUP\": \"003\",\n" +
                        "            \"PARTNER\": \"DISH\",\n" +
                        "            \"ACCOUNT_TYPE\": \"RESIDENTIAL\",\n" +
                        "            \"BILLING_CHANNEL\": \"DISH\",\n" +
                        "            \"ACCOUNT_SUB_TYPE\": \"NONE\",\n" +
                        "            \"ZONE\": null,\n" +
                        "            \"CARD_PAYMENT_ALLOWED\": \"true\",\n" +
                        "            \"EFT_PAYMENT_ALLOWED\": \"true\"\n" +
                        "            }\n" +
                        "        },\n" +
                        "        \"accountInfo\": {\n" +
                        "            \"customerName\": {\n" +
                        "                \"givenName\": \"LOYA\",\n" +
                        "                \"familyName\": \"CARLOS\"\n" +
                        "            },\n" +
                        "            \"accountId\": \"8046909802540808\",\n" +
                        "            \"serviceAddress\": {\n" +
                        "                \"line1\": \"7883 E PRINCETON AVE\",\n" +
                        "                \"line2\": \"\",\n" +
                        "                \"city\": \"DENVER\",\n" +
                        "                \"state\": \"CO\",\n" +
                        "                \"zipCode\": \"80237\",\n" +
                        "                \"zipCodeExtension\": \"2404\"\n" +
                        "            },\n" +
                        "            \"billingAddress\": {\n" +
                        "                \"line1\": \"7883 E PRINCETON AVE\",\n" +
                        "                \"line2\": null,\n" +
                        "                \"city\": \"DENVER\",\n" +
                        "                \"state\": \"CO\",\n" +
                        "                \"zipCode\": \"80237\",\n" +
                        "                \"zipCodeExtension\": null\n" +
                        "            },\n" +
                        "            \"phone1\": {\n" +
                        "                \"phone\": \"3608203984\",\n" +
                        "                \"type\": \"HOME\"\n" +
                        "            },\n" +
                        "            \"phone2\": {\n" +
                        "                \"phone\": \"3608203984\",\n" +
                        "                \"type\": \"HOME\"\n" +
                        "            },\n" +
                        "            \"emailAddress\": \"noemaila@dish.com\",\n" +
                        "            \"managementGroup\": null,\n" +
                        "            \"cycleCode\": 14,\n" +
                        "            \"geoCode\": \"060310140\",\n" +
                        "            \"billingType\": \"1\",\n" +
                        "            \"connectDate\": \"2016-04-14\",\n" +
                        "            \"pendingPayment\": 0.0,\n" +
                        "            \"currentBalance\": -252.61,\n" +
                        "            \"billingFromDate\": \"2016-04-14\",\n" +
                        "            \"billingToDate\": \"2016-05-13\",\n" +
                        "            \"primaryStatus\": \"ACTIVE\"\n" +
                        "        },\n" +
                        "        \"offeringList\": [\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           ]\n" +
                        "    },\n" +
                        "    \"agentData\": {\n" +
                        "        \"agentOperatorId\": \"6BH\",\n" +
                        "        \"privilegesList\": null,\n" +
                        "        \"id\": \"carlos.loya@dish.com\",\n" +
                        "        \"agentRoles\": [\"Tech\"]\n" +
                        "    }\n" +
                        "}");
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
        String endPoint = "https://apps-int.global.dish.com/offering-evaluator/catalog";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                        "{\n" +
                        "   \"accountData\": {\n" +
                        "      \"accountAttributes\": {\n" +
                        "         \"attributes\": {\n" +
                        "            \"MANAGEMENT_GROUP\": \"003\",\n" +
                        "            \"PARTNER\": \"DISH\",\n" +
                        "            \"ACCOUNT_TYPE\": \"RESIDENTIAL\",\n" +
                        "            \"BILLING_CHANNEL\": \"DISH\",\n" +
                        "            \"ACCOUNT_SUB_TYPE\": \"NONE\",\n" +
                        "            \"ZONE\": null,\n" +
                        "            \"CARD_PAYMENT_ALLOWED\": \"true\",\n" +
                        "            \"EFT_PAYMENT_ALLOWED\": \"true\"\n" +
                        "            }\n" +
                        "        },\n" +
                        "        \"accountInfo\": {\n" +
                        "            \"customerName\": {\n" +
                        "                \"givenName\": \"LOYA\",\n" +
                        "                \"familyName\": \"CARLOS\"\n" +
                        "            },\n" +
                        "            \"accountId\": \"8046909802540808\",\n" +
                        "            \"serviceAddress\": {\n" +
                        "                \"line1\": \"7883 E PRINCETON AVE\",\n" +
                        "                \"line2\": \"\",\n" +
                        "                \"city\": \"DENVER\",\n" +
                        "                \"state\": \"CO\",\n" +
                        "                \"zipCode\": \"80237\",\n" +
                        "                \"zipCodeExtension\": \"2404\"\n" +
                        "            },\n" +
                        "            \"billingAddress\": {\n" +
                        "                \"line1\": \"7883 E PRINCETON AVE\",\n" +
                        "                \"line2\": null,\n" +
                        "                \"city\": \"DENVER\",\n" +
                        "                \"state\": \"CO\",\n" +
                        "                \"zipCode\": \"80237\",\n" +
                        "                \"zipCodeExtension\": null\n" +
                        "            },\n" +
                        "            \"phone1\": {\n" +
                        "                \"phone\": \"3608203984\",\n" +
                        "                \"type\": \"HOME\"\n" +
                        "            },\n" +
                        "            \"phone2\": {\n" +
                        "                \"phone\": \"3608203984\",\n" +
                        "                \"type\": \"HOME\"\n" +
                        "            },\n" +
                        "            \"emailAddress\": \"noemaila@dish.com\",\n" +
                        "            \"managementGroup\": null,\n" +
                        "            \"cycleCode\": 14,\n" +
                        "            \"geoCode\": \"060310140\",\n" +
                        "            \"billingType\": \"1\",\n" +
                        "            \"connectDate\": \"2016-04-14\",\n" +
                        "            \"pendingPayment\": 0.0,\n" +
                        "            \"currentBalance\": -252.61,\n" +
                        "            \"billingFromDate\": \"2016-04-14\",\n" +
                        "            \"billingToDate\": \"2016-05-13\",\n" +
                        "            \"primaryStatus\": \"ACTIVE\"\n" +
                        "        },\n" +
                        "        \"offeringList\": [\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           ]\n" +
                        "    },\n" +
                        "    \"agentData\": {\n" +
                        "        \"agentOperatorId\": \"6BH\",\n" +
                        "        \"privilegesList\": null,\n" +
                        "        \"id\": \"carlos.loya@dish.com\",\n" +
                        "        \"agentRoles\": [\"Tech\"]\n" +
                        "    }\n" +
                        "}");
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
        String endPoint = "https://apps-int.global.dish.com/offering-evaluator/catalog";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                        "{\n" +
                        "   \"accountData\": {\n" +
                        "      \"accountAttributes\": {\n" +
                        "         \"attributes\": {\n" +
                        "            \"MANAGEMENT_GROUP\": \"003\",\n" +
                        "            \"PARTNER\": \"DISH\",\n" +
                        "            \"ACCOUNT_TYPE\": \"RESIDENTIAL\",\n" +
                        "            \"BILLING_CHANNEL\": \"DISH\",\n" +
                        "            \"ACCOUNT_SUB_TYPE\": \"NONE\",\n" +
                        "            \"ZONE\": null,\n" +
                        "            \"CARD_PAYMENT_ALLOWED\": \"true\",\n" +
                        "            \"EFT_PAYMENT_ALLOWED\": \"true\"\n" +
                        "            }\n" +
                        "        },\n" +
                        "        \"accountInfo\": {\n" +
                        "            \"customerName\": {\n" +
                        "                \"givenName\": \"LOYA\",\n" +
                        "                \"familyName\": \"CARLOS\"\n" +
                        "            },\n" +
                        "            \"accountId\": \"8046909802540808\",\n" +
                        "            \"serviceAddress\": {\n" +
                        "                \"line1\": \"7883 E PRINCETON AVE\",\n" +
                        "                \"line2\": \"\",\n" +
                        "                \"city\": \"DENVER\",\n" +
                        "                \"state\": \"CO\",\n" +
                        "                \"zipCode\": \"80237\",\n" +
                        "                \"zipCodeExtension\": \"2404\"\n" +
                        "            },\n" +
                        "            \"billingAddress\": {\n" +
                        "                \"line1\": \"7883 E PRINCETON AVE\",\n" +
                        "                \"line2\": null,\n" +
                        "                \"city\": \"DENVER\",\n" +
                        "                \"state\": \"CO\",\n" +
                        "                \"zipCode\": \"80237\",\n" +
                        "                \"zipCodeExtension\": null\n" +
                        "            },\n" +
                        "            \"phone1\": {\n" +
                        "                \"phone\": \"3608203984\",\n" +
                        "                \"type\": \"HOME\"\n" +
                        "            },\n" +
                        "            \"phone2\": {\n" +
                        "                \"phone\": \"3608203984\",\n" +
                        "                \"type\": \"HOME\"\n" +
                        "            },\n" +
                        "            \"emailAddress\": \"noemaila@dish.com\",\n" +
                        "            \"managementGroup\": null,\n" +
                        "            \"cycleCode\": 14,\n" +
                        "            \"geoCode\": \"060310140\",\n" +
                        "            \"billingType\": \"1\",\n" +
                        "            \"connectDate\": \"2016-04-14\",\n" +
                        "            \"pendingPayment\": 0.0,\n" +
                        "            \"currentBalance\": -252.61,\n" +
                        "            \"billingFromDate\": \"2016-04-14\",\n" +
                        "            \"billingToDate\": \"2016-05-13\",\n" +
                        "            \"primaryStatus\": \"ACTIVE\"\n" +
                        "        },\n" +
                        "        \"offeringList\": [\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           {\"id\": null,\"offeringId\": null,\"quantity\": 1},\n" +
                        "           ]\n" +
                        "    },\n" +
                        "    \"agentData\": {\n" +
                        "        \"agentOperatorId\": \"6BH\",\n" +
                        "        \"privilegesList\": null,\n" +
                        "        \"id\": \"carlos.loya@dish.com\",\n" +
                        "        \"agentRoles\": [\"Tech\"]\n" +
                        "    }\n" +
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