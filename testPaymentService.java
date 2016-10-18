/**
 * Developer:   carlos.loya
 * Date:        2/19/2016
 * Purpose:     Tests the Beacon Tech REST service adapter
 * Note:        this class overrides Self-Signed Certificate
 * ad majorem Dei gloriam
 *
 * http://www.java2s.com/Code/Jar/a/Downloadapachehttpcomponentshttpcorejar.htm
 **/

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
import java.sql.*;
import java.util.Random;
import java.util.StringTokenizer;

public class testPaymentService
{
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException,ClassNotFoundException,SQLException
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
        HttpGet request = new HttpGet("https://payment-service.test.apps.internal-green.test.mer.cfdish.io:443/payment/lastsuccessfulpayment/8046909802572017?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

        // get REST response
        HttpResponse response = client.execute(request);

        // show response
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null)
        {
            System.out.println(line);
        }

        // show status code
        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );

        // show the service adapter version
        System.out.println("version: " + showVersion() );

        System.out.println("\npostCCPymt: " + postCCPymt("4005550000000019", "12", "2020", 100) );
        System.out.println("postCCPymt: " + postCCPymt("5442981111111114", "11", "2019", 100) );
        System.out.println("postCCPymt: " + postCCPymt("6011000991300009", "10", "2018", 100) );
        System.out.println("postCCPymt: " + postCCPymt("373235387881007", "09", "2017", 100) );

        System.out.println("postEFTPymt: " + postEFTPymt("8046909529861370", "5425512449", "102000076") );
        System.out.println("postEFTPymt: " + postEFTPymt("8046909455439555", "817741234",  "322271627") );
        System.out.println("postEFTPymt: " + postEFTPymt("8046919110629714", "0012345689", "302070149") );
        System.out.println("postEFTPymt: " + postEFTPymt("8046909789194447", "664872121",  "071000013") );

        System.out.println("lookupLastSuccessful: " + lookupLastSuccessful("8046909529861370") );
        // testPaymentService test07 = new testPaymentService.lookupLastSuccessful("8046909529861370");

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

        // send REST request
        HttpGet request = new HttpGet("https://payment-service.test.apps.internal-green.test.mer.cfdish.io:443/payment/lastsuccessfulpayment/8046909802572017?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

        // get REST response
        HttpResponse response = client.execute(request);

        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );
    }

    static int getStatusCode() throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
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
        HttpGet request = new HttpGet("https://payment-service.test.apps.internal-green.test.mer.cfdish.io:443/payment/lastsuccessfulpayment/8046909802572017?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

        // get REST response
        HttpResponse response = client.execute(request);

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

        // send REST request
        HttpGet request = new HttpGet("https://payment-service.test.apps.internal-green.test.mer.cfdish.io:443/payment/lastsuccessfulpayment/8046909802572017?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");
        HttpResponse response = client.execute(request);

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

    static int postCCPymt(String inCard, String inMonth,String inYear, Integer inAmount) throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
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
        String endPoint = "https://payment-service.test.apps.internal-green.test.mer.cfdish.io:443/payment/card?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=JASON.BODY";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                "{\n" +
                "    \"accountId\": \"8046909807658498\",\n" +
                "    \"paymentAmount\": " + inAmount + ",\n" +
                "    \"paymentNote\": \"TEST\",\n" +
                "    \"paymentReason\": \"TEST\",\n" +
                "    \"paymentSource\": \"WEB\",\n" +
                "    \"forceDuplicate\": true,\n" +
                "    \"cardDetails\": {\n" +
                "        \"billingAddress\": {\n" +
                "            \"line1\": \"9601 S MERIDIAN BLVD\",\n" +
                "            \"line2\": \"\",\n" +
                "            \"city\": \"ENGLEWOOD\",\n" +
                "            \"state\": \"CO\",\n" +
                "            \"zipCode\": \"80112\",\n" +
                "            \"zipPlus4\": \"\"\n" +
                "            },\n" +
                "        \"paymentCardBrand\": \"VISA\",\n" +
                "        \"paymentCardType\": \"CREDIT\",\n" +
                "        \"expirationDay\": 30,\n" +
                "        \"expirationMonth\": \"" + inMonth + "\",\n" +
                "        \"expirationYear\": \"" + inYear + "\",\n" +
                "        \"nameOnCard\": \"TEST,TEST\",\n" +
                "        \"cardNumber\": \"" + inCard + "\",\n" +
                "        \"cardSecurityCode\": \"\"\n" +
                "        }\n" +
                "    }");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        System.out.println("\ninCard: " + inCard );
        System.out.println("inMonth: " + inMonth );
        System.out.println("inYear: " + inYear );
        System.out.println("inAmount: " + inAmount );
        System.out.println("statusCode(): " + response.getStatusLine().getStatusCode() );

        return response.getStatusLine().getStatusCode();
    }

    static int postEFTPymt(String inAcct, String inChkAcct, String inRouting) throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
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
        String endPoint = "https://payment-service.test.apps.internal-green.test.mer.cfdish.io:443/payment/eft?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=JASON%20BODY";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                "{\n" +
                "   \"accountId\": \"" + inAcct + "\",\n" +
                "   \"paymentAmount\": 100,\n" +
                "   \"paymentNote\": \"test\",\n" +
                "   \"paymentReason\": \"test\",\n" +
                "   \"paymentSource\": \"WEB\",\n" +
                "   \"forceDuplicate\": false,\n" +
                "   \"eftDetails\": {\n" +
                "      \"nameOnAccount\": \"TEST,TEST\",\n" +
                "      \"financialInstituteAccountId\": \"" + inChkAcct + "\",\n" +
                "      \"financialInstituteAccountRoutingNumber\": \"" + inRouting + "\",\n" +
                "      \"financialInstituteAccountType\": \"CHECKING\",\n" +
                "      \"financialInstituteCheckNumber\": \"1234\"\n" +
                "      }\n" +
                "   }");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        System.out.println("\ninAcct: " + inAcct );
        System.out.println("inChkAcct: " + inChkAcct );
        System.out.println("inRouting: " + inRouting );
        System.out.println("statusCode(): " + response.getStatusLine().getStatusCode() );

        return response.getStatusLine().getStatusCode();
    }

    static boolean lookupLastSuccessful(String inAcct) throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException,ClassNotFoundException,SQLException
    {
        // override Self-Signed Certificate
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        });
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        // send REST request
        HttpGet request = new HttpGet("https://payment-service.test.apps.internal-green.test.mer.cfdish.io:443/payment/lastsuccessfulpayment/" +
                inAcct + "?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

        HttpResponse response = client.execute(request);

        // show response
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        String fullResponse = "";
        while ((line = rd.readLine()) != null)
        {
            fullResponse += line;
        }

        // show response values by key
        StringTokenizer st = new StringTokenizer(fullResponse, "\"");
        String currToken = "";
        String scrap = "";
        String respPymtTransLogId = "";
        String respAmount = "";
        String respSource = "";
        String respPymtStatus = "";

        while (st.hasMoreTokens() )
        {
            currToken = st.nextToken();

            if (currToken.contains("paymentTransactionLogId") )
            {
                scrap = st.nextToken();
                respPymtTransLogId = st.nextToken();
            }
            if (currToken.contains("amount") )
            {
                respAmount = st.nextToken().substring(3);
            }
            if (currToken.contains("source") )
            {
                scrap = st.nextToken();
                respSource = st.nextToken();
            }
            if (currToken.contains("paymentStatus") )
            {
                scrap = st.nextToken();
                respPymtStatus = st.nextToken();
            }
        } // while
        System.out.println("\nrespPymtTransLogId: " + respPymtTransLogId);
        System.out.println("respAmount: " + respAmount);
        System.out.println("respSource: " + respSource);
        System.out.println("respPymtStatus: " + respPymtStatus);

        Class.forName("oracle.jdbc.driver.OracleDriver");

        // ECO connection
        String urlECO = "jdbc:oracle:thin:@tbecodb:1538:TBECO";
        Connection conECO = DriverManager.getConnection(urlECO, "TQA_USER", "tqa4d1sh");
        Statement stmtECO = conECO.createStatement();

        // ECO query - get open New Connect
        String sqlECO = "select SUBSCRIBER_ACCT_NO\n" +
                "       ,CREDIT_CARD_TRAN_LOG_ID\n" +
                "       ,TRANSACTION_AMT\n" +
                "       ,SOURCE\n" +
                "       ,TRANSACTION_STATUS\n" +
                "       ,CREATION_DATE\n" +
                "from (select *\n" +
                "      from EC_CREDIT_CARD_TRAN_LOG\n" +
                "      where SUBSCRIBER_ACCT_NO = '" + inAcct + "'\n" +
                "      and TRANSACTION_STATUS = 0\n" +
                "      order by CREATION_DATE desc)\n" +
                "where rownum <= 1";

        String dbPymtTransLogId = "";
        String dbAmount = "";
        String dbSource = "";
        String dbPymtStatus = "";

        ResultSet rs = stmtECO.executeQuery(sqlECO);
        if (rs.next() )
        {
            // store database values
            dbPymtTransLogId = rs.getString("CREDIT_CARD_TRAN_LOG_ID");
            dbAmount = rs.getString("TRANSACTION_AMT");
            dbSource = rs.getString("SOURCE");
            dbPymtStatus = rs.getString("TRANSACTION_STATUS");

            System.out.println("dbPymtTransLogId =  " + dbPymtTransLogId);
            System.out.println("dbAmount = " + dbAmount);
            System.out.println("dbSource = " + dbSource);
            System.out.println("dbPymtStatus = " + dbPymtStatus);
        }

        return (respPymtTransLogId.contains(dbPymtTransLogId) );
    } // constructor

} // end class