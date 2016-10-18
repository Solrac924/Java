/**
 * Developer:   carlos.loya
 * Date:        2/17/2016
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class HTTPPost
{
    public static void main(String[] args) throws ClientProtocolException, IOException
    {
        // set up endpoint
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://account-attributes.test.apps.int.test.mer.cfdish.io/account/8046110001234567/attributes?sessionId=1b89b701-067a-49a0-92d0-944b473403dc&agentId=test.test");

        // set up JSON request
        StringEntity request = new StringEntity("{\"propertyId\":\"4563\",\"serviceCodes\":[\"AA\",\"IL\"]}");
        System.out.println("request: " + request.toString() );
        request.setContentType("application/json");

        // post request
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        // show response
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null)
        {
            System.out.println(line);
        }
    }
}