/**
 * Developer:   carlos.loya
 * Date:        2/22/2016
 * Purpose:     Tests the Beacon Tech REST service adapter
 * Note:        this class overrides Self-Signed Certificate
 * ad majorem Dei gloriam
 *
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
import java.util.StringTokenizer;

public class IntAccountService
{
    static String dbAccountId = "";
    static String dbCustomerId = "";
    static String dbPrimaryStatus = "";
    static String dbEmail = "";
    String dbCard_payment_allowed = "";
    String dbEFT_payment_allowed = "";

    static String respAccountId = "";
    static String respCustomerId = "";
    static String respPrimaryStatus = "";
    static String respEmailAddr = "";
    static String respCard_payment_allowed = "";
    static String respEFT_payment_allowed = "";

    public IntAccountService(String inAccount) throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,NumberFormatException
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
        HttpGet request = new HttpGet("https://account-service.integration.apps.int.dev.mer.cfdish.io:443/account/"
                + inAccount
                + "?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

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

        while (st.hasMoreTokens() )
        {
            currToken = st.nextToken();

            if (currToken.contains("accountId") )
            {
                scrap = st.nextToken();
                respAccountId = st.nextToken();
            }
            if (currToken.contains("customerId") )
            {
                scrap = st.nextToken();
                respCustomerId = st.nextToken();
            }
            if (currToken.contains("primaryStatus") )
            {
                scrap = st.nextToken();
                respPrimaryStatus = st.nextToken();
            }
            if (currToken.contains("emailAddress") )
            {
                scrap = st.nextToken();
                respEmailAddr= st.nextToken();
            }
            if (currToken.contains("CARD_PAYMENT_ALLOWED") )
            {
                scrap = st.nextToken();
                respCard_payment_allowed = st.nextToken();
            }
            if (currToken.contains("EFT_PAYMENT_ALLOWED") )
            {
                scrap = st.nextToken();
                respEFT_payment_allowed = st.nextToken();
            }
        } // while

        Class.forName("oracle.jdbc.driver.OracleDriver");

        // SUB connection
        String urlSUB = "jdbc:oracle:thin:@tbsubdb:1540:TBSUB";
        Connection conSUB = DriverManager.getConnection(urlSUB, "TQA_USER", "tqa4d1sh");
        Statement stmtSUB = conSUB.createStatement();

        // SUB query - get open New Connect
        String sqlSUB = "select distinct acct.ACCOUNT_ID as \"accountId\"\n" +
                "       ,sbb_base.CUST_ACCT_NO_SBB as \"customerId\"\n" +
                "       ,cust.FIRST_NAME as \"givenName\"\n" +
                "       ,LAST_NAME as \"familyName\"\n" +
                "       ,addr.LINE_1 as \"line1\"\n" +
                "       ,addr.LINE_2 as \"line2\"\n" +
                "       ,addr.CITY as \"city\"\n" +
                "       ,addr.STATE as \"state\"\n" +
                "       ,substr(addr.POSTAL_CODE,1,5) as \"zipCode\"\n" +
                "       ,substr(addr.POSTAL_CODE,7,4) as \"zipCodeExtension\"\n" +
                "       ,cust_base.PHONE_NO1_CUS as \"phone1\"\n" +
                "       ,cust_base.PHONE_NO2_CUS as \"phone2\"\n" +
                "       ,email.EMAIL_ADDRESS as \"emailAddress\"\n" +
                "       ,cust_base.E_MAIL_ADDR_CUS as \"orig emailAddress\"\n" +
                "       ,ACCOUNT_PRIMARY_STATUS as \"primaryStatus\"\n" +
                "       ,spa.ACCOUNT_TYPE as \"Account_Type\"\n" +
                "       --,ordVW.PROVIDER_NAME as \"Billing_Channel\"\n" +
                "       ,spa.ZONE as \"Zone\"\n" +
                "       ,spa.ACCOUNT_SUB_TYPE as \"Account_Sub_Type\"\n" +
                "       ,spa.PARTNER as \"Partner\"\n" +
                "from SUB_ACCOUNT acct\n" +
                "     ,SUB_CUSTOMER cust\n" +
                "     ,SUB_CUSTOMER_ADDRESS custAddr\n" +
                "     ,SUB_ADDRESS addr\n" +
                "     ,VANTAGE.VTG$SBB_BASE sbb_base\n" +
                "     ,VANTAGE.VTG$CUS_BASE cust_base\n" +
                "     ,SUB_ACCOUNT_EMAIL email\n" +
                "     ,SUB_ACCOUNT_TYPE_SPA spa\n" +
                "where acct.ACCOUNT_ID = '" +
                inAccount + "'\n" +
                "and acct.CUSTOMER_ID = cust.CUSTOMER_ID\n" +
                "and cust.CUSTOMER_ID = custAddr.CUSTOMER_ID\n" +
                "and custAddr.ADDRESS_ID = addr.ADDRESS_ID\n" +
                "and acct.ACCOUNT_ID = sbb_base.SUB_ACCT_NO_SBB\n" +
                "and sbb_base.CUST_ACCT_NO_SBB = cust_base.CUST_ACCT_NO_CUS\n" +
                "and acct.ACCOUNT_ID = email.ACCOUNT_ID\n" +
                "and acct.ACCOUNT_TYPE_SPA_ID = spa.ACCOUNT_TYPE_SPA_ID\n" +
                "and ADDRESS_TYPE = 'PHYSICAL (CSG)'";

        ResultSet rs = stmtSUB.executeQuery(sqlSUB);
        if (rs.next() )
        {
            // store database values
            dbAccountId = rs.getString("accountId");
            dbCustomerId = rs.getString("customerId");
            dbPrimaryStatus = rs.getString("primaryStatus");
            dbEmail = rs.getString("emailAddress");
            //dbCard_payment_allowed = rs.getString("ZONE");
            //dbEFT_payment_allowed = rs.getString("ZONE");
        }

        String pad = "%-48s %s\n";
        System.out.format(pad, "respAccountId : " + respAccountId, "dbAccountId = " + dbAccountId);
        System.out.format(pad, "respCustomerId : " + respCustomerId, "dbCustomerId = " + dbCustomerId);
        System.out.format(pad, "respPrimaryStatus : " + respPrimaryStatus, "dbPrimaryStatus = " + dbPrimaryStatus);
        System.out.format(pad, "respEmailAddr : " + respEmailAddr, "dbEmail = " + dbEmail);
        System.out.println("respCard_payment_allowed: " + respCard_payment_allowed );
        System.out.println("respEFT_payment_allowed: " + respEFT_payment_allowed );
        System.out.println("----------------------------------------------------------------------------------------");

        // close connection
        rs.close();
        stmtSUB.close();
        conSUB.close();
    } // constructor

    public static void main(String[] args) throws ClientProtocolException, IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
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
        HttpGet request = new HttpGet("https://account-service.integration.apps.int.dev.mer.cfdish.io:443/account/8046909802572017?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

        HttpResponse response = client.execute(request);

        // show response
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        String fullResponse = "";
        while ((line = rd.readLine()) != null)
        {
            System.out.println(line);
            fullResponse += line;
        }

        // show status code
        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );

        // show the service adapter version
        System.out.println("version: " + showVersion() );

        // show response values by key
        StringTokenizer st = new StringTokenizer(fullResponse, "\"");
        String currToken = "";
        String scrap = "";

        while (st.hasMoreTokens() )
        {
            currToken = st.nextToken();

            if (currToken.contains("accountId"))
            {
                scrap = st.nextToken();
                respAccountId = st.nextToken();
            }
            if (currToken.contains("customerId"))
            {
                scrap = st.nextToken();
                respCustomerId = st.nextToken();
            }
            if (currToken.contains("primaryStatus"))
            {
                scrap = st.nextToken();
                respPrimaryStatus = st.nextToken();
            }
            if (currToken.contains("emailAddress") )
            {
                scrap = st.nextToken();
                respEmailAddr= st.nextToken();
            }
            if (currToken.contains("CARD_PAYMENT_ALLOWED"))
            {
                scrap = st.nextToken();
                respCard_payment_allowed = st.nextToken();
            }
            if (currToken.contains("EFT_PAYMENT_ALLOWED"))
            {
                scrap = st.nextToken();
                respEFT_payment_allowed = st.nextToken();
            }
        } // while
        System.out.println("respAccountId: " + respAccountId );
        System.out.println("respCustomerId: " + respCustomerId );
        System.out.println("respPrimaryStatus: " + respPrimaryStatus );
        System.out.println("respEmailAddr: " + respEmailAddr );
        System.out.println("respCard_payment_allowed: " + respCard_payment_allowed );
        System.out.println("respEFT_payment_allowed: " + respEFT_payment_allowed );

        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // DIA connection
            String urlSUB = "jdbc:oracle:thin:@tbsubdb:1540:TBSUB";
            Connection conSUB = DriverManager.getConnection(urlSUB, "TQA_USER", "tqa4d1sh");
            Statement stmtSUB = conSUB.createStatement();

            // DIA query - get open New Connect
            String sqlSUB = "select distinct acct.ACCOUNT_ID as \"accountId\"\n" +
                    "       ,sbb_base.CUST_ACCT_NO_SBB as \"customerId\"\n" +
                    "       ,cust.FIRST_NAME as \"givenName\"\n" +
                    "       ,LAST_NAME as \"familyName\"\n" +
                    "       ,addr.LINE_1 as \"line1\"\n" +
                    "       ,addr.LINE_2 as \"line2\"\n" +
                    "       ,addr.CITY as \"city\"\n" +
                    "       ,addr.STATE as \"state\"\n" +
                    "       ,substr(addr.POSTAL_CODE,1,5) as \"zipCode\"\n" +
                    "       ,substr(addr.POSTAL_CODE,7,4) as \"zipCodeExtension\"\n" +
                    "       ,cust_base.PHONE_NO1_CUS as \"phone1\"\n" +
                    "       ,cust_base.PHONE_NO2_CUS as \"phone2\"\n" +
                    "       ,email.EMAIL_ADDRESS as \"emailAddress\"\n" +
                    "       ,cust_base.E_MAIL_ADDR_CUS as \"orig emailAddress\"\n" +
                    "       ,ACCOUNT_PRIMARY_STATUS as \"primaryStatus\"\n" +
                    "       ,spa.ACCOUNT_TYPE as \"Account_Type\"\n" +
                    "       --,ordVW.PROVIDER_NAME as \"Billing_Channel\"\n" +
                    "       ,spa.ZONE as \"Zone\"\n" +
                    "       ,spa.ACCOUNT_SUB_TYPE as \"Account_Sub_Type\"\n" +
                    "       ,spa.PARTNER as \"Partner\"\n" +
                    "from SUB_ACCOUNT acct\n" +
                    "     ,SUB_CUSTOMER cust\n" +
                    "     ,SUB_CUSTOMER_ADDRESS custAddr\n" +
                    "     ,SUB_ADDRESS addr\n" +
                    "     ,VANTAGE.VTG$SBB_BASE sbb_base\n" +
                    "     ,VANTAGE.VTG$CUS_BASE cust_base\n" +
                    "     ,SUB_ACCOUNT_EMAIL email\n" +
                    "     ,SUB_ACCOUNT_TYPE_SPA spa\n" +
                    "where acct.ACCOUNT_ID = '8046909078203057'\n" +
                    "and acct.CUSTOMER_ID = cust.CUSTOMER_ID\n" +
                    "and cust.CUSTOMER_ID = custAddr.CUSTOMER_ID\n" +
                    "and custAddr.ADDRESS_ID = addr.ADDRESS_ID\n" +
                    "and acct.ACCOUNT_ID = sbb_base.SUB_ACCT_NO_SBB\n" +
                    "and sbb_base.CUST_ACCT_NO_SBB = cust_base.CUST_ACCT_NO_CUS\n" +
                    "and acct.ACCOUNT_ID = email.ACCOUNT_ID\n" +
                    "and acct.ACCOUNT_TYPE_SPA_ID = spa.ACCOUNT_TYPE_SPA_ID\n" +
                    "and ADDRESS_TYPE = 'PHYSICAL (CSG)'";

            ResultSet rs = stmtSUB.executeQuery(sqlSUB);
            if (rs.next() )
            {
                // store database values
                dbAccountId = rs.getString("PARTNER");
                dbCustomerId = rs.getString("ACCOUNT_TYPE");
                dbPrimaryStatus = rs.getString("ACCOUNT_SUB_TYPE");
                dbEmail = rs.getString("emailAddress");
                //dbZone = rs.getString("ZONE");

                System.out.println("dbAccountId = " + dbAccountId);
                System.out.println("dbCustomerId = " + dbCustomerId);
                System.out.println("dbPrimaryStatus = " + dbPrimaryStatus);
                System.out.println("dbEmail = " + dbEmail);
                //System.out.println("dbSubType = " + dbSubType);
                //System.out.println("dbZone = " + dbZone);

                System.out.println("--------------------------------------");

            }

            rs.close();
            stmtSUB.close();
            conSUB.close();
        } // try

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
    } // end main

    static void showStatusCode() throws ClientProtocolException, IOException
    {
        // get REST response
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://account-service.integration.apps.int.dev.mer.cfdish.io:443/account/8046909802572017?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");
        HttpResponse response = client.execute(request);

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

        // set up endpoint
        //HttpClient client = new DefaultHttpClient();

        // get REST response
        HttpGet request = new HttpGet("https://account-service.integration.apps.int.dev.mer.cfdish.io:443/account/8046909808774070?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");
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
        HttpGet request = new HttpGet("https://account-service.integration.apps.int.dev.mer.cfdish.io:443/account/8046909802572017?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");
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

} // end class