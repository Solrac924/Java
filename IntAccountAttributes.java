/***
 * Developer:   carlos.loya
 * Date:        2/19/2016
 * Modified:    4/15/2016
 * Purpose:     Tests the Beacon Tech REST service adapter in Integration
 * ad majorem Dei gloriam
 *
 ***/

package x64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.*;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.Header;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;

public class IntAccountAttributes
{
    String dbPartner = "";
    String dbAcctType = "";
    String dbBillingChan = "";
    String dbSubType = "";
    String dbZone = "";
    String respPartner = "";
    String respAcctType = "";
    String respBillingChan = "";
    String respSubType = "";
    String respZone = null;

    public IntAccountAttributes(String inAccount, String inTagCode) throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
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

        String endPoint = "https://account-attributes.integration.apps.int.dev.mer.cfdish.io/account/"
                + inAccount
                + "/attributes?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=test.test";
        HttpPost post = new HttpPost(endPoint);

        // set up JSON request
        StringEntity request = new StringEntity(
                "{"
                        + "  \"propertyId\":\"4563\""
                        + " ,\"serviceCodes\":[\"AA\",\"" + inTagCode + "\"]"
                        + "}");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        // show account
        System.out.println("\ninAccount: " + inAccount);

        // show response
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        String fullResponse = "";
        while ((line = rd.readLine()) != null)
        {
            fullResponse += line;
        }
        //System.out.println("\nresponse: " + fullResponse);

        StringTokenizer st = new StringTokenizer(fullResponse, "\"");
        String currToken = "";
        String scrap = "";

        while (st.hasMoreTokens() )
        {
            currToken = st.nextToken();

            if (currToken.contains("PARTNER") )
            {
                scrap = st.nextToken();
                respPartner = st.nextToken();
            }
            if (currToken.contains("ACCOUNT_TYPE") )
            {
                scrap = st.nextToken();
                respAcctType = st.nextToken();
            }
            if (currToken.contains("BILLING_CHANNEL") )
            {
                scrap = st.nextToken();
                respBillingChan = st.nextToken();
            }
            if (currToken.contains("ACCOUNT_SUB_TYPE") )
            {
                scrap = st.nextToken();
                respSubType = st.nextToken();
            }
            if (currToken.contains("ZONE") )
            {
                scrap = st.nextToken();
                if (scrap.contains("null") )
                {
                    respZone = null;
                }
                else
                {
                    respZone = st.nextToken();
                }
            }

        } // end while

        // get values from SQL query
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // DIA connection
            String urlSUB = "jdbc:oracle:thin:@tbsubdb:1540:TBSUB";
            Connection conSUB = DriverManager.getConnection(urlSUB, "TQA_USER", "tqa4d1sh");
            Statement stmtSUB = conSUB.createStatement();

            // DIA query - get open New Connect
            String sqlSUB = "";
            if (inTagCode.isEmpty() )
            {
                sqlSUB = "select SUB_ACCT_SPA_SVC_CODE_SID\n" +
                        "       ,concat(SYS_PRIN_AGENT_TRIM,'1234567')\n" +
                        "       ,SYS_PRIN_AGENT_TRIM\n" +
                        "       ,ACCOUNT_TYPE\n" +
                        "       ,ZONE\n" +
                        "       ,ACCOUNT_SUB_TYPE\n" +
                        "       ,PARTNER\n" +
                        "       ,SVC_CODE_ID\n" +
                        "       ,WHOLESALE_TYPE \n" +
                        "from SUB_ACCT_SPA_SVC_CODE\n" +
                        "where SYS_PRIN_AGENT_TRIM = '" + inAccount.substring(0,9) + "'\n" +
                        "and SVC_CODE_ID is null";
            }
            else
            {
                sqlSUB = "select SUB_ACCT_SPA_SVC_CODE_SID\n" +
                        "       ,concat(SYS_PRIN_AGENT_TRIM,'1234567')\n" +
                        "       ,SYS_PRIN_AGENT_TRIM\n" +
                        "       ,ACCOUNT_TYPE\n" +
                        "       ,ZONE\n" +
                        "       ,ACCOUNT_SUB_TYPE\n" +
                        "       ,PARTNER\n" +
                        "       ,SVC_CODE_ID\n" +
                        "       ,WHOLESALE_TYPE \n" +
                        "from SUB_ACCT_SPA_SVC_CODE\n" +
                        "where SYS_PRIN_AGENT_TRIM = '" + inAccount.substring(0,9) + "'\n" +
                        "and SVC_CODE_ID = '" + inTagCode + "'";
            }

            String pad = "%-32s %s\n";
            ResultSet rs = stmtSUB.executeQuery(sqlSUB);
            if (rs.next())
            {
                // store database values
                dbPartner = rs.getString("PARTNER");
                dbAcctType = rs.getString("ACCOUNT_TYPE");
                dbSubType = rs.getString("ACCOUNT_SUB_TYPE");
                dbZone = rs.getString("ZONE");

                System.out.format(pad, "respPartner : " + respPartner, "dbPartner = " + dbPartner);
                System.out.format(pad, "respAcctType : " + respAcctType, "dbAcctType = " + dbAcctType);
                System.out.format(pad, "respSubType : " + respSubType, "dbSubType = " + dbSubType);
                System.out.format(pad, "respZone : " + respZone, "dbAcctType = " + dbZone);
                System.out.format(pad,"respBillingChan : " + respBillingChan, "dbBillingChan = " + dbBillingChan);
                System.out.println("----------------------------------------------------------");
            }

            rs.close();
            stmtSUB.close();
            conSUB.close();

        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;
        }
        catch (SQLException e)
        {
            System.out.println("You have a bad SQL code!");
            e.printStackTrace();
            return;
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Error: Not a integer!");
        }
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // show the service adapter version
        System.out.println("version: " + showVersion() );
        System.out.println();

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

        HttpPost post = new HttpPost("https://account-attributes.integration.apps.int.dev.mer.cfdish.io/account/8046110001234567/attributes?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=test.test");

        // set up JSON request
        StringEntity request = new StringEntity(
                "{"
                        + "  \"propertyId\":\"4563\""
                        + " ,\"serviceCodes\":[\"AA\",\"IL\"]"
                        + "}");
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
            System.out.println(line);
            fullResponse += line;
        }
        System.out.println("fullResponse (string): " + fullResponse);

        // extracting individual response values
        getValues(fullResponse);

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
        HttpPost post = new HttpPost("https://account-attributes.integration.apps.int.dev.mer.cfdish.io/account/8046110001234567/attributes?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=test.test");

        // set up JSON request
        StringEntity request = new StringEntity(
                "{"
                        + "\"propertyId\":\"4563\""
                        + ",\"serviceCodes\":[\"AA\",\"IL\"]"
                        + "}");
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        // show Status Code
        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );

    } // showStatusCode

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

        // get Status Code
        HttpPost post = new HttpPost("https://account-attributes.integration.apps.int.dev.mer.cfdish.io/account/8046110001234567/attributes?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=test.test");
        StringEntity request = new StringEntity("{"
                + "\"propertyId\":\"4563\""
                +",\"serviceCodes\":[\"AA\",\"IL\"]"
                +"}");
        request.setContentType("application/json");
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        return response.getStatusLine().getStatusCode();
    } // getStatusCode

    static void getValues(String fullResponse) throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,ArrayIndexOutOfBoundsException
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

        // get Status Code
        HttpPost post = new HttpPost("https://account-attributes.integration.apps.int.dev.mer.cfdish.io/account/8046110001234567/attributes?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=test.test");
        StringEntity request = new StringEntity(
                "{"
                        + "  \"propertyId\":\"4563\""
                        + " ,\"serviceCodes\":[\"AA\",\"IL\"]"
                        + "}");
        request.setContentType("application/json");
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        StringTokenizer st = new StringTokenizer(fullResponse, "\"");
        String currToken = "";
        String partnerPL = "";
        String acctTypePL = "";
        String billingChanPL = "";
        String subTypePL = "";
        String zonePL = "";
        String scrap = "";
        while (st.hasMoreTokens() )
        {
            currToken = st.nextToken();

            if (currToken.contains("PARTNER") )
            {
                partnerPL = st.nextToken();
                partnerPL = st.nextToken();
            }
            if (currToken.contains("ACCOUNT_TYPE") )
            {
                acctTypePL = st.nextToken();
                acctTypePL = st.nextToken();
            }
            if (currToken.contains("BILLING_CHANNEL") )
            {
                billingChanPL = st.nextToken();
                billingChanPL = st.nextToken();
            }
            if (currToken.contains("ACCOUNT_SUB_TYPE") )
            {
                subTypePL = st.nextToken();
                subTypePL = st.nextToken();
            }
            if (currToken.contains("ZONE") )
            {
                scrap = st.nextToken();
                if (scrap.contains("null") )
                {
                    zonePL = null;
                }
                else
                {
                    zonePL = st.nextToken();
                }
            }
        } // while
        System.out.println("partner: " + partnerPL);
        System.out.println("acctType: " + acctTypePL);
        System.out.println("billingChan: " + billingChanPL);
        System.out.println("subType: " + subTypePL);
        System.out.println("zone: " + zonePL);



    } // getValues

    static String showVersion() throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    // (HttpResponse response)
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

        HttpPost post = new HttpPost("https://account-attributes.integration.apps.int.dev.mer.cfdish.io/account/8046110001234567/attributes?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=test.test");

        // set up JSON request
        StringEntity request = new StringEntity(
                "  {"
                        + "  \"propertyId\":\"4563\""
                        + " ,\"serviceCodes\":[\"AA\",\"IL\"]"
                        + "}");
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