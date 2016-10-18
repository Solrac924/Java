/**
 * Developer:       Carlos A Loya
 * Date created:    2/22/2016
 * Purpose:         Tests the Beacon Tech REST service adapter
 * Note:            this class overrides Self-Signed Certificate
 * ad majorem Dei gloriam
 *
 **/

package x64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
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

public class testOrderService
{
    static String respOrderId = "";
    static String respAccountId = "";
    static String respType = "";
    static String respStatus = "";
    static String respScheduleDate = "";

    static String dbOrderId = "";
    static String dbAccountId = "";
    static String dbType = "";
    static String dbStatus = "";
    static String dbScheduleDate = "";

    public testOrderService(String inWO) throws IOException,NoSuchAlgorithmException,KeyStoreException,KeyManagementException,SQLException,ClassNotFoundException,NumberFormatException
    {
        // override Self-Signed Certificate
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustStrategy()
        {
            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        } );
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        // set up endpoint
        HttpGet request = new HttpGet("https://order-service.test.apps.internal-green.test.mer.cfdish.io:443/order/" +
                inWO + "?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

        // send REST request
        HttpResponse response = client.execute(request);

        // show response
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        String fullResponse = "";
        while ((line = rd.readLine() ) != null)
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

            if (currToken.contains("orderId") )
            {
                scrap = st.nextToken();
                respOrderId = st.nextToken();
            }
            if (currToken.contains("accountId") )
            {
                scrap = st.nextToken();
                respAccountId = st.nextToken();
            }
            if (currToken.contains("type") )
            {
                scrap = st.nextToken();
                respType = st.nextToken();
            }
            if (currToken.contains("status") )
            {
                scrap = st.nextToken();
                respStatus = st.nextToken();
            }
            if (currToken.contains("scheduleDate") )
            {
                scrap = st.nextToken();
                respScheduleDate = st.nextToken();
            }

        } // while

        Class.forName("oracle.jdbc.driver.OracleDriver");

        // DIA connection
        String urlDIA = "jdbc:oracle:thin:@tbdiadb:1534:TBDIA";
        Connection conDIA = DriverManager.getConnection(urlDIA, "TQA_USER", "tqa4d1sh");
        Statement stmtDIA = conDIA.createStatement();

        // SUB query - get open New Connect
        String sqlDIA =
                "select eord.ORDER_ID\n" +
                "       ,eord.ACCOUNT_ID\n" +
                "       ,JOB_TYPE_DESCR\n" +
                "       ,ORDER_STATUS\n" +
                "       ,TO_CHAR(JOB_SCHEDULE_DT, 'YYYY-MM-DD') as \"date\"\n" +
                "from ENI_ORDER eord\n" +
                "    ,ENI_JOB ejob\n" +
                "where eord.ORDER_ID = ejob.ORDER_ID\n" +
                "and eord.ORDER_ID = '" + inWO + "'";

        ResultSet rs = stmtDIA.executeQuery(sqlDIA);
        if (rs.next() )
        {
            // store database values
            dbOrderId = rs.getString("ORDER_ID");
            dbAccountId = rs.getString("ACCOUNT_ID");
            dbType = rs.getString("JOB_TYPE_DESCR");
            dbStatus = rs.getString("ORDER_STATUS");
            dbScheduleDate = rs.getString("date");
        }

        String pad = "%-48s %s\n";
        System.out.format(pad, "respOrderId : " + respOrderId, "dbOrderId = " + dbOrderId);
        System.out.format(pad, "respAccountId : " + respAccountId, "dbAccountId = " + dbAccountId);
        System.out.format(pad, "respType : " + respType, "dbType = " + dbType);
        System.out.format(pad, "respStatus : " + respStatus, "dbStatus = " + dbStatus);
        System.out.format(pad, "respScheduleDate : " + respScheduleDate, "dbScheduleDate = " + dbScheduleDate);
        System.out.println("----------------------------------------------------------------------------------------");

    } // testOrderService

    public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException, ClassNotFoundException, SQLException
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
        SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

        // set up endpoint
        HttpGet request = new HttpGet("https://order-service.test.apps.internal-green.test.mer.cfdish.io:443/order/1000021131723011?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

        // send REST request
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

        System.out.println("getOpenNC: " + getOpenWOs.getOpenNC() );
        System.out.println("getOpenTC: " + getOpenWOs.getOpenTC() );
        System.out.println("getOpenSC: " + getOpenWOs.getOpenSC() );

    } // end main

    static void showStatusCode() throws ClientProtocolException, IOException
    {
        // override Self-Signed Certificate
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://order-service.test.apps.internal-green.test.mer.cfdish.io:443/order/1000021131723011?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");
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

        // set up endpoint
        HttpGet request = new HttpGet("https://order-service.test.apps.internal-green.test.mer.cfdish.io:443/order/1000021131723011?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");

        // send REST request
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
        HttpGet request = new HttpGet("https://order-service.test.apps.internal-green.test.mer.cfdish.io:443/order/1000021131723011?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=carlos.loya");
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
