/**
 * Developer:   carlos.loya
 * Date:        3/01/2016
 * Purpose:     Tests the Beacon Tech REST service adapter
 * ad majorem Dei gloriam
 *
 * http://www.java2s.com/Code/Jar/a/Downloadapachehttpcomponentshttpcorejar.htm
 **/

package x64;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Random;
import java.util.StringTokenizer;

public class IntAgreementService
{
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException,KeyStoreException,KeyManagementException
    {
        // start timer
        Long startTime = System.currentTimeMillis();

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

        HttpPost post = new HttpPost("https://agreements-service.integration.apps.int.dev.mer.cfdish.io:443/agreement?sessionId=e1459039-6a0e-48d7-b%25E2%2580%258Bc23-9f39cee2f44c&agentId=test.test");

        // create random WO ID
        Random generator = new Random();
        long randOrdNum = generator.nextInt(1000000000);
        String randOrdID = "1000000" + randOrdNum;

        // set up JSON request
        StringEntity request = new StringEntity(
                "{\n" +
                        "  \"accountId\": \"8046909620369521\",\n" +
                        "  \"orderId\": \"" + randOrdID + "\",\n" +
                        "  \"agreementID\": \"01\",\n" +
                        "  \"firstName\": \"test\",\n" +
                        "  \"lastName\": \"user\",\n" +
                        "  \"emailAddress\": \"test@email.com\",\n" +
                        "  \"phoneNumber\": \"7203562314\",\n" +
                        "  \"signatureImage\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUoAAACWCAYAAAC4osHlAAABXklEQVR42u3awQnCQBAF0G3BsBVsEdqEHViMKSbFaDFagjiCVycrRBHzHvxbTnP4JOSXAvCbhsjFGQByu8jJGQByh8jkDAC5MXJ0BoDc9Hy7BCBxjmydASB3jWycAeA1syGADmZDAB3MhgA6jMVsCGCW2RBAB7MhgA6PP+GDMwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMCiaq23JZ4BWG1ZKkmApBCVJEBSjEoSIClLJQmgKAF8egN8tCSVJcAbhagsASVpcA4AAAAAAAAAAAAAAAAzWmt7EZF/iqIUEfl2UQIAAAAAAAAAAAAAsC53KYHVor9O7wsAAAAASUVORK5CYII=\",\n" +
                        "  \"totalAmount\": 12.33,\n" +
                        "  \"taxAmount\": 1.24,\n" +
                        "  \"paymentAmount\": 24.15,\n" +
                        "  \"transactionDate\": \"2015-12-29T16:37:59\",\n" +
                        "  \"clientAppName\": \"TECHSTOR\",\n" +
                        "  \"agentId\": \"test.test\",\n" +
                        "  \"oeNumber\": \"2342342\"  \n" +
                        "}");
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

        System.out.println("\ntime to catalog page: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");

        // extracting individual response values
        //System.out.println("\ngetting values...");
        //getValues(fullResponse);

        // show status code
        System.out.println("\nStatus: " + response.getStatusLine().getStatusCode() );

        // show the service adapter version
        System.out.println("version: " + showVersion() );

        System.out.println("\ntime for Agreement Service: " + (System.currentTimeMillis() - startTime)/1000.0 + "s");

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
        HttpPost post = new HttpPost("https://agreements-service.integration.apps.int.dev.mer.cfdish.io:443/agreement?sessionId=e1459039-6a0e-48d7-b%25E2%2580%258Bc23-9f39cee2f44c&agentId=test.test");

        // create random WO ID
        Random generator = new Random();
        long randOrdNum = generator.nextInt(1000000000);
        String randOrdID = "1000000" + randOrdNum;

        // set up JSON request
        StringEntity request = new StringEntity(
                "{\n" +
                        "  \"accountId\": \"8046909012345670\",\n" +
                        "  \"orderId\": \"" + randOrdID + "\",\n" +
                        "  \"agreementID\": \"01\",\n" +
                        "  \"firstName\": \"test\",\n" +
                        "  \"lastName\": \"user\",\n" +
                        "  \"emailAddresss\": \"test@email.com\",\n" +
                        "  \"phoneNumber\": \"7203562314\",\n" +
                        "  \"signatureImage\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUoAAACWCAYAAAC4osHlAAABXklEQVR42u3awQnCQBAF0G3BsBVsEdqEHViMKSbFaDFagjiCVycrRBHzHvxbTnP4JOSXAvCbhsjFGQByu8jJGQByh8jkDAC5MXJ0BoDc9Hy7BCBxjmydASB3jWycAeA1syGADmZDAB3MhgA6jMVsCGCW2RBAB7MhgA6PP+GDMwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMCiaq23JZ4BWG1ZKkmApBCVJEBSjEoSIClLJQmgKAF8egN8tCSVJcAbhagsASVpcA4AAAAAAAAAAAAAAAAzWmt7EZF/iqIUEfl2UQIAAAAAAAAAAAAAsC53KYHVor9O7wsAAAAASUVORK5CYII=\",\n" +
                        "  \"totalAmount\": 12.33,\n" +
                        "  \"taxAmount\": 1.24,\n" +
                        "  \"paymentAmount\": 24.15,\n" +
                        "  \"transactionDate\": \"2015-12-29T16:37:59\",\n" +
                        "  \"clientAppName\": \"TECHSTOR\",\n" +
                        "  \"agentId\": \"test.test\",\n" +
                        "  \"oeNumber\": \"2342342\"  \n" +
                        "}");
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

        // set up endpoint
        HttpPost post = new HttpPost("https://agreements-service.integration.apps.int.dev.mer.cfdish.io:443/agreement?sessionId=e1459039-6a0e-48d7-b%25E2%2580%258Bc23-9f39cee2f44c&agentId=test.test");

        // create random WO ID
        Random generator = new Random();
        long randOrdNum = generator.nextInt(1000000000);
        String randOrdID = "1000000" + randOrdNum;

        // set up JSON request
        StringEntity request = new StringEntity(
                "{\n" +
                        "  \"accountId\": \"8046909012345670\",\n" +
                        "  \"orderId\": \"" + randOrdID + "\",\n" +
                        "  \"agreementID\": \"01\",\n" +
                        "  \"firstName\": \"test\",\n" +
                        "  \"lastName\": \"user\",\n" +
                        "  \"emailAddresss\": \"test@email.com\",\n" +
                        "  \"phoneNumber\": \"7203562314\",\n" +
                        "  \"signatureImage\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUoAAACWCAYAAAC4osHlAAABXklEQVR42u3awQnCQBAF0G3BsBVsEdqEHViMKSbFaDFagjiCVycrRBHzHvxbTnP4JOSXAvCbhsjFGQByu8jJGQByh8jkDAC5MXJ0BoDc9Hy7BCBxjmydASB3jWycAeA1syGADmZDAB3MhgA6jMVsCGCW2RBAB7MhgA6PP+GDMwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMCiaq23JZ4BWG1ZKkmApBCVJEBSjEoSIClLJQmgKAF8egN8tCSVJcAbhagsASVpcA4AAAAAAAAAAAAAAAAzWmt7EZF/iqIUEfl2UQIAAAAAAAAAAAAAsC53KYHVor9O7wsAAAAASUVORK5CYII=\",\n" +
                        "  \"totalAmount\": 12.33,\n" +
                        "  \"taxAmount\": 1.24,\n" +
                        "  \"paymentAmount\": 24.15,\n" +
                        "  \"transactionDate\": \"2015-12-29T16:37:59\",\n" +
                        "  \"clientAppName\": \"TECHSTOR\",\n" +
                        "  \"agentId\": \"test.test\",\n" +
                        "  \"oeNumber\": \"2342342\"  \n" +
                        "}");
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

        // create random WO ID
        Random generator = new Random();
        long randOrdNum = generator.nextInt(1000000000);
        String randOrdID = "1000000" + randOrdNum;

        // get Status Code
        HttpPost post = new HttpPost("https://agreements-service.integration.apps.int.dev.mer.cfdish.io:443/agreement?sessionId=e1459039-6a0e-48d7-b%25E2%2580%258Bc23-9f39cee2f44c&agentId=test.test");
        StringEntity request = new StringEntity(
                "{\n" +
                        "  \"accountId\": \"8046909012345670\",\n" +
                        "  \"orderId\": \"" + randOrdID + "\",\n" +
                        "  \"agreementID\": \"01\",\n" +
                        "  \"firstName\": \"test\",\n" +
                        "  \"lastName\": \"user\",\n" +
                        "  \"emailAddresss\": \"test@email.com\",\n" +
                        "  \"phoneNumber\": \"7203562314\",\n" +
                        "  \"signatureImage\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUoAAACWCAYAAAC4osHlAAABXklEQVR42u3awQnCQBAF0G3BsBVsEdqEHViMKSbFaDFagjiCVycrRBHzHvxbTnP4JOSXAvCbhsjFGQByu8jJGQByh8jkDAC5MXJ0BoDc9Hy7BCBxjmydASB3jWycAeA1syGADmZDAB3MhgA6jMVsCGCW2RBAB7MhgA6PP+GDMwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMCiaq23JZ4BWG1ZKkmApBCVJEBSjEoSIClLJQmgKAF8egN8tCSVJcAbhagsASVpcA4AAAAAAAAAAAAAAAAzWmt7EZF/iqIUEfl2UQIAAAAAAAAAAAAAsC53KYHVor9O7wsAAAAASUVORK5CYII=\",\n" +
                        "  \"totalAmount\": 12.33,\n" +
                        "  \"taxAmount\": 1.24,\n" +
                        "  \"paymentAmount\": 24.15,\n" +
                        "  \"transactionDate\": \"2015-12-29T16:37:59\",\n" +
                        "  \"clientAppName\": \"TECHSTOR\",\n" +
                        "  \"agentId\": \"test.test\",\n" +
                        "  \"oeNumber\": \"2342342\"  \n" +
                        "}");
        request.setContentType("application/json");
        post.setEntity(request);
        HttpResponse response = client.execute(post);

        StringTokenizer st = new StringTokenizer(fullResponse, "\"");
        String currToken = "";
        String partnerPL = "";
        String acctTypePL = "";
        String billingChanPL = "";
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
        } // while
        System.out.println("partner: " + partnerPL);
        System.out.println("acctType: " + acctTypePL);
        System.out.println("billingChan: " + billingChanPL);
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

        HttpPost post = new HttpPost("https://agreements-service.integration.apps.int.dev.mer.cfdish.io:443/agreement?sessionId=e1459039-6a0e-48d7-b%25E2%2580%258Bc23-9f39cee2f44c&agentId=test.test");

        // set up JSON request
        StringEntity request = new StringEntity(
                "{\n" +
                        "  \"accountId\": \"8046909012345670\",\n" +
                        "  \"orderId\": \"1020100012010210\",\n" +
                        "  \"agreementID\": \"01\",\n" +
                        "  \"firstName\": \"test\",\n" +
                        "  \"lastName\": \"user\",\n" +
                        "  \"emailAddresss\": \"test@email.com\",\n" +
                        "  \"phoneNumber\": \"7203562314\",\n" +
                        "  \"signatureImage\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAUoAAACWCAYAAAC4osHlAAABXklEQVR42u3awQnCQBAF0G3BsBVsEdqEHViMKSbFaDFagjiCVycrRBHzHvxbTnP4JOSXAvCbhsjFGQByu8jJGQByh8jkDAC5MXJ0BoDc9Hy7BCBxjmydASB3jWycAeA1syGADmZDAB3MhgA6jMVsCGCW2RBAB7MhgA6PP+GDMwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMCiaq23JZ4BWG1ZKkmApBCVJEBSjEoSIClLJQmgKAF8egN8tCSVJcAbhagsASVpcA4AAAAAAAAAAAAAAAAzWmt7EZF/iqIUEfl2UQIAAAAAAAAAAAAAsC53KYHVor9O7wsAAAAASUVORK5CYII=\",\n" +
                        "  \"totalAmount\": 12.33,\n" +
                        "  \"taxAmount\": 1.24,\n" +
                        "  \"paymentAmount\": 24.15,\n" +
                        "  \"transactionDate\": \"2015-12-29T16:37:59\",\n" +
                        "  \"clientAppName\": \"TECHSTOR\",\n" +
                        "  \"agentId\": \"test.test\",\n" +
                        "  \"oeNumber\": \"2342342\"  \n" +
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