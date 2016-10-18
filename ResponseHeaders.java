package x64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ResponseHeaders
{
    public static void main(String[] args)
    {
        try
        {
            // override Self-Signed Certificate
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustStrategy()
            {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException
                {
                    return true;
                }
            });
            SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSF).build();

            HttpGet request = new HttpGet("https://agent-service.test.apps.int.test.mer.cfdish.io:443/agent/DAVID.EASTRIDGE?sessionId=1b89b701-067a-49a0-92d0-944b473403dc");
            HttpResponse response = client.execute(request);

            System.out.println("Printing Response Header...\n");

            Header[] headers = response.getAllHeaders();

            for (Header header : headers)
            {
                System.out.println("Key : " + header.getName() + " ,Value : " + header.getValue());
            }

            System.out.println("\nGet Response Header By Key ...");
            String server = response.getFirstHeader("Server").getValue();

            if (server == null)
            {
                System.out.println("Key 'Server' is not found!");
            }
            else
            {
                System.out.println("\nServer - " + server);
            }

            System.out.println("\nDone");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    } // end main
} // class: ResponseHeaders