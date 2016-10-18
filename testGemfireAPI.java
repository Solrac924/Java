/***
 * Developer:   carlos.loya
 * Date:        2/22/2016
 * Purpose:     Tests the Beacon Tech REST service adapter
 * Note:        this class overrides Self-Signed Certificate
 * ad majorem Dei gloriam
 ***/

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

public class testGemfireAPI
{
    public static void main(String[] args) throws ClientProtocolException, IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // set up non-secure HTTP client
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://tm1-epc-gf-srv101.a.dish.com:8080/gemfire-api/v1/queries/adhoc?q=select%20*%20from%20%2FOffering");

        // send REST request
        HttpResponse response = client.execute(request);

        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );

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

    } // end main

    static void showStatusCode() throws ClientProtocolException, IOException
    {
        // get REST response
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://tm1-epc-gf-srv101.a.dish.com:8080/gemfire-api/v1/queries/adhoc?q=select%20*%20from%20%2FOffering");
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
        HttpGet request = new HttpGet("http://tm1-epc-gf-srv101.a.dish.com:8080/gemfire-api/v1/queries/adhoc?q=select%20*%20from%20%2FOffering");
        HttpResponse response = client.execute(request);

        return response.getStatusLine().getStatusCode();
    }

    static String showVersion() throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // set up non-secure HTTP client
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://tm1-epc-gf-srv101.a.dish.com:8080/gemfire-api/v1/queries/adhoc?q=select%20*%20from%20%2FOffering");

        // send REST request
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