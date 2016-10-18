/**
 * Developer:   carlos.loya
 * Date:        2/16/2016
 * Purpose:     POSTs a GET request
 * AMDG
 *
 * http://www.java2s.com/Code/Jar/a/Downloadapachehttpcomponentshttpcorejar.htm
 **/

package x64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
// import org.apache.commons.httpclient.contrib.ssl.EasySSLProtocolSocketFactory;

public class HTTPGet
{
    public static void main(String[] args) throws ClientProtocolException, IOException
    {
        // override SSLPeerUnverifiedException
        //Protocol easyHttps = new Protocol("https", new EasySSLProtocolSocketFactory(), 443);
        //Protocol.registerProtocol("https", easyHttps);

        // get REST response
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://agent-service.test.apps.int.test.mer.cfdish.io/agent/DAVID.EASTRIDGE?sessionId=1b89b701-067a-49a0-92d0-944b473403dc");
        HttpResponse response = client.execute(request);

        // show response
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null)
        {
            System.out.println(line);
        }
    } // end main

} // end class